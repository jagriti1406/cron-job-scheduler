# 📧 Quartz Email Scheduler (Spring Boot)

A lightweight Spring Boot app using Quartz Scheduler to send automated emails based on a cron expression. No manual trigger needed — the job runs automatically on startup.

---

## 🚀 Features

- Cron-based automatic email scheduler
- Quartz + Spring Boot integration
- Configurable SMTP via `application.properties`
- Mailtrap or Gmail support
- Console logs to track job execution

---

## 🛠 Setup Instructions

### 1. Clone and Open the Project

```bash
git clone https://github.com/jagriti1406/quartz-email-scheduler.git
cd quartz-email-scheduler
```

Open the project in IntelliJ or your preferred IDE.

---

### 2. Configure Email Settings

#### ✅ Option A: Gmail (App Password required)

```properties
spring.mail.host=smtp.gmail.com
spring.mail.port=587
spring.mail.username=your_email@gmail.com
spring.mail.password=your_16_char_app_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

➡️ Enable 2FA in Gmail and generate an App Password here:  
[https://myaccount.google.com/apppasswords](https://myaccount.google.com/apppasswords)

#### ✅ Option B: Mailtrap (for testing)

```properties
spring.mail.host=smtp.mailtrap.io
spring.mail.port=2525
spring.mail.username=your_mailtrap_username
spring.mail.password=your_mailtrap_password
spring.mail.properties.mail.smtp.auth=true
spring.mail.properties.mail.smtp.starttls.enable=true
```

➡️ Get credentials from [https://mailtrap.io](https://mailtrap.io)

---

### 3. Run the Application

#### ✅ Using IntelliJ
- Right-click `QuartzEmailSchedulerApplication.java` → **Run**

#### ✅ Using Terminal
```bash
./mvnw spring-boot:run
```

The job is scheduled on application startup — no manual triggering needed.

---

## ⏱ Cron Expression Examples

| Expression              | Description                |
|--------------------------|----------------------------|
| `0/30 * * * * ?`        | Every 30 seconds           |
| `0 0 * * * ?`           | Every hour                 |
| `0 0 9 * * ?`           | Every day at 9 AM          |
| `0 0/15 * * * ?`        | Every 15 minutes           |
| `0 0 12 ? * MON-FRI`    | Weekdays at 12 PM          |
| `0 0 0 1 * ?`           | On the 1st of every month  |

💡 Quartz uses a 6-field cron format:  
`second minute hour day-of-month month day-of-week`

Use this tool to build your own:  
[https://www.freeformatter.com/cron-expression-generator-quartz.html](https://www.freeformatter.com/cron-expression-generator-quartz.html)

---

## 📊 Example Logs

```
📧 Quartz Job Triggered at: Tue May 21 15:30:00
Sending email to: test@example.com
✅ Email sent successfully
```

---

## 🧩 Extend This Project

- Add job persistence with a database (`spring.quartz.job-store-type=jdbc`)
- Support multiple job types (email, SMS, Slack notifications)
- Build a REST API or admin UI for scheduling on demand
- Add retry logic or failure notifications
