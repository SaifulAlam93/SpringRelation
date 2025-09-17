package com.abc.crud.dtos;

import com.abc.crud.entity.Department;
import com.abc.crud.entity.Student;

import java.util.List;

public class DtoMapper {

    // Student → StudentDTO
    public static StudentDTO toStudentDTO(Student student) {
        if (student == null) return null;

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setDob(student.getDob());

        if (student.getDepartment() != null) {
            DepartmentSummaryDTO depDto = new DepartmentSummaryDTO();
            depDto.setId(student.getDepartment().getId());
            depDto.setName(student.getDepartment().getName());
            dto.setDepartment(depDto);
        }

        return dto;
    }

    // Department → DepartmentDTO
    public static DepartmentDTO toDepartmentDTO(Department department) {
        if (department == null) return null;

        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());

        if (department.getStudents() != null) {
            List<StudentSummaryDTO> students = department.getStudents().stream()
                    .map(s -> {
                        StudentSummaryDTO summary = new StudentSummaryDTO();
                        summary.setId(s.getId());
                        summary.setFirstName(s.getFirstName());
                        summary.setLastName(s.getLastName());
                        return summary;
                    })
                    .toList();
            dto.setStudents(students);
        }

        return dto;
    }
}
