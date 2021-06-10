package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Stat;
import com.demo.tasklist.springbootbackend.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/stat")
public class StatController {

    @Autowired
    private StatRepository statRepository;

    @GetMapping("/all")
    public ResponseEntity<List<Stat>> findById() {

        List<Stat> list = statRepository.findAll();
        return ResponseEntity.ok(list);
    }
}

