package com.abc.crud.dtos;

import lombok.Data;

import java.time.LocalDate;

@Data
public class StudentDTO {
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private LocalDate dob;

    // Only include department name (or id+name if needed)
    private DepartmentSummaryDTO department;
}
