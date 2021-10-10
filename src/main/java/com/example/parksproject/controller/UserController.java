package com.example.parksproject.controller;

import com.example.parksproject.payload.CreatedStudyResponse;
import com.example.parksproject.payload.InfoResponse;
import com.example.parksproject.payload.MyStudyResponse;
import com.example.parksproject.payload.UserResponse;
import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @Cacheable(value = "users", key = "#userPrincipal.id", cacheManager = "cacheManager")
    @GetMapping("/user/info")
    @PreAuthorize("hasRole('USER')")
    public UserResponse getCurrentUser(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getMyInfo(userPrincipal.getId());
    }

    @PutMapping("/user/info")
    public ResponseEntity<?> modifyCurrentUser(@CurrentUser UserPrincipal userPrincipal,
                                               @Valid @RequestBody InfoResponse infoResponse) {
        return userService.modifyUserInfo(userPrincipal,infoResponse);
    }

    @Cacheable(value = "getMyStudy", key = "#userPrincipal.id", cacheManager = "cacheManager")
    @GetMapping("/user/study")
    public List<MyStudyResponse> getMyStudy(@CurrentUser UserPrincipal userPrincipal) {
        return userService.findMyStudy(userPrincipal.getId());
    }

    @Cacheable(value = "getCreatedStudy", key = "#userPrincipal.id", cacheManager = "cacheManager")
    @GetMapping("/user/mystudy")
    public List<CreatedStudyResponse> getCreatedStudy(@CurrentUser UserPrincipal userPrincipal) {
        return userService.getCreatedStudy(userPrincipal.getId());
    }
}
