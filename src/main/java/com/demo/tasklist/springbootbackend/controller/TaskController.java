package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Task;
import com.demo.tasklist.springbootbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> taskList = taskRepository.findAllByOrderByTitleAsc();
        return ResponseEntity.ok(taskList);
    }

    @PostMapping("/add")
    public ResponseEntity<Task> add(@RequestBody Task task) {
        if (task.getId() != null) {
            return new ResponseEntity("Task id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (task.getTitle() == null || task.getTitle().trim().length() == 0) {
            return new ResponseEntity("Title cannot be empty", HttpStatus.NOT_ACCEPTABLE);
        }
        taskRepository.save(task);
        return new ResponseEntity("New task was added", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        if (task.getId() == null) {
            return new ResponseEntity("id cannot be null", HttpStatus.NOT_ACCEPTABLE);
        }
        taskRepository.save(task);
        return new ResponseEntity("Task with id=" + task.getId() + " was updated", HttpStatus.OK);

    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        Task task = null;
        try {
            task = taskRepository.findById(id).get();
        } catch (NoSuchElementException e) {

            return new ResponseEntity("There is no element with such id=" + id, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        try {
            taskRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Couldn't delete Task with such id. Task with id=" + id + " doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Task with id=" + id + " was deleted", HttpStatus.OK);
    }



}
