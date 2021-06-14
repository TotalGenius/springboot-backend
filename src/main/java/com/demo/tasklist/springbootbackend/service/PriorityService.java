package com.demo.tasklist.springbootbackend.service;


import com.demo.tasklist.springbootbackend.entity.Priority;
import com.demo.tasklist.springbootbackend.repository.PriorityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PriorityService {

    @Autowired
    private PriorityRepository priorityRepository;

    public List<Priority> findAll() {
        return priorityRepository.findAllByOrderByTitleAsc();
    }

    public Priority add(Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority update(Priority priority) {
        return priorityRepository.save(priority);
    }

    public Priority findById(Long id) {
        return priorityRepository.findById(id).get();
    }

    public void deleteById(Long id) {
        priorityRepository.deleteById(id);
    }

    public List<Priority> findAllByTitle(String title) {
        return priorityRepository.findAllByTitle(title);
    }

}
