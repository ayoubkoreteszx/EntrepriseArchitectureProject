package EAProjectEmail.EAProjectEmail.Email;

import EAProjectEmail.EAProjectEmail.Data.CourseDTOResponse;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class CourseListener {

    @Autowired
    JmsTemplate jmsTemplate;

    @Autowired
    EmailService emailService;

    @JmsListener(destination = "newCourseRecord")
    public void receiveMessage(String courseDTOResponseString) {
        System.out.println("JMS receiver got triggered by course table update: " + courseDTOResponseString);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            CourseDTOResponse courseDTOResponse = objectMapper.readValue(courseDTOResponseString, CourseDTOResponse.class);
            String to = "abdallahgaber15@gmail.com";
            String subject = "ADMIN NOTIFICATION!";
            String text = "Dear Admin, Course ("+courseDTOResponse.getCourseName()+") " +
                    "with code ("+courseDTOResponse.getCourseCode()+") " +
                    "is added/updated successfully!";
            emailService.sendEmail(to, subject, text);
            System.out.println("!!!!! SENDING EMAIL TO (ADMIN: "+to+") !!!!!");

        } catch (IOException e) {
            System.out.println("JMS receiver: Cannot convert : " + courseDTOResponseString+" to a CalcInstruction object");
        }




    }
}
