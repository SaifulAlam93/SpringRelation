package com.abc.crud.services;



import com.abc.crud.dtos.DtoMapper;
import com.abc.crud.dtos.StudentDTO;
import com.abc.crud.entity.Student;
import com.abc.crud.repository.StudentRepository;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class StudentService {

    private final StudentRepository repository;

    public StudentService(StudentRepository repository) {
        this.repository = repository;
    }

    public Student save(Student student) { return repository.save(student); }

    public List<Student> findAll() { return repository.findAll(); }

    public Optional<Student> findById(Long id) { return repository.findById(id); }

    public void deleteById(Long id) { repository.deleteById(id); }

//    public List<StudentDTO> getAllStudents() {
//        return studentRepo.findAll().stream()
//                .map(DtoMapper::toStudentDTO)
//                .toList();
//    }
//
//    public StudentDTO getStudentById(Long id) {
//        return studentRepo.findById(id)
//                .map(DtoMapper::toStudentDTO)
//                .orElseThrow(() -> new RuntimeException("Student not found"));
//    }


}
