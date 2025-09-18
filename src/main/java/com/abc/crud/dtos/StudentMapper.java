package com.abc.crud.dtos;


import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Course;
import com.abc.crud.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface StudentMapper {

    // Convert Entity -> DTO
    @Mapping(target = "departmentId", source = "department.id")
    @Mapping(target = "courseIds",
            expression = "java(student.getCourses() != null ? student.getCourses().stream().map(Course::getId).collect(Collectors.toSet()) : null)")
    StudentDTO toDTO(Student student);

    // Convert DTO -> Entity (relationships handled later in service)
    @Mapping(target = "department", ignore = true)
    @Mapping(target = "courses", ignore = true)
    Student toEntity(StudentDTO dto);
}
