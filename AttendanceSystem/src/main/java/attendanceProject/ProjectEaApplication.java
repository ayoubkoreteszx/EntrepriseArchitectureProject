package attendanceProject;

import attendanceProject.domain.*;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.courceService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;

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
