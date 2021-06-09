package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Category;
import com.demo.tasklist.springbootbackend.entity.Priority;
import com.demo.tasklist.springbootbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != null) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("title cannot be empty or consisted of spaces", HttpStatus.NOT_ACCEPTABLE);
        }
        categoryRepository.save(category);

        return new ResponseEntity("New category was added", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {

        Category tempCategory = categoryRepository.getById(category.getId());

        //Checking id(id cannot be null)
        if (category.getId() == null) {
            return new ResponseEntity("id cannot be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryRepository.save(category);

        return new ResponseEntity("category with id " + category.getId() + " was updated", HttpStatus.OK);
    }


}
