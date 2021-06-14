package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Priority;
import com.demo.tasklist.springbootbackend.repository.PriorityRepository;
import com.demo.tasklist.springbootbackend.search.PrioritySearchValue;
import com.demo.tasklist.springbootbackend.service.PriorityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityService priorityService;

    @Value("${priorityDefaultColor}")
    private String defaultColor;

    @GetMapping("/all")
    public List<Priority> findAll() {
        List<Priority> list = priorityService.findAll();
        return list;
    }

    @PostMapping("/add")
    public ResponseEntity<Priority> add(@RequestBody Priority priority) {

        //Checking id (id must be null)
        if (priority.getId() != null) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }

        //Checking title (title cannot be null or consists if spaces)
        if (priority.getTitle() == null || priority.getTitle().trim().length() == 0) {
            return new ResponseEntity("Title cannot be empty or consists of only spaces", HttpStatus.NOT_ACCEPTABLE);
        }

        //Checking color (if color empty set default color)
        if (priority.getColor() == null || priority.getColor().trim().length() == 0) {
            priority.setColor(defaultColor);
        }

        priorityService.add(priority);
        return new ResponseEntity("New Priority was added", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Priority> update(@RequestBody Priority priority) {

        //Checking id(id cannot be null)
        if (priority.getId() == null) {
            return new ResponseEntity("id cannot be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        priorityService.update(priority);

        return new ResponseEntity("Priority with id " + priority.getId() + " was updated", HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity<Priority> findById(@PathVariable Long id) {
        Priority priority = null;

        /*
        If priority with such doesn't exist exception will be thrown,
        and we will send special message to client
         */

        try {
            priority = priorityService.findById(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("Priority with such id doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return ResponseEntity.ok(priority);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Priority> deleteById(@PathVariable Long id) {
        try {
            priorityService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Couldn't delete priority with such id. Priority with such id doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Priority with such id was deleted", HttpStatus.OK);
    }

    @PostMapping("/search")
    public ResponseEntity<List<Priority>> findByTitle(@RequestBody PrioritySearchValue prioritySearchValue) {
        List<Priority> list = priorityService.findAllByTitle(prioritySearchValue.getTitle());
        return ResponseEntity.ok(list);
    }
}
