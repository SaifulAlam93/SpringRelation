package com.abc.crud.repository;


import com.abc.crud.entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

    @Query("SELECT s FROM Student s LEFT JOIN FETCH s.courses WHERE s.id = :id")
    Optional<Student> findByIdWithCourses(@Param("id") Long id);

    @Query("SELECT DISTINCT s FROM Student s " +
            "LEFT JOIN FETCH s.courses " +
            "LEFT JOIN FETCH s.department")
    List<Student> findAllWithCoursesAndDept();
}
