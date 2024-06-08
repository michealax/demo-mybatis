package com.shane.mybatis.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Scheduled;

@Configuration
@Slf4j
public class CacheUpdater {

    @Scheduled(fixedRate = 5000000)
    public void runScheduleFixedRate(){
        log.info("update cache in fixed rate");
    }

    @Scheduled(cron = "* * 1 ? * *")
    public void runScheduleCron(){
        log.info("update cache every 5 seconds");
    }
}
