package com.example.parksproject.service;

import com.example.parksproject.domain.Category;
import com.example.parksproject.payload.CategoryRequest;
import com.example.parksproject.payload.CategoryResponse;
import com.example.parksproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class CategoryService {

    private final CategoryRepository categoryRepository;

    public ResponseEntity<?> makeParentCategory(CategoryRequest categoryRequest){
        Category category = Category.builder()
                .name(categoryRequest.getName()).build();
        categoryRepository.save(category);

        return ResponseEntity.ok("만들기 성공");
    }

    public ResponseEntity<?> makeChildCategory(CategoryRequest categoryRequest) {
        Category category = categoryRepository.findByName(categoryRequest.getName());

        Category child = Category.builder()
                .name(categoryRequest.getChildName())
                .parent(category).build();
        category.addChildCategory(child);
        categoryRepository.save(child);

        return ResponseEntity.ok("성공");
    }

    public ResponseEntity<?> findCategoryChildList(String name) {
        Category category = categoryRepository.findByName(name);

        List<Category> byParent = categoryRepository.findByParent(category);

        List<CategoryResponse> response = byParent.stream()
                .map(parent -> new CategoryResponse(parent.getName())).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }

    public ResponseEntity<?> findParentCategoryList() {
        List<Category> byParentIsNull = categoryRepository.findByParentIsNull();

        List<CategoryResponse> response = byParentIsNull.stream()
                .map(parent -> new CategoryResponse(parent.getName())).collect(Collectors.toList());

        return ResponseEntity.ok(response);
    }
}
