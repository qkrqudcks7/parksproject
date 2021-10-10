package com.example.parksproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class CreatedStudyResponse implements Serializable {

    private Long id;

    private String image;

    private String title;

    private int maxMember;

    private List<String> members;
}
