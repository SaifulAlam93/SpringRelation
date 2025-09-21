package com.abc.crud.entity;

import com.abc.crud.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import org.apache.commons.lang3.builder.ToStringExclude;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Data
@Entity
//@Table(
//        name = "student",
//        uniqueConstraints = {
//                @UniqueConstraint(name = "UK_EMAIL", columnNames = {"email"}),
//                @UniqueConstraint(name = "UK_NAME", columnNames = {"first_name", "last_name"})
//        }
//)
public class Student extends BaseEntity {

//    @NotBlank(message = "First name is required")
//    @Size(min = 2, max = 50, message = "First name must be 2-50 characters")
    private String firstName;

//    @NotBlank(message = "Last name is required")
//    @Size(min = 2, max = 50, message = "Last name must be 2-50 characters")
    private String lastName;

//    @NotBlank(message = "Email is required")
//    @Email(message = "Email should be valid")
//    @Column(unique = true)
    private String email;

//    @Past(message = "Date of birth must be in the past")
    private LocalDate dob;  // Date of Birth

    @ManyToOne
    @JoinColumn(name = "department_id")
//    @JsonIgnoreProperties("students")   // important for JSON serialization
    @ToString.Exclude
    private Department department;


    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "student_course",
            joinColumns = @JoinColumn(name = "student_id"),
            inverseJoinColumns = @JoinColumn(name = "course_id")
    )
    @ToString.Exclude
    private Set<Course> courses = new HashSet<>();
}
