package com.demo.tasklist.springbootbackend.service;

import com.demo.tasklist.springbootbackend.entity.Stat;
import com.demo.tasklist.springbootbackend.repository.StatRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@Transactional
public class StatService {
    @Autowired
    StatRepository statRepository;

    public Stat findById(Long id) {
        return statRepository.findById(id).get();
    }

}
