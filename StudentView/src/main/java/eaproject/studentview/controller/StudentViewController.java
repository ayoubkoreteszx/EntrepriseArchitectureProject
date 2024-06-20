package eaproject.studentview.controller;

import eaproject.studentview.controller.dto.AttendanceRecordDTOResponse;
import eaproject.studentview.controller.dto.CourseOfferingResponse;
import eaproject.studentview.feignClients.AttendanceSystemClient;
import eaproject.studentview.feignClients.CourseOfferingSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/student-view")
public class StudentViewController {
    @Autowired
    AttendanceSystemClient client;
    @Autowired
    CourseOfferingSystem courseOfferingSystem;
    @RequestMapping("/course-offerings/{offeringId}/attendance")
    public ResponseEntity<?>  getAttendanceRecordsForCourseOffering
            (@PathVariable long offeringId, @RequestParam long studentId){
        return new ResponseEntity<>(
                client.getStudentAttendanceRecordsForCourseOffering(studentId,
                        offeringId),
                HttpStatus.OK
        );
    }
    @GetMapping("/course-offerings/{offeringId}")
    public ResponseEntity<CourseOfferingResponse> getCourseOfferingById(@PathVariable long offeringId){
        try {
            ResponseEntity<?> response = courseOfferingSystem.getCourseOfferingById(offeringId);
            if (response.getStatusCode() == HttpStatus.OK){
                return new ResponseEntity<>((CourseOfferingResponse) response.getBody(), HttpStatus.OK);
            } else {
                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
            }

        } catch (Exception e){
            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
        }

    }
    @GetMapping("/attendance-records/{studentId}")
    public ResponseEntity<List<AttendanceRecordDTOResponse>> getAttendanceRecordById(@PathVariable long studentId){
        return new ResponseEntity<>(client.getStudentAttendanceRecords(studentId), HttpStatus.OK);
    }

}
