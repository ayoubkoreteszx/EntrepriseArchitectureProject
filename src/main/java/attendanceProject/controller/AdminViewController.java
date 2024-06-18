//package attendanceProject.controller;
//
//import attendanceProject.service.attendanceRecordService.ExcelExportService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.core.io.ByteArrayResource;
//import org.springframework.http.HttpHeaders;
//import org.springframework.http.MediaType;
//import org.springframework.http.ResponseEntity;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.PathVariable;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.io.ByteArrayInputStream;
//import java.io.IOException;
//
//@RestController
//@RequestMapping("/admin-view")
//public class AdminViewController {
//    @Autowired
//    ExcelExportService excelExportService;
//    @GetMapping("/course-offerings/{offeringId}")
//    public ResponseEntity<?> downloadAttendanceReportForCourseOffering
//            (@PathVariable Long offeringId){
//        try {
//
//            ByteArrayInputStream excelFile = excelExportService.exportAttendanceToExcel(offeringId);
//            HttpHeaders headers = new HttpHeaders();
//            headers.add("Content-Disposition", "attachment; filename=attendance.xlsx");
//
//            return ResponseEntity
//                    .ok()
//                    .headers(headers)
//                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
//                    .body(excelFile.readAllBytes());
//        } catch (IOException e) {
//            return ResponseEntity.status(500).build();
//        }
//    }
//}
