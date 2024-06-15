package attendanceProject.controller;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/attendance-records")
public class AttendanceRecordController {
    private AttendanceRecordService attendanceRecordService;
    public AttendanceRecordController(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }
//
//    public ResponseEntity<?> getAttendanceRecordById(long attendanceRecordId) {
//        AttendanceRecord
//        return new ResponseEntity<AttendanceRecord>(attendanceRecordService
//                .getAttendanceRecordById(attendanceRecordId), HttpStatus.OK);
//    }
}
