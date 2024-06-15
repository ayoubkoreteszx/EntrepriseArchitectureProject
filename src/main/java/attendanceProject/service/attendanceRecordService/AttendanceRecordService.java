package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Course;
import attendanceProject.service.DTO.CourseDTO;

import java.util.List;

public interface AttendanceRecordService {
    public List<AttendanceRecord> getAttendanceRecordsByStudentId(String studentId);
    AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord);
}
