package com.example.kele.component;

import lombok.extern.slf4j.Slf4j;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.scheduling.quartz.QuartzJobBean;

import java.util.Date;

/**
 * @author xukele
 * @TIME 2019/6/4 15:39
 * kele-practise & com.example.kele.component
 */
@Slf4j
public class TestMisson extends QuartzJobBean {

    @Override
    protected void executeInternal(JobExecutionContext context) throws JobExecutionException {

        log.info("===> test 定时任务,当前时间:{}", new Date());

    }
}
