package attendanceProject.controller;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import attendanceProject.service.mapper.AttendanceRecordMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attendance-records")
public class AttendanceRecordController {
    private AttendanceRecordService attendanceRecordService;
    
    @Autowired
    private AttendanceRecordMapper attendanceRecordMapper;
    public AttendanceRecordController(AttendanceRecordService attendanceRecordService) {
        this.attendanceRecordService = attendanceRecordService;
    }

    @GetMapping("/student/{studentId}")
    public List<attendanceProject.service.DTO.AttendanceRecordDTO> getAttendanceRecords(@PathVariable long studentId) {
        List<AttendanceRecord> attendanceRecords = attendanceRecordService.getAttendanceRecordsByStudentId(studentId);
        return attendanceRecordMapper.toDTOs(attendanceRecords);
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