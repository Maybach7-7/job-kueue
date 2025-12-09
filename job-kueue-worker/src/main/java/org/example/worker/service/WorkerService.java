package org.example.worker.service;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.example.common.dto.SendEmailRequest;
import org.example.common.dto.TaskRequest;
import org.example.common.entity.TaskStatus;
import org.example.common.entity.TaskStatusEnum;
import org.example.common.entity.TaskTypeEnum;
import org.example.common.repository.TaskRepository;
import org.example.common.repository.TaskStatusRepository;
import org.springframework.stereotype.Service;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final SendEmailService sendEmailService;

    public void startHandlingTask(TaskRequest<?> taskRequest) {
        TaskStatus statusInProgress = taskStatusRepository.findByStatusName(TaskStatusEnum.IN_PROGRESS.name());
        taskRepository.updateStatus(taskRequest.getTaskId(), statusInProgress);
        taskRepository.updateStartTime(taskRequest.getTaskId());
        processTask(taskRequest);
    }

    public void processTask(TaskRequest<?> taskRequest) {
        try {
            switch (taskRequest.getTaskTypeEnum()) {
                case TaskTypeEnum.SEND_EMAIL -> sendEmailService.sendEmail(getSendEmailRequest(taskRequest));
            }
        } catch(RuntimeException exc) {
            TaskStatus statusFailed = taskStatusRepository.findByStatusName(TaskStatusEnum.FAILED.name());
            taskRepository.updateStatus(taskRequest.getTaskId(), statusFailed);
            return;
        }

        TaskStatus statusFinished= taskStatusRepository.findByStatusName(TaskStatusEnum.FINISHED.name());
        taskRepository.updateStatus(taskRequest.getTaskId(), statusFinished);
        taskRepository.updateEndTime(taskRequest.getTaskId());
    }

    private SendEmailRequest getSendEmailRequest(TaskRequest<?> taskRequest) {
        ObjectMapper mapper = new ObjectMapper();
        SendEmailRequest request = mapper.convertValue(
                (LinkedHashMap) taskRequest.getTaskData(),
                SendEmailRequest.class
        );
        return request;
    }
}
