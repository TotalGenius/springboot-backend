package com.demo.tasklist.springbootbackend.service;

import com.demo.tasklist.springbootbackend.entity.Task;
import com.demo.tasklist.springbootbackend.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class TaskService {

    @Autowired
    private TaskRepository taskRepository;


    public List<Task> findAll() {
        return taskRepository.findAllByOrderByTitleAsc();
    }

    public Task add(Task task) {
        return taskRepository.save(task);
    }

    public Task update(Task task) {
        return taskRepository.save(task);
    }

    public Task findById(Long id) {
        return taskRepository.findById(id).get();
    }

    public void delete(Long id) {
        taskRepository.deleteById(id);
    }

    public Page<Task> findByParams(String title, Integer completed, Long priorityId, Long categoryId, Pageable pageable) {
        return taskRepository.findByParams(title, completed, priorityId, categoryId, pageable);
    }

}
