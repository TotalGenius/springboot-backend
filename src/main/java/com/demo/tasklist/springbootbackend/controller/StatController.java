package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Priority;
import com.demo.tasklist.springbootbackend.entity.Stat;
import com.demo.tasklist.springbootbackend.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
public class StatController {

    @Value("${defaultId}")
    private Long defaultId;

    @Autowired
    private StatRepository statRepository;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        Stat stat = statRepository.findById(defaultId).get();
        return ResponseEntity.ok(stat);
    }
}

