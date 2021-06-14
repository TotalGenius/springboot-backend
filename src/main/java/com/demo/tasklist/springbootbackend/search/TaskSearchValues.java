package com.demo.tasklist.springbootbackend.search;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
public class TaskSearchValues {
    //Fields for search by params
    private String title;
    private Integer completed;
    private Long priorityId;
    private Long categoryId;

    //Fields for paging
    private Integer pageNumber;
    private Integer pageSize;

    //Sorting
    private String sortColumn;
    private String sortDirection;


}
