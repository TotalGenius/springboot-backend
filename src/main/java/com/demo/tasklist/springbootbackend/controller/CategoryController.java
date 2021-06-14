package com.demo.tasklist.springbootbackend.controller;

import com.demo.tasklist.springbootbackend.entity.Category;
import com.demo.tasklist.springbootbackend.search.CategorySearchValues;
import com.demo.tasklist.springbootbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @GetMapping("/all")
    public ResponseEntity<List<Category>> findAll() {
        List<Category> list = categoryService.findAllByOrderedByAsc();
        return ResponseEntity.ok(list);
    }

    @PostMapping("/add")
    public ResponseEntity<Category> add(@RequestBody Category category) {
        if (category.getId() != null) {
            return new ResponseEntity("id must be null", HttpStatus.NOT_ACCEPTABLE);
        }
        if (category.getTitle() == null || category.getTitle().trim().length() == 0) {
            return new ResponseEntity("title cannot be empty or consisted of spaces", HttpStatus.NOT_ACCEPTABLE);
        }
        categoryService.add(category);

        return new ResponseEntity("New category was added", HttpStatus.OK);
    }

    @PutMapping("/update")
    public ResponseEntity<Category> update(@RequestBody Category category) {


        //Checking id(id cannot be null)
        if (category.getId() == null) {
            return new ResponseEntity("id cannot be empty", HttpStatus.NOT_ACCEPTABLE);
        }

        categoryService.update(category);

        return new ResponseEntity("category with id " + category.getId() + " was updated", HttpStatus.OK);
    }


    @GetMapping("/id/{id}")
    public ResponseEntity<Category> findById(@PathVariable Long id) {

        Category category = null;
        /*
        If category with such doesn't exist exception will be thrown,
        and we will send special message to client
         */
        try {
            category = categoryService.findById(id);
        } catch (NoSuchElementException e) {
            return new ResponseEntity("There is no category with such id ", HttpStatus.NOT_ACCEPTABLE);
        }
        //if category with such id exists we will return this category to client
        return ResponseEntity.ok(category);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Category> deleteById(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return new ResponseEntity("Couldn't delete category with such id. Category with such id doesn't exist", HttpStatus.NOT_ACCEPTABLE);
        }
        return new ResponseEntity("Category with such id was deleted", HttpStatus.OK);
    }

    //search by certain value(s)
    @PostMapping("/search")
    public ResponseEntity<List<Category>> findByTitle(@RequestBody CategorySearchValues categorySearchValues) {
        List<Category> list = categoryService.findAllByTitle(categorySearchValues.getTitle());
        return ResponseEntity.ok(list);
    }
}
