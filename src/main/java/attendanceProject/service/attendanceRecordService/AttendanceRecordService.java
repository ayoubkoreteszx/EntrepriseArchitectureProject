package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;

import java.util.List;

public interface AttendanceRecordService {
    AttendanceRecord createAttendanceRecorde(AttendanceRecord attendanceRecord,
                                             Long studentId, Long sessionId);
    AttendanceRecord getAttendanceRecordById(Long id);
    AttendanceRecord updateAttendanceRecord(Long id, AttendanceRecord attendanceRecord);
    List<AttendanceRecord> getAllAttendanceRecords();
    void deleteAttendanceRecord(long id);
    List<AttendanceRecord> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseId);
}
