package com.example.parksproject.payload;

import lombok.Data;

@Data
public class StudyRequest {

    private String path;

    private String title;

    private String shortDescription;

    private String longDescription;

    private String image;

    private boolean recruiting;

    private boolean published;

    private boolean closed;
}
