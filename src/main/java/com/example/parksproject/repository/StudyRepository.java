package com.example.parksproject.repository;

import com.example.parksproject.domain.Study;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StudyRepository extends JpaRepository<Study,Long>, StudyRepositoryExtension {

    List<Study> findByClosedIsFalse();
    List<Study> findByClosedIsTrue();
}
