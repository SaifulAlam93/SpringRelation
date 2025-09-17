package com.abc.crud.services;


import com.abc.crud.entity.Department;
import com.abc.crud.repository.DepartmentRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class DepartmentService {

    private final DepartmentRepository repository;

    public DepartmentService(DepartmentRepository repository) {
        this.repository = repository;
    }

    public Department save(Department dept) {
        return repository.save(dept);
    }

    public List<Department> findAll() {
        return repository.findAll();
    }

    public Optional<Department> findById(Long id) {
        return repository.findById(id);
    }

    public void deleteById(Long id) {
        repository.deleteById(id);
    }

//    public List<DepartmentDTO> getAllDepartments() {
//        return departmentRepo.findAll().stream()
//                .map(DtoMapper::toDepartmentDTO)
//                .toList();
//    }
//
//    public DepartmentDTO getDepartmentById(Long id) {
//        return departmentRepo.findById(id)
//                .map(DtoMapper::toDepartmentDTO)
//                .orElseThrow(() -> new RuntimeException("Department not found"));
//    }
}
