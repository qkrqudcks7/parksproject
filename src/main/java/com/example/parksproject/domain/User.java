package com.example.parksproject.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@Builder
public class User {

    @Id @GeneratedValue
    @Column(name = "user_id")
    private Long id;

    @Column(nullable = false)
    private String name;

    @Email
    @Column(nullable = false)
    private String email;

    @Column(nullable = true)
    private String imageUrl;

    @Column(nullable = true)
    private Role role;

    @Column(nullable = false)
    private final Boolean emailVerified = false;

    @JsonIgnore
    private String password;

    @NotNull
    @Enumerated(EnumType.STRING)
    private AuthProvider authProvider;

    @Column(nullable = true)
    private String providerId;

    @Column(nullable = true)
    private String bio;

    @Column(nullable = true)
    private String occupation;

    @Column(nullable = true)
    private String location;

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}
