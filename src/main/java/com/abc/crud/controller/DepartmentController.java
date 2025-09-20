package com.abc.crud.controller;

import com.abc.crud.dtos.DepartmentDTO;
import com.abc.crud.services.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    @PostMapping
    public ResponseEntity<DepartmentDTO> create(@RequestBody DepartmentDTO dto) {
        return service.saveDepartment(dto);
    }

    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll() {
        return service.getAllDepartments();
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getById(@PathVariable Long id) {
        return service.getDepartmentById(id);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        return service.deleteDepartment(id);
    }
}
