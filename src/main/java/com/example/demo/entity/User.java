package com.example.demo.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table(name = "users")
@Getter
@Setter
public class User {
    @Id
    @GeneratedValue
    private Long id;

    private String name;
    private String email;

    @Column(name = "password_hash")
    private String passwordHash;

    private boolean active;
}

