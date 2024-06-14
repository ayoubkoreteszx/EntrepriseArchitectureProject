package attendanceProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
//@EntityScan(basePackages = "attendanceProject.domain")
public class ProjectEaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectEaApplication.class, args);
    }

}
