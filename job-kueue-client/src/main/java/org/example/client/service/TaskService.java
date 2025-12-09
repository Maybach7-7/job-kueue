package org.example.client.service;

import lombok.RequiredArgsConstructor;
import org.example.client.response.TaskResponse;
import org.example.common.entity.Task;
import org.example.common.entity.TaskType;
import org.example.common.entity.TaskTypeEnum;
import org.example.common.repository.TaskRepository;
import org.example.common.repository.TaskStatusRepository;
import org.example.common.repository.TaskTypeRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TaskStatusRepository taskStatusRepository;

    public Task createTask(TaskTypeEnum taskTypeEnum) {
        TaskType taskType = taskTypeRepository.findByTypeName(taskTypeEnum.name());

        Task task = new Task();
        task.setTaskType(taskType);
        task.setTaskStatus(taskStatusRepository.findByStatusName("CREATED"));
        return taskRepository.save(task);
    }

    public Optional<TaskResponse> getTaskById(Integer id) {
        return taskRepository.findByIdWithDetails(id)
                .map(this::convertToResponse);
    }

    private TaskResponse convertToResponse(Task task) {
        return new TaskResponse(
                task.getId(),
                task.getTaskType().getTypeName(),
                task.getTaskStatus().getStatusName(),
                task.getCreatedTime(),
                task.getStartTime(),
                task.getEndTime()
        );
    }
}
