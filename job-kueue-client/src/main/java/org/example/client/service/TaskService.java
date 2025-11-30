package org.example.client.service;

import lombok.RequiredArgsConstructor;
import org.example.common.repository.TaskRepository;
import org.example.common.repository.TaskStatusRepository;
import org.example.common.repository.TaskTypeRepository;
import org.example.common.entity.Task;
import org.example.common.entity.TaskType;
import org.example.common.entity.TaskTypeEnum;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class TaskService {

    private final TaskRepository taskRepository;
    private final TaskTypeRepository taskTypeRepository;
    private final TaskStatusRepository taskStatusRepository;

    public Task createTask(TaskTypeEnum taskTypeEnum) {
        TaskType taskType = taskTypeRepository.findByName(taskTypeEnum.name());

        Task task = new Task();
        task.setTaskType(taskType);
        task.setTaskStatus(taskStatusRepository.findByStatusName("CREATED"));
        return taskRepository.save(task);
    }
}
