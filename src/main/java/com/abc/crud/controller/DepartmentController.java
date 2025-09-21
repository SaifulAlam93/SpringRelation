package com.abc.crud.controller;

import com.abc.crud.dtos.DepartmentDTO;
import com.abc.crud.services.DepartmentService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/departments")
public class DepartmentController {

    private final DepartmentService service;

    public DepartmentController(DepartmentService service) {
        this.service = service;
    }

    // ---------- Create Department ----------
    @PostMapping
    public ResponseEntity<DepartmentDTO> createDepartment(@RequestBody DepartmentDTO dto) {
        DepartmentDTO saved = service.saveDepartment(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }
//    @PostMapping
//    public ResponseEntity<?> createDepartment(@RequestBody DepartmentDTO dto) {
//        return service.saveDepartment1(dto);
//    }

    // ---------- Get All ----------
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAllDepartments() {
        return ResponseEntity.ok(service.getAllDepartments());
    }

    // ---------- Get by ID ----------
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable Long id) {
        DepartmentDTO dto = service.getDepartmentById(id);
        return ResponseEntity.ok(dto);
    }

    // ---------- Delete ----------
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteDepartment(@PathVariable Long id) {
        service.deleteDepartment(id);
        return ResponseEntity.noContent().build();
    }
}
