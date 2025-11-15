package org.example.common.dto;

import lombok.Data;

@Data
public class SendEmailRequest {

    private String recipient;

    private String subject;

    private String body;
}
