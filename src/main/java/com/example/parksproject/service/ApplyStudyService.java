package com.example.parksproject.service;

import com.example.parksproject.domain.ApplyState;
import com.example.parksproject.domain.ApplyStudy;
import com.example.parksproject.domain.Study;
import com.example.parksproject.domain.User;
import com.example.parksproject.payload.ApplyStudyRequest;
import com.example.parksproject.payload.ApplyStudyResponse;
import com.example.parksproject.payload.StudyRequest;
import com.example.parksproject.repository.ApplyStudyRepository;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.repository.UserRepository;
import com.example.parksproject.security.UserPrincipal;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class ApplyStudyService {

    private final ApplyStudyRepository applyStudyRepository;
    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    public ResponseEntity<?> makeApply(ApplyStudyRequest applyStudyRequest, Long id, UserPrincipal userPrincipal) {
        User u = userRepository.findById(userPrincipal.getId()).get();
        Study s = studyRepository.findById(id).get();

        ApplyStudy applyStudy = ApplyStudy.builder()
                .study(s)
                .user(u)
                .message(applyStudyRequest.getMessage())
                .applyState(ApplyState.WAITING)
                .build();

        applyStudyRepository.save(applyStudy);

        return ResponseEntity.ok("신청이 완료되었습니다.");
    }

    public ResponseEntity<?> getThisApply(Long id) {
        Study study = studyRepository.findById(id).get();

        List<ApplyStudy> applyStudies = applyStudyRepository.findByStudyId(study.getId());

        List<ApplyStudyResponse> applyStudyResponse = applyStudies.stream().map(
                apply -> new ApplyStudyResponse(apply.getId(), apply.getApplyState(), apply.getUser().getId(), apply.getMessage())
        ).collect(Collectors.toList());


        return ResponseEntity.ok(applyStudyResponse);
    }
}
