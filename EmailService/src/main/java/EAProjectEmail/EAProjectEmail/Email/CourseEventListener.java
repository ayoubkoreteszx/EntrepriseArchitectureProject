//package EAProjectEmail.EAProjectEmail.Email;
//
//import EAProjectEmail.EAProjectEmail.Data.CourseDTOResponse;
//import EAProjectEmail.EAProjectEmail.Data.CourseRegistration;
//import jakarta.persistence.PostPersist;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.event.EventListener;
//import org.springframework.jms.core.JmsTemplate;
//import org.springframework.stereotype.Service;
//
//@Service
//public class CourseEventListener {
//
//    @Autowired
//    private JmsTemplate jmsTemplate;
//
//    @EventListener
//    public void handleCourseEvent(CourseEvent event) {
//        System.out.println("EventListener Got Triggered!!");
//        CourseDTOResponse course = event.getCourse();
//        // Handle the received course object
//        System.out.println("(EventListener): Received Course Event: " + course.getCourseName());
//        String to = "abdallahgaber15@gmail.com"; // TODO: You should fetch the student's email based on the student_id
//        String subject = "Adding New Course Confirmation";
//        String text = "A new Course have been successfully added in the database with ID: "
//                + course.getId()+ ". And with name: "+ course.getCourseName();
//
//        // Create EmailMessage object
//        EmailMessage emailMessage = new EmailMessage(to, subject, text);
//        // Send message to the JMS queue
//        jmsTemplate.convertAndSend("mailbox", emailMessage);
//    }
//}
