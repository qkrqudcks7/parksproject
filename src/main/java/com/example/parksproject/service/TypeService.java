package com.example.parksproject.service;

import com.example.parksproject.repository.CategoryRepository;
import com.example.parksproject.repository.TypeRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class TypeService {

    private final TypeRepository typeRepository;
    private final CategoryRepository categoryRepository;



}
