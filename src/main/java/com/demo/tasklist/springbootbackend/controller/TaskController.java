package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Task;
import com.demo.tasklist.springbootbackend.search.TaskSearchValues;
import com.demo.tasklist.springbootbackend.service.TaskService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/task")
public class TaskController {
    @Autowired
    private TaskService taskService;

    @GetMapping("/all")
    public ResponseEntity<List<Task>> findAll() {
        List<Task> taskList = taskService.findAll();
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
        taskService.add(task);
        return new ResponseEntity("New task was added", HttpStatus.OK);
    }


    @PutMapping("/update")
    public ResponseEntity<Task> update(@RequestBody Task task) {

        if (task.getId() == null) {
            return new ResponseEntity("id cannot be null", HttpStatus.NOT_ACCEPTABLE);
        }
        taskService.update(task);
        return new ResponseEntity("Task with id=" + task.getId() + " was updated", HttpStatus.OK);

    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Task> findById(@PathVariable Long id) {

        Task task = null;
        try {
            task = taskService.findById(id);
        } catch (NoSuchElementException e) {

            return new ResponseEntity("There is no element with such id=" + id, HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(task);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Task> delete(@PathVariable Long id) {
        try {
            taskService.delete(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Couldn't delete Task with such id. Task with id=" + id + " doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Task with id=" + id + " was deleted", HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<Page<Task>> findByParams(@RequestBody TaskSearchValues taskSearchValues) {

        //Data for search
        String title = taskSearchValues.getTitle() != null ? taskSearchValues.getTitle() : null;
        Integer completed = taskSearchValues.getCompleted() != null ? taskSearchValues.getCompleted() : null;
        Long priorityId = taskSearchValues.getPriorityId() != null ? taskSearchValues.getPriorityId() : null;
        Long categoryId = taskSearchValues.getCategoryId() != null ? taskSearchValues.getCategoryId() : null;

        //Data for paging
        Integer pageSize = taskSearchValues.getPageSize() != null ? taskSearchValues.getPageSize() : 0;
        Integer pageNumber = taskSearchValues.getPageNumber() != null ? taskSearchValues.getPageNumber() : 0;

        //Data for sorting
        String sortColumn = taskSearchValues.getSortColumn() != null ? taskSearchValues.getSortColumn() : null;
        Sort.Direction direction = taskSearchValues.getSortDirection().trim().equals("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;

        //Sorting object
        Sort sort = Sort.by(direction, sortColumn);

        //Paging object
        PageRequest pageRequest = PageRequest.of(pageNumber, pageSize, sort);

        Page result = taskService.findByParams(title, completed, priorityId, categoryId, pageRequest);

        return ResponseEntity.ok(result);
    }


}
