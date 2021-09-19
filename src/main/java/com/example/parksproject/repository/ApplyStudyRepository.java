package com.example.parksproject.repository;

import com.example.parksproject.domain.ApplyStudy;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ApplyStudyRepository extends JpaRepository<ApplyStudy, Long> {

    List<ApplyStudy> findByStudyId(Long id);

}
