package com.demo.tasklist.springbootbackend.repository;


import com.demo.tasklist.springbootbackend.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    public List<Priority> findAllByOrderByTitleAsc();

    @Query("select c from Priority c where (:title is null or :title='' or lower(c.title)like lower(concat('%', :title,'%') ) )")
    public List<Priority> findAllByTitle(@Param("title")String title);

}