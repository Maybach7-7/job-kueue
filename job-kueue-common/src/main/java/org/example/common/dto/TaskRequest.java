package org.example.common.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.example.common.entity.TaskTypeEnum;

@Data
@ToString
@NoArgsConstructor
public class TaskRequest<T> {

    @JsonProperty("task_type")
    private TaskTypeEnum taskTypeEnum;

    @JsonProperty("task_id")
    private Integer taskId;

    @JsonProperty("task_data")
    private T taskData;

    public TaskRequest(TaskTypeEnum taskTypeEnum, Integer taskId, T taskData) {
        this.taskTypeEnum = taskTypeEnum;
        this.taskId = taskId;
        this.taskData = taskData;
    }
}
