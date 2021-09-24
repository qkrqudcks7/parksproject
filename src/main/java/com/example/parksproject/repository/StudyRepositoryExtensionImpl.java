package com.example.parksproject.repository;

import com.example.parksproject.domain.QCategory;
import com.example.parksproject.domain.QStudy;
import com.example.parksproject.domain.Study;
import com.querydsl.core.QueryResults;
import com.querydsl.jpa.impl.JPAQueryFactory;

import java.util.List;

public class StudyRepositoryExtensionImpl implements StudyRepositoryExtension{

    private final JPAQueryFactory queryFactory;

    public StudyRepositoryExtensionImpl(JPAQueryFactory queryFactory) {
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Study> findByKeyword(String keyword) {
        QStudy study = QStudy.study;

        QueryResults<?> queryResults = queryFactory.from(study).where(study.title.contains(keyword).or(study.shortDescription.contains(keyword)).or(study.longDescription.contains(keyword))).fetchResults();
        return (List<Study>) queryResults.getResults();
    }
}
