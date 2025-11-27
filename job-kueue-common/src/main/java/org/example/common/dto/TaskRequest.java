package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@ToString
@NoArgsConstructor
public class TaskRequest {

    @JsonProperty("task_type")
    private TaskType taskType;

    @JsonProperty("task_data")
    private Object taskData;

    public TaskRequest(TaskType taskType, SendEmailRequest sendEmailRequest) {
        this.taskType = taskType;
        this.taskData = sendEmailRequest;
    }
}
