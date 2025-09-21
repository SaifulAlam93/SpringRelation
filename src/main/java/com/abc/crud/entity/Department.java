package com.abc.crud.entity;


import com.abc.crud.config.BaseEntity;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.List;


@Data
@Entity
@EqualsAndHashCode(callSuper = true, onlyExplicitlyIncluded = true)
@Table(
        name = "department",
        uniqueConstraints = {
                @UniqueConstraint(name = "UK_DEPARTMENT_NAME", columnNames = {"name"}),
        }
)
public class Department extends BaseEntity {

//    @NotBlank(message = "Department name is required")
//    @Size(min = 2, max = 100)
    private String name;


    @OneToMany(mappedBy = "department", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    @JsonIgnoreProperties("department")
//    @ToString.Exclude
    private List<Student> students;
}

