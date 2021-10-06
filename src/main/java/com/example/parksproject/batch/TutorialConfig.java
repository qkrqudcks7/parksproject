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

import java.util.List;

@Slf4j
@Configuration
@RequiredArgsConstructor
public class TutorialConfig {

    public static final String JOB_NAME = "transactionProcessorBatch";
    public static final String BEAN_PREFIX = JOB_NAME + "_";
    private final StudyRepository studyRepository;

    @Bean(JOB_NAME)
    public Job closedStudyJob(JobBuilderFactory jobBuilderFactory, Step closedStudyStep) {
        log.info("************ this is closedStudyJob");
        return jobBuilderFactory.get("closedStudyJob")
                .preventRestart()
                .start(closedStudyStep)
                .build();
    }

    @Bean(BEAN_PREFIX + "step")
    @JobScope
    public Step closedStudyStep(StepBuilderFactory stepBuilderFactory) {
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
        List<Study> byClosedIsFalse = studyRepository.findByClosedIsFalse();
        log.info("************* byClosedIsFalse Size :" + byClosedIsFalse.size());

        return new ListItemReader<>(byClosedIsFalse);
    }

    @Bean
    public ItemProcessor<Study, Study> closedStudyProcessor() {
        return study -> {
            log.info("********************************");
            return study.setClosed();
        };
    }

    public ItemWriter<Study> closedStudyWriter() {
        log.info("********** This is closedStudyWriter");
        return (studyRepository::saveAll);
    }
}
