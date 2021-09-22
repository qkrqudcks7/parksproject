package com.example.parksproject.repository;

import com.example.parksproject.domain.Study;

import java.util.List;

public interface StudyRepositoryExtension {

    List<Study> findByCategory();
}
