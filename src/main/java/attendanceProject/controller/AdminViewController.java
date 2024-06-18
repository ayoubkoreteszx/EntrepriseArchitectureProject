package attendanceProject.controller;

import attendanceProject.domain.CourseOffering;
import attendanceProject.service.attendanceRecordService.ExcelExportService;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/admin-view")
public class AdminViewController {

    private ExcelExportService excelExportService;
    private CourseOfferingService courseOfferingService;

    public AdminViewController(ExcelExportService excelExportService, CourseOfferingService courseOfferingService) {
        this.excelExportService = excelExportService;
        this.courseOfferingService = courseOfferingService;
    }

    @GetMapping("/course-offerings/{offeringId}")
    public ResponseEntity<?> downloadAttendanceReportForCourseOffering
            (@PathVariable long offeringId){
        try {

            ByteArrayInputStream excelFile = excelExportService.exportAttendanceToExcel(offeringId);
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

    @GetMapping("/course-offerings")
    public ResponseEntity<List<CourseOffering>> getCourseOfferingsByDate(@RequestParam LocalDate date){
        List<CourseOffering> courseOfferings = courseOfferingService.getAllCourseOfferingsByDate(date);
        if (Objects.isNull(courseOfferings)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(courseOfferings);
    }
}
