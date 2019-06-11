package com.example.kele.config;

import com.example.kele.component.TestMisson;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.JobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * @author xukele
 * @TIME 2019/6/4 15:30
 * kele-practise & com.example.kele.config
 */
@Configuration
public class QuartzConfig {

    @Bean
    public JobDetailFactoryBean mission1() {

        JobDetailFactoryBean jobDetailFactoryBean = new JobDetailFactoryBean();

        jobDetailFactoryBean.setJobClass(TestMisson.class);

        return jobDetailFactoryBean;
    }

    @Bean
    public CronTriggerFactoryBean trigger1() {
        CronTriggerFactoryBean triggerFactoryBean = new CronTriggerFactoryBean();

        triggerFactoryBean.setJobDetail(mission1().getObject());

        triggerFactoryBean.setName("test-mission1");
        triggerFactoryBean.setGroup("group1");

        triggerFactoryBean.setCronExpression("2 0/30 * * * ?");
        return triggerFactoryBean;
    }

    @Bean
    public SchedulerFactoryBean schedulerFactoryBean() {

        SchedulerFactoryBean factoryBean = new SchedulerFactoryBean();
        factoryBean.setStartupDelay(10);
        factoryBean.setTriggers(trigger1().getObject());

        return factoryBean;
    }
}
