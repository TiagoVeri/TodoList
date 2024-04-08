package com.study.todolist.mapper;

import com.study.todolist.dto.TaskDTO;
import com.study.todolist.entity.Task;

public class TaskMapper {

    public static Task dtoToEntity(TaskDTO taskDTO){
        Task entity = new Task();
        entity.setTaskDescription(taskDTO.getTaskDescription());
        return entity;
    }

    public static TaskDTO entityToDto(Task task){
        TaskDTO dto = new TaskDTO();
        dto.setId(task.getId());
        dto.setTaskDescription(task.getTaskDescription());
        return dto;
    }
}
