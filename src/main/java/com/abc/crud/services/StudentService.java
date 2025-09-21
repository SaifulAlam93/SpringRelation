package com.abc.crud.services;

import com.abc.crud.config.BaseEntity;
import com.abc.crud.dtos.DepartmentDTO;
import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Course;
import com.abc.crud.entity.Department;
import com.abc.crud.entity.Student;
import com.abc.crud.repository.CourseRepository;
import com.abc.crud.repository.DepartmentRepository;
import com.abc.crud.repository.StudentRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

    private final StudentRepository repository;
    private final CourseRepository courseRepository;
    private final DepartmentRepository departmentRepository;

    public StudentService(StudentRepository repository,
                          CourseRepository courseRepository,
                          DepartmentRepository departmentRepository) {
        this.repository = repository;
        this.courseRepository = courseRepository;
        this.departmentRepository = departmentRepository;
    }

    @Transactional
    public StudentDTO save(StudentDTO studentDTO) {
        Student student = toEntity(studentDTO);
        return toDTO(repository.save(student));
    }

//    @Transactional(readOnly = true)
    public List<StudentDTO> getAllStudents() {
        return repository.findAll()
                .stream()
                .map(this::toDTO)
                .toList();
    }
//    @Transactional(readOnly = true)
    public StudentDTO getStudentById(Long id) {
        return repository.findById(id)
                .map(this::toDTO)
                .orElseThrow(() -> new RuntimeException("Student not found with id: " + id));
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new RuntimeException("Student not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // 🔹 Convert Entity → DTO
    public StudentDTO toDTO(Student student) {
        if (student == null) return null;

        StudentDTO dto = new StudentDTO();
        dto.setId(student.getId());
        dto.setFirstName(student.getFirstName());
        dto.setLastName(student.getLastName());
        dto.setEmail(student.getEmail());
        dto.setDob(student.getDob());

        // Department mapping
        if (student.getDepartment() != null) {
            DepartmentDTO deptDTO = new DepartmentDTO();
            deptDTO.setId(student.getDepartment().getId());
            deptDTO.setName(student.getDepartment().getName());
            dto.setDepartmentDTO(deptDTO);
        }
        // Courses mapping
//        Set<Course> courses = courseRepository.findCoursesByStudentId(student.getId());
        if (student.getCourses() != null && !student.getCourses().isEmpty()) {
            Set<Long> courseIds = new HashSet<>(student.getCourses()
                    .stream()
                    .map(Course::getId)
                    .toList());
            dto.setCourseIds(courseIds);
        }

//        Set<Long> courseIds = courseRepository.findCourseIdsByStudentIdNative(student.getId());
//        if (courseIds != null && !courseIds.isEmpty()) {
//            dto.setCourseIds(courseIds);
//        }


        return dto;
    }

    // 🔹 Convert DTO → Entity
    public Student toEntity(StudentDTO dto) {
        if (dto == null) return null;

        Student student = new Student();
        student.setId(dto.getId());
        student.setFirstName(dto.getFirstName());
        student.setLastName(dto.getLastName());
        student.setEmail(dto.getEmail());
        student.setDob(dto.getDob());


        // Department lookup
        if (dto.getDepartmentDTO() != null) {
            Department dept = departmentRepository.findById(dto.getDepartmentDTO().getId())
                    .orElseThrow(() -> new RuntimeException("Department not found with id: " + dto.getDepartmentDTO().getId()));
            student.setDepartment(dept);
        }

        // Courses lookup
        if (dto.getCourseIds() != null) {
            Set<Course> courses = new HashSet<>(courseRepository.findAllById(dto.getCourseIds()));
            student.getCourses().clear();         // Clear old entries safely
            student.getCourses().addAll(courses);
        }

        return student;
    }
}
