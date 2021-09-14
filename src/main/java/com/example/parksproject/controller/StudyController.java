package com.example.parksproject.controller;

import com.example.parksproject.domain.Study;
import com.example.parksproject.payload.StudyRequest;
import com.example.parksproject.payload.StudyResponse;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequiredArgsConstructor
public class StudyController {

    public final StudyRepository studyRepository;
    public final StudyService studyService;

    @GetMapping("/allstudy")
    public ResponseEntity<?> getAllStudy() {
        List<Study> all = studyRepository.findAll();
        List<StudyResponse> collect = all.stream().map(
                study -> new StudyResponse(study.getId(), study.getPath(), study.getTitle(), study.getShortDescription(), study.getLongDescription(), study.getImage(),study.getApplies(),study.getManagers(), study.getCategorys(), study.isRecruiting(), study.isPublished(), study.isClosed(), study.getMembersId(), study.getLocation(), study.getMaxMember()))
                .collect(Collectors.toList());

        Collections.reverse(collect);
        List<StudyResponse> newOne = new ArrayList<>();
        for (StudyResponse i:collect) {
            if (i.isPublished()) {
                newOne.add(i);
            }
        }
        return new ResponseEntity<>(newOne, HttpStatus.OK);
    }

    @PostMapping("/study")
    public ResponseEntity<?> makeStudy (@CurrentUser UserPrincipal userPrincipal,
                                        @Valid StudyRequest studyRequest,
                                        MultipartFile multipartFile) throws IOException {
        return studyService.makeStudy(studyRequest, userPrincipal,multipartFile);
    }

    @GetMapping("/onestudy/{id}")
    public ResponseEntity<?> getOneStudy(@PathVariable("id") Long id) {
        return studyService.getOneBoard(id);
    }

    @DeleteMapping("/study/{id}")
    public void deleteStudy(@PathVariable("id") Long id) {
        studyRepository.deleteById(id);
    }
}
