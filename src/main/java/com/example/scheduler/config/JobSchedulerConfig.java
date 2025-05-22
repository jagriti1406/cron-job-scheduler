package com.example.scheduler.config;

import com.example.scheduler.job.EmailSenderJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import jakarta.annotation.PostConstruct;

@Configuration
public class JobSchedulerConfig {

    @Autowired
    private Scheduler scheduler;

    @PostConstruct
    public void scheduleJobOnStartup() throws SchedulerException {
        JobDetail jobDetail = JobBuilder.newJob(EmailSenderJob.class)
                .withIdentity("emailJob", "group1")
                .usingJobData("to", "your_email@gmail.com")
                .usingJobData("subject", "Automated Reminder")
                .usingJobData("body", "This is an automated Quartz job triggered by cron!")
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("emailTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule("0/30 * * * * ?"))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
    }
}
