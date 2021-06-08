package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Category;
import com.demo.tasklist.springbootbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/test")
    private List<Category> test() {
        List<Category> list = categoryRepository.findAll();
        return list;
    }

    @PostMapping("/add")
    public void add(@RequestBody Category category) {
        categoryRepository.save(category);
    }

}
