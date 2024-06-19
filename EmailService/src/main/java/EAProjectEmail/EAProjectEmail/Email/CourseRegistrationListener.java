//package EAProjectEmail.EAProjectEmail.Email;
//
//import EAProjectEmail.EAProjectEmail.Data.CourseRegistration;
//import jakarta.persistence.PostPersist;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Component;
//
//
//@Component
//public class CourseRegistrationListener {
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    @PostPersist
//    public void afterCourseRegistrationPersist(CourseRegistration courseRegistration) {
//        System.out.println("!!!!! ENTERED PostPersist METHOD !!!!!");
//        // Construct email details
//        String to = "abdallahgaber15@gmail.com"; // TODO: You should fetch the student's email based on the student_id
//        String subject = "Course Registration Confirmation";
//        String text = "You have been successfully registered for the course offering with ID: " + courseRegistration.getCourseOfferingId();
//
//        // Create EmailMessage object
//        EmailMessage emailMessage = new EmailMessage(to, subject, text);
//        // Send message to the JMS queue
//        jmsTemplate.convertAndSend("mailbox", emailMessage);
//    }
//}
