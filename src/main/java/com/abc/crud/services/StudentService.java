package com.abc.crud.services;


import com.abc.crud.dtos.DepartmentDTO;
import com.abc.crud.dtos.DtoMapper;
import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Course;
import com.abc.crud.entity.Department;
import com.abc.crud.entity.Student;
import com.abc.crud.repository.CourseRepository;
import com.abc.crud.repository.DepartmentRepository;
import com.abc.crud.repository.StudentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;
    public StudentService(StudentRepository repository, CourseRepository courseRepository, DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    public Student save(Student student) {
        return repository.save(student);
    }

    public List<Student> findAll() {
        return repository.findAll();
    }

    public Optional<Student> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

    public List<StudentDTO> getAllStudents() {


        return repository.findAll().stream()
                .map(DtoMapper::toStudentDTO)
                .toList();
    }

    public StudentDTO getStudentById(Long id) {

        Optional<Student> st = repository.findById(id);
        StudentDTO std = new StudentDTO();
//        if (st.isPresent()){
//            BeanUtils.copyProperties(st.get(), std);
//        }
        st.ifPresent(student -> {
            BeanUtils.copyProperties(student, std);
            Set<Long> oldC = student.getCourses().stream().map(Course::getId).collect(Collectors.toSet());

            std.setCourseIds(oldC);
        });
        return std;

//        return repository.findById(id)
//                .map(DtoMapper::toStudentDTO)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
    }

    public  StudentDTO toDTO(Student student) {
        if (student == null) return null;
        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setDob(student.getDob());

        if (student.getDepartment() != null) {
            DepartmentDTO dp = new DepartmentDTO();
            dp.setId(student.getDepartment().getId());
            dp.setName(student.getDepartment().getName());
            dto.setDepartmentDTO(dp);
        }
        if (student.getCourses() != null) {
            dto.setCourseIds(
                    student.getCourses().stream()
                            .map(Course::getId)
                            .collect(Collectors.toSet())
            );
        }
        return dto;
    }

    public  Student toEntity(StudentDTO dto) {
        if (dto == null) return null;

        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());

        if (dto.getDepartmentDTO()!=null){
            Department dt = departmentRepository.findById(dto.getDepartmentDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found"));
            student.setDepartment(dt);
        }

        if (dto.getCourseIds() != null && !dto.getCourseIds().isEmpty()) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(dto.getCourseIds()));
            student.getCourses().clear();   // <-- Important: clear old links
            student.getCourses().addAll(courses); // add fresh set
        }
        return student;
    }
}
