package com.example.parksproject.batch;

import com.example.parksproject.domain.Study;
import com.example.parksproject.repository.StudyRepository;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.support.ListItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class StudyJobConfig {

    private final StudyRepository studyRepository;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;

    @Bean
    public Job closedStudyJob() {
        log.info("************ this is closedStudyJob");
        return jobBuilderFactory.get("closedStudyJob")
                .start(closedStudyStep())
                .build();
    }

    @Bean
    public Step closedStudyStep() {
        log.info("********** this is closedStudyStep");
        return stepBuilderFactory.get("closedStudyStep")
                .<Study, Study> chunk(10)
                .reader(closedStudyReader())
                .processor(closedStudyProcessor())
                .writer(closedStudyWriter())
                .build();
    }

    @Bean
    public ListItemReader<Study> closedStudyReader() {
        log.info("************ this is closedStudyReader");
        List<Study> byClosedIsFalse = studyRepository.findByClosedIsTrue();
        log.info("************* byClosedIsFalse Size :" + byClosedIsFalse.size());

        return new ListItemReader<>(byClosedIsFalse);
    }

    @Bean
    public ItemProcessor<Study, Study> closedStudyProcessor() {
        return study -> {
            log.info("********************************");
            return study.setOpen();
        };
    }

    public ItemWriter<Study> closedStudyWriter() {
        log.info("********** This is closedStudyWriter");
        return (studyRepository::saveAll);
    }
}
