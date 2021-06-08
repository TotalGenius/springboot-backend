package com.demo.tasklist.springbootbackend.repository;

import com.demo.tasklist.springbootbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
}
