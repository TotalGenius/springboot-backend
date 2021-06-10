package com.demo.tasklist.springbootbackend.repository;


import com.demo.tasklist.springbootbackend.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    public List<Priority> findAllByOrderByTitleAsc();

}