package com.demo.tasklist.springbootbackend.repository;

import com.demo.tasklist.springbootbackend.entity.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TaskRepository extends JpaRepository<Task, Long> {
    public List<Task> findAllByOrderByTitleAsc();

    @Query("select c from Task c where " +
            "(:title is null or :title='' or lower(c.title) like lower(concat( '%', :title, '%' ) ) ) and" +
            "(:completed is null or c.completed = :completed) and" +
            "(:priority is null or c.priority.id=:priority) and" +
            "(:category is null or c.category.id=:category)")
    public Page<Task> findByParams(@Param("title") String title,
                                   @Param("completed") Integer completed,
                                   @Param("priority") Long priorityId,
                                   @Param("category") Long categoryId,
                                   Pageable pageable);


}
