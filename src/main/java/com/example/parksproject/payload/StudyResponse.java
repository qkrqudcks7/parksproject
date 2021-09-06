package com.example.parksproject.payload;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class StudyResponse {

    private Long id;

    private String path;

    private String title;

    private String shortDescription;

    private String longDescription;

    private String image;

    private List<String> members;

    private List<String> managers;

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    public StudyResponse(Long id, String path, String title, String shortDescription, String longDescription, String image, List<String> members, List<String> managers, boolean recruiting, boolean published, boolean closed) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.image = image;
        this.members = members;
        this.managers = managers;
        this.recruiting = recruiting;
        this.published = published;
        this.closed = closed;
    }
}
