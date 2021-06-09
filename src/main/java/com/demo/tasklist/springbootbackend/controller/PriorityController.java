package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Priority;
import com.demo.tasklist.springbootbackend.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityRepository priorityRepository;

    @Value("${priorityDefaultColor}")
    private String defaultColor;

    @GetMapping("/test")
    public List<Priority> test() {
        List<Priority> list = priorityRepository.findAll();
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

        priorityRepository.save(priority);
        return new ResponseEntity("New Priority was added", HttpStatus.OK);
    }


}
