package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Priority;
import com.demo.tasklist.springbootbackend.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/priority")
public class PriorityController {

    @Autowired
    private PriorityRepository priorityRepository;

    @GetMapping("/test")
    public List<Priority> test() {
        List<Priority> list = priorityRepository.findAll();
        return list;
    }

    @PostMapping("/add")
    public void add(@RequestBody Priority priority) {
        priorityRepository.save(priority);
    }


}
