package com.study.todolist.dto;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TaskDTO {

    private Long id;

    @NotEmpty(message = "Task Description can not be a null or empty")
    @Size(min = 5, max = 100, message = "Task Description must be between 5 and 100 characters")
    private String taskDescription;


}
