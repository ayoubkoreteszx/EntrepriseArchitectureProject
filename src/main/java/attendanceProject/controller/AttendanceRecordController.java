package attendanceProject.controller;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance-records")
public class AttendanceRecordController {
    private AttendanceRecordService attendanceRecordService;
    public AttendanceRecordController(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getAttendanceRecordById(@PathVariable long id) {
        AttendanceRecordDTO attendanceRecord =  attendanceRecordService.getAttendanceRecordById(id);
        if (attendanceRecord == null) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(attendanceRecord, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<?> addAttendanceRecord(@RequestBody AttendanceRecordDTO attendanceRecordDTO) {
        return new ResponseEntity<AttendanceRecordDTO>(
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
}
