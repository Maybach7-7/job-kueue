package org.example.client.service;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.client.kafka.KafkaProducer;
import org.example.common.dto.SendEmailRequest;
import org.example.common.dto.TaskRequest;
import org.example.common.dto.TaskType;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SendEmailService {

    private static final String TASKS_TOPIC = "tasks";

    private final KafkaProducer kafkaProducer;

    public void processRequest(@Valid SendEmailRequest sendEmailRequest) {
        log.info("Начало обработки запроса для sendEmailRequest: " + sendEmailRequest);
        // надо записать инфу о таске в базу данных
        // записываем сообщение в кафку
        TaskRequest taskRequest = new TaskRequest(
                TaskType.SEND_EMAIL,
                sendEmailRequest
        );
        log.info("Создан taskRequest: " + taskRequest);
        log.info("Отправка объекта taskRequest в кафка");
        kafkaProducer.sendJsonObject(TASKS_TOPIC, taskRequest);
        log.info("Объект отправлен в kafka");
    }
}
