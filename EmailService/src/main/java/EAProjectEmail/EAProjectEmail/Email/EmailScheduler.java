package EAProjectEmail.EAProjectEmail.Email;

import EAProjectEmail.EAProjectEmail.Data.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class EmailScheduler {

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    private CourseRepository courseRepository;

    @Autowired
    private JmsTemplate jmsTemplate;

    //RegistrationEndTime USED FOR DEVELOPING
    //LocalDateTime RegistrationEndTime = LocalDateTime.of(2024,6,19,14,20);
    int NumberOfHoursReminder1 = 4;
    int NumberOfHoursReminder2 = 8;

    List<String> studentEmails = Arrays.asList("abdallahgaber15@outlook.com", "aelsharawy@miu.edu");



    //@Scheduled(fixedRate = 60000) // Check every minute for simplicity
    @Scheduled(cron = "0 * * * * *") //Run every minute
    public void sendReminders() {
        List<CourseOffering> courseOfferings = courseOfferingRepository.findAll();
        List<String> coursesReminder1 = new ArrayList<String>();
        List<String> coursesReminder2 = new ArrayList<String>();
        LocalDateTime now = LocalDateTime.now().withSecond(0).withNano(0);
        for (CourseOffering offering : courseOfferings) {
            LocalDate registrationEndDate = offering.getStartDate();
            if (registrationEndDate == null)
                continue;
            LocalDateTime end = registrationEndDate.atStartOfDay()
                    .withHour(22).withMinute(0); // FOR DEVELOPMENT
            //= RegistrationEndTime.withSecond(0).withNano(0);; // USED FOR DEVELOPING
            if (now.isEqual(end.minusHours(NumberOfHoursReminder1)))
                coursesReminder1.add(courseRepository.findById(offering.getCourse_id()).get().getCourse_name());
            if (now.isEqual(end.minusHours(NumberOfHoursReminder2)))
                coursesReminder2.add(courseRepository.findById(offering.getCourse_id()).get().getCourse_name());
        }
        if (!coursesReminder1.isEmpty())
            for (String emailAddress : studentEmails)
                sendReminderEmail(emailAddress, now.plusHours(NumberOfHoursReminder1),coursesReminder1,true);
        if (!coursesReminder2.isEmpty())
            for (String emailAddress : studentEmails)
                sendReminderEmail(emailAddress, now.plusHours(NumberOfHoursReminder2),coursesReminder2,false);
    }

    private void sendReminderEmail(String to, LocalDateTime end, List<String> courses,boolean lastReminder) {
        String subject;
        if (lastReminder)
            subject = "URGENT! Last Reminder: Course Registration Reminder";
        else
            subject = "Course Registration Reminder";
        String text = "Reminder: Course registration for courses: "+courses+" ends at " + end;
        EmailMessage emailMessage = new EmailMessage(to, subject, text);
        jmsTemplate.convertAndSend("mailbox", emailMessage);
    }
}
