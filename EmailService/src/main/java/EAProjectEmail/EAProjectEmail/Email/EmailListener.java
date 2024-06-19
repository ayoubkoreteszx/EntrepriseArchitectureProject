package EAProjectEmail.EAProjectEmail.Email;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;

@Component
public class  EmailListener {

    @Autowired
    private EmailService emailService;

    @JmsListener(destination =  "mailbox")
    public void receiveMessage(EmailMessage emailMessage) {
        System.out.println("!!!!! SENDING  EMAIL TO ("+emailMessage.getTo()+") !!!!!");
        emailService.sendEmail(emailMessage.getTo(), emailMessage.getSubject(), emailMessage.getText());
    }
}
