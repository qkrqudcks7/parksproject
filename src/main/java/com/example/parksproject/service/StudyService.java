package com.example.parksproject.service;

import com.example.parksproject.domain.Manager;
import com.example.parksproject.domain.Member;
import com.example.parksproject.domain.Study;
import com.example.parksproject.domain.User;
import com.example.parksproject.payload.StudyRequest;
import com.example.parksproject.repository.ManagerRepository;
import com.example.parksproject.repository.MemberRepository;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class StudyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final ManagerRepository managerRepository;
    private final MemberRepository memberRepository;

    public String study(StudyRequest studyRequest, Long id) {
        User u = userRepository.findById(id).get();

        Manager manager = Manager.builder()
                .user(u).build();

        Study study = Study.builder()
                .path(studyRequest.getPath())
                .title(studyRequest.getTitle())
                .shortDescription(studyRequest.getShortDescription())
                .image(studyRequest.getImage())
                .recruiting(studyRequest.isRecruiting())
                .published(studyRequest.isPublished())
                .closed(studyRequest.isClosed()).build();
        study.addManager(manager);
        return "스터디 생성 완료";
    }
}
