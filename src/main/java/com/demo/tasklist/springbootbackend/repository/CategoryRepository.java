package com.demo.tasklist.springbootbackend.repository;

import com.demo.tasklist.springbootbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {
    public List<Category> findAllByOrderByIdAsc();

    @Query("select c from Category c where (:title is null  or :title='' or lower(c.title) like lower(concat('%', :title, '%') ) )")
    public List<Category> findAllByTitle(@Param ("title") String title);
}
