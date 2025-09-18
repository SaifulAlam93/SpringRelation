package com.abc.crud.dtos;


import com.abc.crud.entity.Course;
import com.abc.crud.entity.Student;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.stream.Collectors;

@Mapper(componentModel = "spring")
public interface CourseMapper {

    @Mapping(target = "studentIds",
            expression = "java(course.getStudents() != null ? course.getStudents().stream().map(Student::getId).collect(Collectors.toSet()) : null)")
    CourseDTO toDTO(Course course);

    @Mapping(target = "students", ignore = true)
    Course toEntity(CourseDTO dto);
}
