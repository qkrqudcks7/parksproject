package com.example.parksproject.controller;

import com.example.parksproject.domain.User;
import com.example.parksproject.payload.InfoResponse;
import com.example.parksproject.repository.UserRepository;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @GetMapping("/user/info")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getMyInfo(userPrincipal.getId());
    }

    @PutMapping("/user/info")
    public ResponseEntity<?> modifyCurrentUser(@CurrentUser UserPrincipal userPrincipal,
                                               @Valid @RequestBody InfoResponse infoResponse) {
        return userService.modifyUserInfo(userPrincipal,infoResponse);
    }

    @GetMapping("/user/study")
    public ResponseEntity<?> getMyStudy(@CurrentUser UserPrincipal userPrincipal) {
        return userService.findMyStudy(userPrincipal.getId());
    }

    @GetMapping("/user/mystudy")
    public ResponseEntity<?> getCreatedStudy(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCreatedStudy(userPrincipal.getId());
    }
}
