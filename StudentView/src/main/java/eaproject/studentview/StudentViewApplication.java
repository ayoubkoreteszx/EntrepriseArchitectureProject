package eaproject.studentview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class StudentViewApplication {

	public static void main(String[] args) {
		SpringApplication.run(StudentViewApplication.class, args);
	}

}
