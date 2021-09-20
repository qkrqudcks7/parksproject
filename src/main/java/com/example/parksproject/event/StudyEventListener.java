package com.example.parksproject.event;

import com.example.parksproject.domain.Study;
import com.example.parksproject.repository.StudyRepository;
import com.example.parksproject.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

@Component
@Transactional
@Slf4j
@Async
@RequiredArgsConstructor
public class StudyEventListener {

    private final StudyRepository studyRepository;
    private final UserRepository userRepository;

    @EventListener
    public void handleStudyCreatedEvent(StudyCreatedEvent studyCreatedEvent) {
        Study study = studyRepository.findById(studyCreatedEvent.getStudy().getId()).get();

        log.info(String.valueOf(study.getId()) + "111");
    }
}
