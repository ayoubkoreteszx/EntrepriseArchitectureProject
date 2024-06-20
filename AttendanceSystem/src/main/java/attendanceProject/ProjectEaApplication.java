package attendanceProject;

import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.courceService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
//@EntityScan(basePackages = "attendanceProject.domain")
public class ProjectEaApplication implements CommandLineRunner {

    @Autowired
    private CourseService courseService;
    @Autowired
    private AttendanceRecordService attendanceRecordService;
    public static void main(String[] args) {
        SpringApplication.run(ProjectEaApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
    }

}
