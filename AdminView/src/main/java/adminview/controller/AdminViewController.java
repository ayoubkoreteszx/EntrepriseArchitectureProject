package adminview.controller;

import adminview.controller.dto.CourseOfferingAdminResponse;
import adminview.controller.dto.CourseRegistrationResponse;
import adminview.feignClients.AttendanceSystemClient;
//import adminview.feignClients.CourseRegistrationSystem;
import adminview.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin-view")
public class AdminViewController {
    @Autowired
    ExcelService excelService;
//    @Autowired
//    CourseRegistrationSystem courseRegistrationSystem;
    @Autowired
    AttendanceSystemClient attendanceSystemClient;


    @GetMapping("/course-offerings/{offeringId}/attendance")
    public ResponseEntity<?> downloadAttendanceReportForCourseOffering
            (@PathVariable long offeringId){
        try{
            ByteArrayInputStream excelFile = excelService.exportAttendanceToExcel(offeringId);
            HttpHeaders headers = new HttpHeaders();
            headers.add("Content-Disposition", "attachment; filename=attendance.xlsx");
            return ResponseEntity
                    .ok()
                    .headers(headers)
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(excelFile.readAllBytes());
        } catch (IOException e) {
            return ResponseEntity.status(500).build();
        }
    }
//    @GetMapping("/course-registrations/allByStudentId/{id}")
//    public ResponseEntity<?> getAllCourseRegistrationsByStudentId(@PathVariable long id){
//        try {
//            ResponseEntity<?> response = courseRegistrationSystem.getAllCourseRegistrationsByStudentId(id);
//            if (response.getStatusCode() == HttpStatus.OK){
//                return new ResponseEntity<>((CourseRegistrationResponse) response.getBody(), HttpStatus.OK);
//            } else {
//                return new ResponseEntity<>(HttpStatus.NOT_FOUND);
//            }
//        } catch (Exception e){
//            return new ResponseEntity<>(HttpStatus.SERVICE_UNAVAILABLE);
//        }
//    }

    //USE CASE 7
    @GetMapping("/course-offerings/{offeringId}")
    public ResponseEntity<CourseOfferingAdminResponse> getCourseOffering(@PathVariable long offeringId) {
        return new ResponseEntity<>(
                attendanceSystemClient.getCourseOfferingById(offeringId),
                HttpStatus.OK
        );
    }

}
