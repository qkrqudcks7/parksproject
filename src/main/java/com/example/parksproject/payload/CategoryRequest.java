package com.example.parksproject.payload;

import lombok.Data;

@Data
public class CategoryRequest {
    private String name;
    private String parentName;
    private String childName;
}
