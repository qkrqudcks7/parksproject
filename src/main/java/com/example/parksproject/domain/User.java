package com.example.parksproject.domain;

import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

@Entity
@Getter
@AllArgsConstructor @NoArgsConstructor
@EqualsAndHashCode(of = "id")
@Builder
public class User {

    @Id @GeneratedValue
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

}
