package org.example.client.controller;

import jakarta.validation.Valid;
import org.example.common.dto.SendEmailRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailController {

    public static final String SEND_EMAIL = "/task/send_email";

    @PostMapping(SEND_EMAIL)
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid SendEmailRequest sendEmailRequest) {
        // валидируем запрос
        // заносим информацию о новой таске в базу
        // публикуем таску в кафка-топик
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
}
