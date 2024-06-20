package adminview.controller;

import adminview.controller.dto.CourseRegistrationResponse;
//import adminview.feignClients.CourseRegistrationSystem;
import adminview.service.ExcelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/admin-view")
public class AdminViewController {
    @Autowired
    ExcelService excelService;
//    @Autowired
//    CourseRegistrationSystem courseRegistrationSystem;

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
//    public CourseRegistrationResponse getAllCourseRegistrationsByStudentId(@PathVariable long id){
//        return courseRegistrationSystem.getAllCourseRegistrationsByStudentId(id);
//    }
}
