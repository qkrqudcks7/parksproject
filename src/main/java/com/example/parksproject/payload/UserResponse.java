package com.example.parksproject.payload;

import com.example.parksproject.domain.AuthProvider;
import com.example.parksproject.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserResponse implements Serializable {

    private Long id;

    private String name;

    private String email;

    private String imageUrl;

    private Role role;

    private Boolean emailVerified;

    private AuthProvider authProvider;

    private String providerId;

    private String bio;

    private String occupation;

    private String location;
}
