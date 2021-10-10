package com.example.parksproject.payload;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@AllArgsConstructor
public class MyStudyResponse implements Serializable {

    private Long id;

    private String state;

    private String title;

    private String image;

    private boolean recruiting;

    private boolean published;

    private boolean closed;
}
