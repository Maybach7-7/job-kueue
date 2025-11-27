package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.ToString;

@Data
@ToString
public class SendEmailRequest {

    @NotBlank(message = "Поле recipient_email не может быть пустым")
    @Email(message = "Поле recipient_email должно быть email-адресом")
    @JsonProperty("recipient_email")
    private String recipientEmail;

    @NotBlank(message = "Поле subject не может быть пустым")
    @Size(max = 255, message = "Тема письма не должна превышать 255 символов")
    @JsonProperty("subject")
    private String subject;

    @NotBlank(message = "Поле body не может быть пустым")
    @Size(max = 10000, message = "Тело письма не должно превышать 10000 символов")
    @JsonProperty("body")
    private String body;
}
