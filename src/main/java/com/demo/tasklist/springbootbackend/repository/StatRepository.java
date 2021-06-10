package com.demo.tasklist.springbootbackend.repository;

import com.demo.tasklist.springbootbackend.entity.Stat;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StatRepository extends JpaRepository<Stat, Long> {
}
