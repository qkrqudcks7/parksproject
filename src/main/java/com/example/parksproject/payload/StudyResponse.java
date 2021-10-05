package com.example.parksproject.payload;

import lombok.Getter;
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

    private List<String> categorys;

    private boolean recruiting;

    private boolean published;

    private boolean closed;

    private String location;

    private int maxMember;

    public StudyResponse(Long id, String path, String title, String shortDescription, String longDescription, String image, List<String> members, List<String> managers, List<String> categorys, boolean recruiting, boolean published, boolean closed, String location, int maxMember) {
        this.id = id;
        this.path = path;
        this.title = title;
        this.shortDescription = shortDescription;
        this.longDescription = longDescription;
        this.image = image;
        this.members = members;
        this.managers = managers;
        this.categorys = categorys;
        this.recruiting = recruiting;
        this.published = published;
        this.closed = closed;
        this.location = location;
        this.maxMember = maxMember;
    }
}
