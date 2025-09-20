package com.abc.crud.services;

import com.abc.crud.dtos.DepartmentDTO;
import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Department;
import com.abc.crud.entity.Student;
import com.abc.crud.repository.DepartmentRepository;
import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    // ---------- Save Department ----------
    public ResponseEntity<DepartmentDTO> saveDepartment(DepartmentDTO dto) {
        Department dept = toEntity(dto);
        Department saved = repository.save(dept);
        return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
    }

    // ---------- Get All Departments ----------
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        List<DepartmentDTO> dtos = repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
        return ResponseEntity.ok(dtos);
    }

    // ---------- Get Department by ID ----------
    public ResponseEntity<DepartmentDTO> getDepartmentById(Long id) {
        Optional<Department> optional = repository.findById(id);
        if (optional.isEmpty()) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(toDTO(optional.get()));
    }

    // ---------- Delete Department ----------
    public ResponseEntity<Void> deleteDepartment(Long id) {
        if (!repository.existsById(id)) {
            return ResponseEntity.notFound().build();
        }
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }

    // ---------- DTO Mappers ----------
    public DepartmentDTO toDTO(Department department) {
        if (department == null) return null;
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());

        // Map students to StudentDTO (summary)
        if (department.getStudents() != null) {
            List<StudentDTO> students = department.getStudents().stream().map(s -> {
                StudentDTO studentDTO = new StudentDTO();
                studentDTO.setId(s.getId());
                studentDTO.setFirstName(s.getFirstName());
                studentDTO.setLastName(s.getLastName());
                studentDTO.setEmail(s.getEmail());
                return studentDTO;
            }).collect(Collectors.toList());
            dto.setStudents(students);
        }

        return dto;
    }

    public Department toEntity(DepartmentDTO dto) {
        if (dto == null) return null;
        Department dept = new Department();
        dept.setId(dto.getId());
        dept.setName(dto.getName());
        // Note: We do not set students here to avoid overriding existing ones
        return dept;
    }
}
