package com.adammendak.core.scheduledJobs;

import lombok.extern.slf4j.Slf4j;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class LogEvery30Sec {

    @Scheduled(fixedRate = 30000)
    public void logEvery30Sec() {
        log.info("#### SCHEDULED JOB 30 sec");
    }
}
