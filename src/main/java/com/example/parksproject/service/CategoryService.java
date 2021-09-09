package com.example.parksproject.service;

import com.example.parksproject.domain.Category;
import com.example.parksproject.payload.CategoryRequest;
import com.example.parksproject.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
}
