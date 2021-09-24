package com.example.parksproject.payload;

import com.example.parksproject.domain.NotificationType;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@AllArgsConstructor
public class NotificationResponse {

    private Long id;

    private String title;

    private String message;

    private NotificationType notificationType;

    private Boolean checked;

    private LocalDateTime localDateTime;

}
