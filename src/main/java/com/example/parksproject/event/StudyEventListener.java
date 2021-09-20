package com.example.parksproject.event;

import com.example.parksproject.domain.Notification;
import com.example.parksproject.domain.NotificationType;
import com.example.parksproject.domain.Study;
import com.example.parksproject.domain.User;
import com.example.parksproject.repository.NotificationRepository;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

@Component
@Transactional
@Slf4j
@Async
@RequiredArgsConstructor
public class StudyEventListener {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;
    private final NotificationRepository notificationRepository;

    @EventListener
    public void handleStudyCreatedEvent(StudyCreatedEvent studyCreatedEvent) {
        User u = userRepository.findById(studyCreatedEvent.getStudy().getManagerId()).get();

        Notification notification = Notification.builder()
                .title(studyCreatedEvent.getStudy().getTitle())
                .message(studyCreatedEvent.getStudy().getShortDescription())
                .user(u)
                .notificationType(NotificationType.STUDY_CREATED)
                .checked(false)
                .localDateTime(LocalDateTime.now()).build();

        notificationRepository.save(notification);
    }
}
