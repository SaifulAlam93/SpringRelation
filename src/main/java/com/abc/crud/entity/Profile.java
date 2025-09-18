package com.abc.crud.entity;

import com.abc.crud.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "profiles")
@Data
public class Profile extends BaseEntity {
    private String firstName;
    private String lastName;


    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

}