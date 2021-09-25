package com.example.parksproject.controller;

import com.example.parksproject.security.CurrentUser;
import com.example.parksproject.security.UserPrincipal;
import com.example.parksproject.service.NotificationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class NotificationController {

    private final NotificationService notificationService;

    @GetMapping("/notification")
    public ResponseEntity<?> getAlarm(@CurrentUser UserPrincipal userPrincipal) {

        return notificationService.getAlarm(userPrincipal.getId());
    }

    @PutMapping("/notification/{id}")
    public ResponseEntity<?> clickAlarm(@PathVariable("id") Long id) {
        return notificationService.clickAlarm(id);
    }
}
