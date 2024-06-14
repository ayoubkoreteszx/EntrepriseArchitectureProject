package attendanceProject.controller;

import attendanceProject.domain.CourseOffering;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/courseOffering")
public class CourseOfferingController {
    @Autowired
    private CourseOfferingService courseOfferingService;

    @RequestMapping("/{id}")
    public ResponseEntity<?> getCourseOfferingById(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (courseOffering == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(courseOffering, HttpStatus.OK);
    }
}
