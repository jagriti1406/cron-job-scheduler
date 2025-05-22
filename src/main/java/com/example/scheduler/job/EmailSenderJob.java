package com.example.scheduler.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.logging.Logger;

@Component
public class EmailSenderJob implements Job {

    @Autowired
    private JavaMailSender mailSender;

    private static final Logger logger = Logger.getLogger(EmailSenderJob.class.getName());

    @Override
    public void execute(JobExecutionContext context) throws JobExecutionException {
        String to = context.getMergedJobDataMap().getString("to");
        String subject = context.getMergedJobDataMap().getString("subject");
        String body = context.getMergedJobDataMap().getString("body");

        logger.info("Quartz Job Triggered at: " + new Date());
        logger.info("Sending email to: " + to);
        logger.info("Subject: " + subject);
        logger.info("Body: " + body);

        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            logger.info("Email sent successfully at " + new Date());
        } catch (Exception e) {
            logger.severe(" Failed to send email: " + e.getMessage());
            throw new JobExecutionException(e);
        }
    }
}
