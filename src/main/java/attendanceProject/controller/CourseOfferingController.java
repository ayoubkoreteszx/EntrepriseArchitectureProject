package attendanceProject.controller;

import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.LocationType;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/courseOffering")
public class CourseOfferingController {
    @Autowired
    private CourseOfferingService courseOfferingService;

    @GetMapping("/{id}")
    public ResponseEntity<?> getCourseOfferingById(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (courseOffering == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(courseOffering, HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteBook(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (courseOffering == null) {
            return ResponseEntity.notFound().build();
        }
        courseOfferingService.deleteCourseOfferingById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PostMapping
    public ResponseEntity<?> addCourseOffering(@RequestBody CourseOffering courseOffering) {
        courseOfferingService.addCourseOffering(courseOffering);
        return new ResponseEntity<> (courseOffering, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCourseOffering(@PathVariable Long id, @RequestBody CourseOffering courseOffering) {
        courseOfferingService.updateCourseOffering(courseOffering);
        return new ResponseEntity<> (courseOffering, HttpStatus.OK);
    }

    @GetMapping
    public ResponseEntity<?> findAllCourseOfferings() {
        return new ResponseEntity<List<CourseOffering>>(
                courseOfferingService.findAllCourseOfferings(),
                HttpStatus.OK
        );
    }
}
