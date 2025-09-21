package com.abc.crud.services;


import com.abc.crud.config.ResourceNotFoundException;
import com.abc.crud.dtos.DepartmentDTO;
import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Department;
import com.abc.crud.repository.DepartmentRepository;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.TransactionSystemException;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.MethodArgumentNotValidException;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

//    @Transactional
//    public ResponseEntity<?> saveDepartment2(DepartmentDTO dto) {
//        try {
//            Optional<Department> existing = repository.findByName(dto.getName());
//            if (existing.isPresent()) {
//                return ResponseEntity.status(HttpStatus.CONFLICT)
//                        .body("Department already exists with name: " + dto.getName());
//            }
//
//            Department saved = repository.save(toEntity(dto));
//            return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
//
//        } catch (Exception e) {
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to create department: " + e.getMessage());
//        }
//    }


//    @Transactional
//    public ResponseEntity<?> saveDepartment(DepartmentDTO dto) {
//        try {
//            Department saved = repository.save(toEntity(dto));
//            return ResponseEntity.status(HttpStatus.CREATED).body(toDTO(saved));
//        } catch (DataIntegrityViolationException e) {
//            return ResponseEntity.status(HttpStatus.CONFLICT)
//                    .body("Department already exists with name: " + dto.getName());
//        } catch (Exception e) {
//            // Any other runtime exception
//            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
//                    .body("Failed to create department: " + e.getMessage());
//        }
//    }


    // ---------- Save Department ----------
    @Transactional
    public DepartmentDTO saveDepartment(DepartmentDTO dto) {
        Department saved = repository.save(toEntity(dto));
        return toDTO(saved);
    }

    // ---------- Get All Departments ----------
    public List<DepartmentDTO> getAllDepartments() {
        return repository.findAll().stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }

    // ---------- Get Department by ID ----------
    public DepartmentDTO getDepartmentById(Long id) {
        Department dept = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Department not found with id: " + id));
        return toDTO(dept);
    }

    // ---------- Delete Department ----------
    @Transactional
    public void deleteDepartment(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Department not found with id: " + id);
        }
        repository.deleteById(id);
    }

    // ---------- DTO Mappers ----------
    private DepartmentDTO toDTO(Department department) {
        if (department == null) return null;
        DepartmentDTO dto = new DepartmentDTO();
        dto.setId(department.getId());
        dto.setName(department.getName());

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

    private Department toEntity(DepartmentDTO dto) {
        if (dto == null) return null;
        Department dept = new Department();
        dept.setId(dto.getId());
        dept.setName(dto.getName());
        return dept;
    }
}





