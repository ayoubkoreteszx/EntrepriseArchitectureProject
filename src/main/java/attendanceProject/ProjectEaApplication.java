package attendanceProject;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = "com.miu.edu.projectea.domain")
public class ProjectEaApplication {

    public static void main(String[] args) {
        SpringApplication.run(ProjectEaApplication.class, args);
    }

}
