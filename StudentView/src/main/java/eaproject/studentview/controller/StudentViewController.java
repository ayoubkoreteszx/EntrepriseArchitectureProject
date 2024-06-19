package eaproject.studentview.controller;

import eaproject.studentview.controller.dto.CourseOfferingResponse;
import eaproject.studentview.feignClients.AttendanceSystemClient;
import eaproject.studentview.feignClients.CourseOfferingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/student-view")
public class StudentViewController {
    @Autowired
    AttendanceSystemClient client;
    @Autowired
    CourseOfferingSystem courseOfferingSystem;
    @RequestMapping("/course-offerings/{offeringId}/attendance")
    public ResponseEntity<?> getAttendanceRecordsForCourseOffering
            (@PathVariable long offeringId, @RequestParam long studentId){
        return new ResponseEntity<>(
                client.getStudentAttendanceRecordsForCourseOffering(studentId,
                        offeringId),
                HttpStatus.OK
        );
    }
    @GetMapping("/course-offerings/{offeringId}")
    public ResponseEntity<CourseOfferingResponse> getCourseOfferingById(@PathVariable long offeringId){
        return new ResponseEntity<CourseOfferingResponse>(
                courseOfferingSystem.getCourseOfferingById(offeringId),
                HttpStatus.OK);
    }
}
