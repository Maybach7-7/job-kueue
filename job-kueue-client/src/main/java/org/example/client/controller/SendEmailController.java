package org.example.client.controller;

import org.example.common.dto.SendEmailRequest;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendEmailController {

    public static final String SEND_EMAIL = "/task/send_email";

    @PostMapping(SEND_EMAIL)
    public void sendEmail(@RequestBody SendEmailRequest request) {
        // заносим информацию о таске в базу
        // публикуем таску в кафка-топик
    }
}
