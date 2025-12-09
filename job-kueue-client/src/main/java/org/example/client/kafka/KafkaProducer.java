package org.example.client.kafka;

import lombok.RequiredArgsConstructor;
import org.example.common.dto.TaskRequest;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class KafkaProducer {

    private final KafkaTemplate<String, TaskRequest<?>> kafkaTemplate;

    public void sendJsonObject(String topic, TaskRequest<?> jsonObject) {
        kafkaTemplate.send(topic, jsonObject.getTaskId().toString(), jsonObject);
    }
}
