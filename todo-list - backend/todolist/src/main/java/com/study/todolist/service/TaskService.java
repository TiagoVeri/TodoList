package com.study.todolist.service;

import com.study.todolist.entity.Task;
import com.study.todolist.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;
    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public Task getTaskById(Long id){
        return taskRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Task not found"));
    }

    public Task insertTask(Task task){
        return taskRepository.save(task);
    }

    public void updateTask(Task newTask, Long id){
        Task currentTask = getTaskById(id);
        currentTask.setTaskDescription(newTask.getTaskDescription());
        taskRepository.save(currentTask);
    }

    public void deleteTask(Long id){
        Task currentTask = getTaskById(id);
        taskRepository.deleteById(id);
    }
}
