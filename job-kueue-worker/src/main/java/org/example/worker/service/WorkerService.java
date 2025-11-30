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
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedHashMap;

@Service
@RequiredArgsConstructor
public class WorkerService {

    private final TaskRepository taskRepository;
    private final TaskStatusRepository taskStatusRepository;
    private final SendEmailService sendEmailService;

    @Transactional
    public void processMessage(TaskRequest<?> taskRequest) {
        TaskStatus statusInProgress = taskStatusRepository.findByStatusName(TaskStatusEnum.IN_PROGRESS.name());
        taskRepository.updateStatus(taskRequest.getTaskId(), statusInProgress);

        switch (taskRequest.getTaskTypeEnum()) {
            case TaskTypeEnum.SEND_EMAIL -> sendEmailService.sendEmail(getSendEmailRequest(taskRequest));
        }
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
