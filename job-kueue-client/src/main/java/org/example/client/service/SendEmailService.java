package org.example.client.service;

import jakarta.persistence.criteria.CriteriaBuilder;
import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.example.client.kafka.KafkaProducer;
import org.example.common.dto.SendEmailRequest;
import org.example.common.dto.TaskRequest;
import org.example.common.entity.TaskTypeEnum;
import org.example.common.entity.Task;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class SendEmailService {

    private static final String TASKS_TOPIC = "tasks";

    private final KafkaProducer kafkaProducer;

    private final TaskService taskService;

    @Transactional
    public Integer processRequest(@Valid SendEmailRequest sendEmailRequest) {
        log.info("Начало обработки запроса для sendEmailRequest: " + sendEmailRequest);

        Task newTask = taskService.createTask(TaskTypeEnum.SEND_EMAIL);

        TaskRequest<SendEmailRequest> taskRequest = new TaskRequest<>(
                TaskTypeEnum.SEND_EMAIL,
                newTask.getId(),
                sendEmailRequest
        );
        log.info("Создан taskRequest: " + taskRequest);
        log.info("Отправка объекта taskRequest в кафка");
        kafkaProducer.sendJsonObject(TASKS_TOPIC, taskRequest);
        log.info("Объект отправлен в kafka");
        return newTask.getId();
    }
}
