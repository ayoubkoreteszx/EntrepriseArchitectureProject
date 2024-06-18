package attendanceProject.controller;

import attendanceProject.controller.Dto.attendance.AttendanceRecordDTORequest;
import attendanceProject.controller.Dto.attendance.AttendanceRecordDTOResponse;
import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.attendanceRecordService.ExcelExportService;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import attendanceProject.controller.Dto.attendance.AttendanceRecordMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/attendance-records")
public class AttendanceRecordController {
    private final AttendanceRecordService attendanceRecordService;

    public AttendanceRecordController(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }

    @GetMapping("/student/{studentId}")
    public ResponseEntity<?> getAttendanceRecords(@PathVariable long studentId) {
        return new ResponseEntity<>(
                attendanceRecordService.getAttendanceRecordsByStudentId(studentId),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceRecordById(@PathVariable long id) {
        AttendanceRecordDTOResponse attendanceRecord =  attendanceRecordService.getAttendanceRecordById(id);
        if (attendanceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAttendanceRecord(@RequestBody AttendanceRecordDTORequest attendanceRecordDTO) {
        AttendanceRecordMapper attendanceRecordMapper = new AttendanceRecordMapper();
        return new ResponseEntity<AttendanceRecordDTOResponse>(
                attendanceRecordService.createAttendanceRecord(attendanceRecordDTO),
                HttpStatus.CREATED
        );
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteAttendanceRecord(@PathVariable long id) {
        attendanceRecordService.deleteAttendanceRecord(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping
    public ResponseEntity<?> getAllAttendanceRecords() {
        return new ResponseEntity<>(attendanceRecordService.getAllAttendanceRecords(), HttpStatus.OK);
    }

    @GetMapping("/course-offering/{offeringId}")
    public ResponseEntity<?> getAttendanceRecordForCourseOffering(@PathVariable long offeringId){
        return new ResponseEntity<>(
                attendanceRecordService.getAttendanceRecordsByCourseOffering(offeringId),
                HttpStatus.OK
        );
    }
}