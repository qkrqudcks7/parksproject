package com.example.parksproject.controller;

import com.example.parksproject.payload.CategoryRequest;
import com.example.parksproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;

    @PostMapping("/category")
    public ResponseEntity<?> addCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return categoryService.makeParentCategory(categoryRequest);

    }

}
