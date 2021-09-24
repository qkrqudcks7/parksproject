package com.example.parksproject.service;

import com.example.parksproject.domain.Notification;
import com.example.parksproject.payload.NotificationResponse;
import com.example.parksproject.repository.NotificationRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class NotificationService {

    private final NotificationRepository notificationRepository;

    public ResponseEntity<?> getAlarm(Long id) {
        List<Notification> all = notificationRepository.findByUserId(id);

        List<NotificationResponse> collect = all.stream().map(
                notification -> new NotificationResponse(notification.getId(), notification.getTitle(), notification.getMessage(), notification.getNotificationType(), notification.getChecked(), notification.getLocalDateTime())
        ).collect(Collectors.toList());

        Collections.reverse(collect);

        return new ResponseEntity<>(collect, HttpStatus.OK);
    }
}
