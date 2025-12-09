package org.example.worker.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.example.common.dto.SendEmailRequest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class SendEmailService {

    private final JavaMailSender javaMailSender;

    public void sendEmail(SendEmailRequest sendEmailRequest) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setTo(sendEmailRequest.getRecipientEmail());
        message.setSubject(sendEmailRequest.getSubject());
        message.setText(sendEmailRequest.getBody());
        message.setFrom("samusevdenis7@yandex.ru");

        log.info("Происходит отправка email на адрес: " + sendEmailRequest.getRecipientEmail());
//        try {
            javaMailSender.send(message);
//            Thread.sleep(10000);
//        } catch (InterruptedException e) {
//            throw new RuntimeException(e);
//        }
        log.info("Email на адрес " + sendEmailRequest.getRecipientEmail() + " успешно отправлен!");
    }
}
