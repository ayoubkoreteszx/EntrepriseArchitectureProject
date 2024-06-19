package eaproject.studentview.controller;

import eaproject.studentview.feignClients.AttendanceSystemClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/student-view")
public class StudentViewController {
    @Autowired
    AttendanceSystemClient client;
    @RequestMapping("/course-offerings/{offeringId}/attendance")
    public ResponseEntity<?> getAttendanceRecordsForCourseOffering
            (@PathVariable long offeringId, @RequestParam long studentId){
        return new ResponseEntity<>(
                client.getStudentAttendanceRecordsForCourseOffering(studentId,
                        offeringId),
                HttpStatus.OK
        );
    }
}
