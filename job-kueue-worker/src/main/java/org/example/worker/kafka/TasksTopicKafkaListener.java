package org.example.worker.kafka;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.common.dto.TaskRequest;
import org.example.worker.service.WorkerService;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
@Log4j2
@RequiredArgsConstructor
public class TasksTopicKafkaListener {

    private final WorkerService workerService;

    @KafkaListener(topics = "tasks", groupId = "worker-group")
    public void listenTasksTopic(TaskRequest<?> taskRequest) {
        log.info("Получено сообщение из топика tasks: " + taskRequest);
        workerService.startHandlingTask(taskRequest);
    }
}
