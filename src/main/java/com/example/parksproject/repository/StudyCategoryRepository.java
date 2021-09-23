package com.example.parksproject.repository;

import com.example.parksproject.domain.StudyCategory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyCategoryRepository extends JpaRepository<StudyCategory, Long> {

    List<StudyCategory> findByCategoryId(Long id);
}
