package org.example.worker.service;

import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.SendEmailRequest;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class SendEmailService {

    public void sendEmail(SendEmailRequest sendEmailRequest) {
        log.info("Происходит отправка email на адрес: " + sendEmailRequest.getRecipientEmail());
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        log.info("Email на адрес " + sendEmailRequest.getRecipientEmail() + " успешно отправлен!");
    }
}
