package com.example.parksproject.repository;

import com.example.parksproject.domain.Category;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    Category findByName(String name);

    List<Category> findByParent(Category category);

    List<Category> findByParentIsNull();
}
