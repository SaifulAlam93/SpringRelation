package com.abc.crud.dtos;

import lombok.Data;

import java.util.Set;

@Data
public class CourseDTO {
    private Long id;
    private String name;

    private Set<Long> studentIds;  // only IDs to avoid recursion

}
