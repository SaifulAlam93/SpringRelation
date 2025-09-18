package com.abc.crud.entity;

import com.abc.crud.config.BaseEntity;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table(name = "users")
@Data
public class User extends BaseEntity {
    @Column(nullable = false, unique = true)
    private String username;

    @OneToOne(mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true,
            fetch = FetchType.LAZY)
    private Profile profile;

    // helper to link both sides
    public void setProfile(Profile profile){
        this.profile = profile;
        if(profile != null) profile.setUser(this);
    }
}