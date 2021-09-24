package com.example.parksproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class CreatedStudyResponse {

    private Long id;

    private String image;

    private String title;

    private int maxMember;

    private List<String> members;
}
