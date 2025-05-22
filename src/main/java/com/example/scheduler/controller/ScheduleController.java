package com.example.scheduler.controller;

import com.example.scheduler.job.EmailSenderJob;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/scheduler")
public class ScheduleController {

    @Autowired
    private Scheduler scheduler;

    @PostMapping("/email")
    public String scheduleEmail(@RequestParam String to,
                                @RequestParam String subject,
                                @RequestParam String body,
                                @RequestParam String cronExpression) throws SchedulerException {

        JobDetail jobDetail = JobBuilder.newJob(EmailSenderJob.class)
                .withIdentity("emailJob", "group1")
                .usingJobData("to", to)
                .usingJobData("subject", subject)
                .usingJobData("body", body)
                .build();

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("emailTrigger", "group1")
                .withSchedule(CronScheduleBuilder.cronSchedule(cronExpression))
                .build();

        scheduler.scheduleJob(jobDetail, trigger);
        return "Email job scheduled successfully.";
    }
}
