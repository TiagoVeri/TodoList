package com.study.todolist.service;

import com.study.todolist.dto.TaskDTO;
import com.study.todolist.entity.Task;
import com.study.todolist.mapper.TaskMapper;
import com.study.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
                .orElseThrow(() -> new RuntimeException("Task was not found."));
        return TaskMapper.entityToDto(task);
    }

    public TaskDTO insertTask(TaskDTO taskDTO){
        try{
            return TaskMapper.entityToDto(taskRepository.save(TaskMapper.dtoToEntity(taskDTO)));
        } catch (RuntimeException e){
            throw new RuntimeException("Something went wrong when saving new Task");
        }
    }

    public TaskDTO updateTask(TaskDTO newTask, Long id){
        TaskDTO currentTask = getTaskById(id);
        currentTask.setTaskDescription(newTask.getTaskDescription());
        return TaskMapper.entityToDto(taskRepository.save(TaskMapper.dtoToEntity(currentTask)));
    }

    public void deleteTask(Long id){
        TaskDTO currentTask = getTaskById(id);
        taskRepository.deleteById(currentTask.getId());
    }
}
