package attendanceProject.controller;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.DTO.AttendanceRecordDTO;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.mapper.AttendanceRecordMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/attendance-records")
public class AttendanceRecordController {

    @Autowired
    private AttendanceRecordService attendanceRecordService;
    @Autowired
    private AttendanceRecordMapper attendanceRecordMapper;

    @GetMapping("/{studentId}")
    public List<AttendanceRecordDTO> getAttendanceRecords(@PathVariable String studentId) {
        List<AttendanceRecord> attendanceRecords = attendanceRecordService.getAttendanceRecordsByStudentId(studentId);
        return attendanceRecordMapper.toDTOs(attendanceRecords);
    }
}
