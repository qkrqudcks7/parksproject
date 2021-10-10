package com.example.parksproject.controller;

import com.example.parksproject.domain.Study;
import com.example.parksproject.payload.StudyRequest;
import com.example.parksproject.payload.StudyResponse;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.StudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
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

        return studyService.findAll();
    }

    @GetMapping("/sixstudy")
    public ResponseEntity<?> getSixStudy() {
        return studyService.findSixStudy();
    }

    @PostMapping("/study")
    public ResponseEntity<?> makeStudy (@CurrentUser UserPrincipal userPrincipal,
                                        @Valid StudyRequest studyRequest,
                                        MultipartFile multipartFile) throws IOException {
        return studyService.makeStudy(studyRequest, userPrincipal,multipartFile);
    }

    @PutMapping("/study/{id}")
    public ResponseEntity<?> modifyStudy(@PathVariable("id") Long id,
                                         @Valid @RequestBody StudyRequest studyRequest) {
        return studyService.modifyStudy(id,studyRequest);
    }

    @GetMapping("/onestudy/{id}")
    @Cacheable(value = "study", key = "#id", cacheManager = "cacheManager")
    public ResponseEntity<?> getOneStudy(@PathVariable("id") Long id) {
        return studyService.getOneBoard(id);
    }

    @DeleteMapping("/study/{id}")
    public void deleteStudy(@PathVariable("id") Long id) {
        studyRepository.deleteById(id);
    }

    @GetMapping("/studybycategory/{name}")
    public ResponseEntity<?> getStudyByCategory(@PathVariable("name") String name) {
        return studyService.getStudyByCategory(name);
    }

    @GetMapping("/searchstudy/{keyword}")
    public ResponseEntity<?> searchStudy(@PathVariable("keyword") String keyword) {
        return studyService.searchStudy(keyword);

    }
}
