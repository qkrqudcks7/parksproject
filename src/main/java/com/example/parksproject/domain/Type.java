package com.example.parksproject.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@Getter
@AllArgsConstructor @NoArgsConstructor
public class Type {

    @Id @GeneratedValue
    @Column(name = "type_id")
    private Long id;

    @OneToMany(mappedBy = "type")
    private List<CategoryType> categoryTypes = new ArrayList<>();

    private String name;

}
