package attendanceProject.controller;

import attendanceProject.domain.CourseOffering;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLException;
import java.util.Objects;

@RestController
@RequestMapping("/sys-admin/course-offerings")
public class CourseOfferingController {
    private CourseOfferingService courseOfferingService;

    public CourseOfferingController(CourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseOfferingById(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(courseOffering, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        courseOfferingService.deleteCourseOfferingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> addCourseOffering(@RequestBody CourseOffering courseOffering) {
        courseOfferingService.createCourseOffering(courseOffering);
        return new ResponseEntity<> (courseOffering, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourseOffering(@PathVariable Long id,
                                                  @RequestBody CourseOffering courseOffering)
            throws SQLException {
        return new ResponseEntity<> (
                courseOfferingService.updateCourseOffering(id, courseOffering),
                HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCourseOfferings() {
        return new ResponseEntity<>(
                courseOfferingService.findAllCourseOfferings(),
                HttpStatus.OK
        );
    }
}
