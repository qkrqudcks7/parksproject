package com.example.parksproject.service;

import com.example.parksproject.domain.*;
import com.example.parksproject.event.StudyCreatedEvent;
import com.example.parksproject.payload.StudyRequest;
import com.example.parksproject.payload.StudyResponse;
import com.example.parksproject.repository.*;
import com.example.parksproject.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
@Slf4j
public class StudyService {

    private final UserRepository userRepository;
    private final StudyRepository studyRepository;
    private final ApplicationEventPublisher eventPublisher;
    private final CategoryRepository categoryRepository;
    private final S3FileUploadService s3FileUploadService;

    public ResponseEntity<?> makeStudy(StudyRequest studyRequest, UserPrincipal userPrincipal, MultipartFile multipartFile) throws IOException {
        if (multipartFile != null) {
            studyRequest.setImage(s3FileUploadService.upload(multipartFile));
        }

        User u = userRepository.findById(userPrincipal.getId()).get();
        Category category = categoryRepository.findByName(studyRequest.getCategoryName());
        Category parent = categoryRepository.findByName(studyRequest.getParentCategoryName());

        StudyCategory studyCategory = StudyCategory.builder()
                .category(category).build();

        StudyCategory studyParentCategory = StudyCategory.builder()
                .category(parent).build();

        List<StudyCategory> studyCategories = new ArrayList<>();
        studyCategories.add(studyCategory);
        studyCategories.add(studyParentCategory);

        Manager manager = Manager.builder()
                .user(u).build();

        Study study = Study.builder()
                .path(studyRequest.getPath())
                .title(studyRequest.getTitle())
                .shortDescription(studyRequest.getShortDescription())
                .longDescription(studyRequest.getLongDescription())
                .image(studyRequest.getImage())
                .recruiting(studyRequest.isRecruiting())
                .published(true)
                .closed(studyRequest.isClosed())
                .location(studyRequest.getLocation())
                .maxMember(studyRequest.getMaxMember()).build();
        study.addManager(manager);
        study.addStudyCategory(studyCategories);
        studyCategory.addStudy(study);
        studyParentCategory.addStudy(study);
        manager.addStudy(study);
        studyRepository.save(study);
        eventPublisher.publishEvent(new StudyCreatedEvent(study));

        return ResponseEntity.ok("스터디 생성 완료");
    }

    public ResponseEntity<?> getOneBoard(Long id) {
        Study study = studyRepository.findById(id).get();
        StudyResponse studyResponse = new StudyResponse(study.getId(), study.getPath(), study.getTitle(), study.getShortDescription(), study.getLongDescription(), study.getImage(), study.getApplies(), study.getManagers(), study.getCategorys(), study.isRecruiting(), study.isPublished(), study.isClosed(), study.getMembersId(), study.getLocation(), study.getMaxMember());

        return new ResponseEntity<>(studyResponse, HttpStatus.OK);
    }

    public ResponseEntity<?> modifyStudy(Long id, StudyRequest studyRequest) {
        Study study = studyRepository.findById(id).get();
        study.modifyStudy(studyRequest);
        studyRepository.save(study);

        return ResponseEntity.ok("스터디 수정 완료");
    }
}
