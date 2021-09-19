package com.example.parksproject.domain;

import com.example.parksproject.payload.StudyResponse;
import lombok.*;
import net.minidev.json.annotate.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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

    @Column(nullable = true)
    private Boolean emailVerified = false;

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

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private final List<ApplyStudy> applyStudies = new ArrayList<>();

    public List<StudyResponse> getSupplyStudies() {
        return applyStudies.stream().map(applyStudy -> new StudyResponse(applyStudy.getStudy().getId(),applyStudy.getApplyState().toString(),applyStudy.getStudy().getTitle(), applyStudy.getStudy().getImage(), applyStudy.getStudy().isRecruiting(),applyStudy.getStudy().isPublished(),applyStudy.getStudy().isClosed())).collect(Collectors.toList());
    }

    public User update(String name, String imageUrl) {
        this.name = name;
        this.imageUrl = imageUrl;
        return this;
    }
}
