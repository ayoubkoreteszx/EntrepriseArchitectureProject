package adminview;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

@SpringBootApplication
@EnableFeignClients
public class AdminViewApplication {

    public static void main(String[] args) {
        SpringApplication.run(AdminViewApplication.class, args);
    }

}
