package com.abc.crud.dtos;

import lombok.Data;

import java.util.List;
@Data
public class DepartmentDTO {
    private Long id;
    private String name;

    // Avoid recursion: include only student summaries
    private List<StudentSummaryDTO> students;
}
