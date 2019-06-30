package com.example.kele.schedule;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class TestMission2 {

   // @Scheduled(cron = "0/4 * * * * *")
    public void send() {
        log.info("===> Java定时任务。。。4s一次");
    }

}
