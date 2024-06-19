package attendanceProject.controller;

import attendanceProject.domain.CourseOffering;

import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin-view")
public class AdminViewController {

    private CourseOfferingService courseOfferingService;

    public AdminViewController(CourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
    }

    @GetMapping("/course-offerings")
    public ResponseEntity<List<CourseOffering>> getCourseOfferingsByDate(@RequestParam LocalDate date){
        List<CourseOffering> courseOfferings = courseOfferingService.getAllCourseOfferingsByDate(date);
        if (Objects.isNull(courseOfferings)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseOfferings);
    }
}
