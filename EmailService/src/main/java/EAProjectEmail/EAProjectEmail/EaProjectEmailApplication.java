package EAProjectEmail.EAProjectEmail;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jms.annotation.EnableJms;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication (scanBasePackages = {"EAProjectEmail.EAProjectEmail.Data","EAProjectEmail.EAProjectEmail.Email","EAProjectEmail.EAProjectEmail"})
@EnableScheduling
@EnableJms
public class EaProjectEmailApplication {

	public static void main(String[] args) {
		SpringApplication.run(EaProjectEmailApplication.class, args);
	}

}
