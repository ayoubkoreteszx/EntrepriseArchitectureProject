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
/*
        Student student = new Student();
        student.setStudentId("TEST_St_ID");

        LocationType locationType = new LocationType();
        locationType.setType("LocationTypeTest");
        Location location = new Location();
        location.setLocationType(locationType);
        location.setName("LocationNameTest");
        Session session = new Session();
        session.setDate(LocalDate.now());
        session.setStartTime(LocalTime.MIDNIGHT);
        session.setEndTime(LocalTime.NOON);
        attendanceRecordService.saveAttendanceRecord(new AttendanceRecord(
                LocalDateTime.now(),
                student,
                location,
                session
        ));
        */



/*
        Course course = new Course();
        course.setCourseCode("CS544");
        course.setAudit(new Audit(
                LocalDate.of(2024,6,14),
                LocalDate.of(2024,6,15),
                "Abdallah", "Gaber"));
        course.setCourseName("Enterprise Architecture");
        course.setCourseDescription("Spring Course");
        course.setCredits(500);
        course.setDepartment("ComPro");
        course.setPrerequisites(null);

        courseService.saveCourse(course);

        Course course2 = new Course();
        course2.setCourseCode("CS500");
        course2.setAudit(new Audit(
                LocalDate.of(2024,6,14),
                LocalDate.of(2024,6,15),
                "PAbdallah", "PGaber"));
        course2.setCourseName("PEnterprise Architecture");
        course2.setCourseDescription("PSpring Course");
        course2.setCredits(400);
        course2.setDepartment("PComPro");
        course2.setPrerequisites(Arrays.asList(course));

        courseService.saveCourse(course2);
*/




    }

}
