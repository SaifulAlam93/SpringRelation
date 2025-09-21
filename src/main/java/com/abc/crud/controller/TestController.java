package com.abc.crud.controller;


import com.abc.crud.config.CustomApiResponse;
import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Department;
import com.abc.crud.services.StudentService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/test")
public class TestController {

    @Value("${profile}")
    private String message;


//    @GetMapping("/")
//    public String home() {
//        return "Message: " + message;
//    }
//    @PostMapping("/testData/{id}")
//    public String testApi(@PathVariable Long id,
//                          @RequestParam(name = "abc") String abcd,
//                          @RequestBody Department db) {
//        System.out.println("Id: " + id);
//        System.out.println("RequestParam: : " + abcd);
//        System.out.println(db.toString());
//        return "GotIt";
//    }

    private final StudentService studentService;

    public TestController(StudentService studentService) {
        this.studentService = studentService;
    }

    // ✅ Get single student
    @GetMapping("/{id}")
    public ResponseEntity<CustomApiResponse<StudentDTO>> getStudent(@PathVariable Long id) {
        StudentDTO student = studentService.getStudentById(id);
        if (student == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(false, "Student not found with ID: " + id, null));
        }
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Student fetched successfully", student));
    }

    // ✅ Get all students
    @GetMapping
    public ResponseEntity<CustomApiResponse<List<StudentDTO>>> getAllStudents() {
        List<StudentDTO> students = studentService.getAllStudents();
        return ResponseEntity.ok(new CustomApiResponse<>(true, "All students fetched successfully", students));
    }

    // ✅ Create student
    @PostMapping
    public ResponseEntity<CustomApiResponse<StudentDTO>> createStudent(@RequestBody StudentDTO dto) {
        StudentDTO saved = studentService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED)
                .body(new CustomApiResponse<>(true, "Student created successfully", saved));
    }

    // ✅ Update student
    @PutMapping("/{id}")
    public ResponseEntity<CustomApiResponse<StudentDTO>> updateStudent(@PathVariable Long id, @RequestBody StudentDTO dto) {

        dto.setId(id);
        StudentDTO updated = studentService.save(dto);
        if (updated == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body(new CustomApiResponse<>(false, "Student not found with ID: " + id, null));
        }
        return ResponseEntity.ok(new CustomApiResponse<>(true, "Student updated successfully", updated));
    }

    // ✅ Delete student
//    @DeleteMapping("/{id}")
//    public ResponseEntity<CustomApiResponse<Void>> deleteStudent(@PathVariable Long id) {
//        boolean deleted = studentService.deleteById(id);
//        if (!deleted) {
//            return ResponseEntity.status(HttpStatus.NOT_FOUND)
//                    .body(new CustomApiResponse<>(false, "Student not found with ID: " + id, null));
//        }
//        return ResponseEntity.ok(new CustomApiResponse<>(true, "Student deleted successfully", null));
//    }
}
