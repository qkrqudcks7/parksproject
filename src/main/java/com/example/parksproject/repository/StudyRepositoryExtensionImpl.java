package com.example.parksproject.repository;

import com.example.parksproject.domain.QStudy;
import com.example.parksproject.domain.QStudyCategory;
import com.example.parksproject.domain.Study;
import com.querydsl.core.types.dsl.NumberPath;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class StudyRepositoryExtensionImpl implements StudyRepositoryExtension{

    private final JPAQueryFactory queryFactory;

    public StudyRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Study> findByCategory() {
        QStudy study = QStudy.study;
        QStudyCategory studyCategory = QStudyCategory.studyCategory;

    }
}
