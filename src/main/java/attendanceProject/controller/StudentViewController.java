package attendanceProject.controller;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.CourseOffering;
import attendanceProject.service.attendanceService.AttendanceService;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/student-view")
public class StudentViewController {
    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private WebClient webClient;

    @GetMapping("/course-offerings/{offeringId}/attendance")
    public ResponseEntity<?> getCourseOfferingAttendance(@PathVariable Long offeringId, @RequestParam Long studentId) {
        CourseOffering courseOffering = webClient.get()
                .uri("http://localhost:8080/courseOffering/" + offeringId)
                .retrieve()
                .bodyToMono(CourseOffering.class)
                .block();
        if(Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        if (!courseOffering.getStartDate().isAfter(LocalDate.now())) {
                return new ResponseEntity<>(
                        attendanceService.getAttendanceByStudentAndCourseOffering(studentId, offeringId),
                        HttpStatus.OK
                );
            } else {
                throw new IllegalStateException("Course offering has not started yet.");
            }
        }
}
