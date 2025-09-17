package com.abc.crud.controller;



import com.abc.crud.entity.Student;
import com.abc.crud.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController {

    private final StudentService service;

    public StudentController(StudentService service) { this.service = service; }

    @PostMapping
    public Student create(@Valid @RequestBody Student student) {
        return service.save(student);
    }

    @GetMapping
    public List<Student> getAll() { return service.findAll(); }

    @GetMapping("/{id}")
    public Student getById(@PathVariable Long id) {
        return service.findById(id).orElse(null);
    }

    @PutMapping("/{id}")
    public Student update(@PathVariable Long id, @Valid @RequestBody Student student) {
        student.setId(id);
        return service.save(student);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { service.deleteById(id); }
}
