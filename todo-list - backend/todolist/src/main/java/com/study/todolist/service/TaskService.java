package com.study.todolist.service;

import com.study.todolist.dto.TaskDTO;
import com.study.todolist.entity.Task;
import com.study.todolist.exceptions.ResourceNotFoundException;
import com.study.todolist.mapper.TaskMapper;
import com.study.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.lang.module.ResolutionException;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    public List<TaskDTO> getAllTasks(){
        List<TaskDTO> taskDTOList = taskRepository.findAll().stream().map(task -> {
            return new TaskDTO(task.getId(), task.getTaskDescription());
        }).collect(Collectors.toList());
        return taskDTOList;
    }

    public TaskDTO getTaskById(Long id){
        Task task = taskRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Task", "Task ID", String.valueOf(id)));
        return TaskMapper.entityToDto(task);
    }


    @Transactional
    public TaskDTO insertTask(TaskDTO taskDTO){
        return TaskMapper.entityToDto(taskRepository.save(TaskMapper.dtoToEntity(taskDTO)));
    }

    @Transactional
    public TaskDTO updateTask(TaskDTO newTask, Long id){
        TaskDTO currentTask = getTaskById(id);
        currentTask.setTaskDescription(newTask.getTaskDescription());
        return TaskMapper.entityToDto(taskRepository.save(TaskMapper.dtoToEntity(currentTask)));
    }

    @Transactional
    @Modifying
    public void deleteTask(Long id){
        TaskDTO currentTask = getTaskById(id);
        taskRepository.deleteById(currentTask.getId());
    }
}
