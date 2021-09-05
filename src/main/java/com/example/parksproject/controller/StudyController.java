package com.example.parksproject.controller;

import com.example.parksproject.payload.StudyRequest;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class StudyController {

    public final StudyRepository studyRepository;
    public final StudyService studyService;

    @PostMapping("/study")
    public ResponseEntity<?> makeStudy (@CurrentUser UserPrincipal userPrincipal,
                                        @Valid @RequestBody StudyRequest studyRequest) {
        return studyService.makeStudy(studyRequest, userPrincipal);
    }
}
