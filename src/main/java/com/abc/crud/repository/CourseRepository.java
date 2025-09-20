package com.abc.crud.repository;

import com.abc.crud.entity.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Set;


@Repository
public interface CourseRepository extends JpaRepository<Course, Long> {

    @Query("SELECT c FROM Student s JOIN s.courses c WHERE s.id = :studentId")
    Set<Course> findCoursesByStudentId(Long studentId);

    // Optional: Using native SQL
    @Query(
            value = "SELECT c.* FROM course c " +
                    "JOIN student_course sc ON c.id = sc.course_id " +
                    "WHERE sc.student_id = :studentId",
            nativeQuery = true
    )
    Set<Course> findCoursesByStudentIdNative(@Param("studentId") Long studentId);


    // JPQL version
    @Query("SELECT c.id FROM Student s JOIN s.courses c WHERE s.id = :studentId")
    Set<Long> findCourseIdsByStudentId(@Param("studentId") Long studentId);

    // Native SQL version
    @Query(value = "SELECT sc.course_id FROM student_course sc " +
            "WHERE sc.student_id = :studentId", nativeQuery = true)
    Set<Long> findCourseIdsByStudentIdNative(@Param("studentId") Long studentId);
}
