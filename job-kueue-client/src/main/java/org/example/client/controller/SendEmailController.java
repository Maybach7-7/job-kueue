package org.example.client.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.example.client.service.SendEmailService;
import org.example.client.service.TaskService;
import org.example.common.dto.SendEmailRequest;
import org.example.client.response.TaskResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequiredArgsConstructor
@Tag(name = "Отправка Email", description = "API для создания и получения задач по отправке email")
public class SendEmailController {

    public static final String SEND_EMAIL = "/task/send_email";
    public static final String GET_TASK_BY_ID = "/task/{id}";

    private final SendEmailService sendEmailService;
    private final TaskService taskService;

    @Operation(summary = "Создать задачу на отправку email", description = "Создает новую задачу для отправки email сообщения")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Задача успешно создана"),
            @ApiResponse(responseCode = "400", description = "Некорректный формат запроса",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = org.example.client.response.ValidationErrorResponse.class)))
    })
    @PostMapping(SEND_EMAIL)
    public ResponseEntity<Void> sendEmail(@RequestBody @Valid SendEmailRequest sendEmailRequest) {
        var taskId =  sendEmailService.processRequest(sendEmailRequest);

        URI location = ServletUriComponentsBuilder.fromCurrentContextPath()
                .path("/task/{id}")
                .buildAndExpand(taskId)
                .toUri();

        return ResponseEntity.created(location).build();
    }

    @Operation(summary = "Получить задачу по ID", description = "Возвращает информацию о задаче по её идентификатору")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Задача найдена",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = TaskResponse.class))),
            @ApiResponse(responseCode = "404", description = "Задача не найдена")
    })
    @GetMapping(GET_TASK_BY_ID)
    public ResponseEntity<TaskResponse> getTaskById(@PathVariable Integer id) {
        return taskService.getTaskById(id)
                .map(taskResponse -> new ResponseEntity<>(taskResponse, HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}
