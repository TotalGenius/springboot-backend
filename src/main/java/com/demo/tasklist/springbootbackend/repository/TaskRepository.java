package com.demo.tasklist.springbootbackend.repository;

import com.demo.tasklist.springbootbackend.entity.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByOrderByTitleAsc();
}
