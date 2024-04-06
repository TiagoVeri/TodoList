package com.study.todolist.controller;

import com.study.todolist.entity.Task;
import com.study.todolist.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping("api/task")
public class TaskController {

    @Autowired
    private TaskService taskService;

    @GetMapping("/tasklist")
    public ResponseEntity<List<Task>> getAllTasks(){
        return ResponseEntity.ok().body(taskService.getAllTasks());
    }

    @GetMapping("{id}")
    public ResponseEntity<Task> getTaskById(@PathVariable Long id){
        return ResponseEntity.ok().body(taskService.getTaskById(id));
    }

    @PostMapping
    public ResponseEntity<Task> insertTask(@RequestBody Task task){
        Task newTask= taskService.insertTask(task);

        URI uri = ServletUriComponentsBuilder
                .fromCurrentRequest()
                .path("/{id}")
                .buildAndExpand(newTask.getId())
                .toUri();

        return ResponseEntity.created(uri).build();
    }

    @PutMapping("{id}")
    public ResponseEntity<Void> updateTask(@RequestBody Task task, @PathVariable Long id)  {
        try{
            taskService.updateTask(task,id);
        } catch (RuntimeException e){
            throw new RuntimeException("Something went wrong when updating the description");
        }
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> deleteTask (@PathVariable Long id){
        try{
            taskService.deleteTask(id);
        } catch (RuntimeException e){
            throw new RuntimeException("Something went wrong when deleting the task");
        }
        return ResponseEntity.noContent().build();
    }
}
