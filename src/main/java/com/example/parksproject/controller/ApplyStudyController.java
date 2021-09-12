package com.example.parksproject.controller;

import com.example.parksproject.payload.ApplyStudyRequest;
import com.example.parksproject.repository.ApplyStudyRepository;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.ApplyStudyService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class ApplyStudyController {

    private final ApplyStudyRepository applyStudyRepository;
    private final ApplyStudyService applyStudyService;

    @PostMapping("/apply/{id}")
    public ResponseEntity<?> apply(@CurrentUser UserPrincipal userPrincipal,
                                   @Valid @RequestBody ApplyStudyRequest applyStudyRequest,
                                   @PathVariable("id") Long id) {
        return applyStudyService.makeApply(applyStudyRequest,id,userPrincipal);
    }

    @GetMapping("/apply/{id}")
    public ResponseEntity<?> getStudyApplies(@PathVariable("id") Long id) {
        return applyStudyService.getThisApply(id);
    }
}
