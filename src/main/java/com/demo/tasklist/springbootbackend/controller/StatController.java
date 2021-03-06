package com.demo.tasklist.springbootbackend.controller;


import com.demo.tasklist.springbootbackend.entity.Stat;
import com.demo.tasklist.springbootbackend.repository.StatRepository;
import com.demo.tasklist.springbootbackend.service.StatService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class StatController {

    @Value("${defaultId}")
    private Long defaultId;

    @Autowired
    private StatService statService;

    @GetMapping("/stat")
    public ResponseEntity<Stat> findById() {

        Stat stat = statService.findById(defaultId);
        return ResponseEntity.ok(stat);
    }
}

