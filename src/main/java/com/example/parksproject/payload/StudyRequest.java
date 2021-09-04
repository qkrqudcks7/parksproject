package com.example.parksproject.payload;

import com.example.parksproject.domain.Manager;
import com.example.parksproject.domain.Member;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class StudyRequest {

    private String path;

    private String title;

    private String shortDescription;

    private String image;

    private boolean recruiting;

    private boolean published;

    private boolean closed;
}
