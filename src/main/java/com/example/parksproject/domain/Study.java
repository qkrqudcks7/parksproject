package com.example.parksproject.domain;

import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Builder @AllArgsConstructor @NoArgsConstructor
public class Study {

    @Id @GeneratedValue
    @Column(name = "study_id")
    private Long id;

    @Column(unique = true)
    private String path;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String shortDescription;

    @Lob @Basic(fetch = FetchType.EAGER)
    private String image;

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private final List<Member> members = new ArrayList<>();

    @OneToMany(mappedBy = "study", cascade = CascadeType.ALL)
    private final List<Manager> managers = new ArrayList<>();

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    public void addManager(Manager manager) {
        this.managers.add(manager);
    }

    public void addMember(Member member) {
        this.members.add(member);
    }



}
