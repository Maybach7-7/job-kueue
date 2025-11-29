package org.example.worker.kafka;

import lombok.extern.log4j.Log4j2;
import org.example.common.dto.TaskRequest;
import org.springframework.stereotype.Component;
import org.springframework.kafka.annotation.KafkaListener;

@Component
@Log4j2
public class TasksTopicKafkaListener {

    @KafkaListener(topics = "tasks", groupId = "worker-group")
    public void listenTasksTopic(TaskRequest taskRequest) {
        log.info("Получено сообщение из топика tasks: " + taskRequest);
    }
}
