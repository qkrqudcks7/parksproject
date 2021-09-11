package com.example.parksproject.controller;

import com.example.parksproject.domain.Category;
import com.example.parksproject.payload.CategoryRequest;
import com.example.parksproject.repository.CategoryRepository;
import com.example.parksproject.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class CategoryController {

    public final CategoryService categoryService;
    public final CategoryRepository categoryRepository;

    @PostMapping("/category/parent")
    public ResponseEntity<?> addParentCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return categoryService.makeParentCategory(categoryRequest);

    }

    @PostMapping("/category/child")
    public ResponseEntity<?> addChildCategory(@Valid @RequestBody CategoryRequest categoryRequest) {

        return categoryService.makeChildCategory(categoryRequest);

    }

    @GetMapping("/category/child/{name}")
    public ResponseEntity<?> findChildCategory(@PathVariable("name") String name) {
        return categoryService.findCategoryChildList(name);
    }

    @GetMapping("/category/parent")
    public ResponseEntity<?> findParentCategory() {
        return categoryService.findParentCategoryList();
    }

    @DeleteMapping("/category/parent/{id}")
    public void deleteById(@PathVariable("id") Long id) {
        categoryRepository.deleteById(id);
    }
}
