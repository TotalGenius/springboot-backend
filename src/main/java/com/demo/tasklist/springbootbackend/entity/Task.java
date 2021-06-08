package com.demo.tasklist.springbootbackend.entity;

import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.sql.Date;

@Entity
@NoArgsConstructor
@Setter
@EqualsAndHashCode
public class Task {
    private Long id;
    private String title;
    private Integer completed;
    private Date date;
    private Priority priority;
    private Category category;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    public Long getId() {
        return id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 100)
    public String getTitle() {
        return title;
    }


    @Basic
    @Column(name = "completed", nullable = true)
    public Integer getCompleted() {
        return completed;
    }


    @Basic
    @Column(name = "date", nullable = true)
    public Date getDate() {
        return date;
    }



    @ManyToOne
    @JoinColumn(name = "priority_id", referencedColumnName = "id")
    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priorityByPriorityId) {
        this.priority = priorityByPriorityId;
    }

    @ManyToOne
    @JoinColumn(name = "category_id", referencedColumnName = "id")
    public Category getCategory() {
        return category;
    }

}
