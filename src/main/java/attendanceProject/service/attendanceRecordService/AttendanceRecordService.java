package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;

import java.util.List;

public interface AttendanceRecordService {
    AttendanceRecordDTO createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO);
    AttendanceRecordDTO getAttendanceRecordById(Long id);
    AttendanceRecordDTO updateAttendanceRecord(Long id, AttendanceRecordDTO attendanceRecordDTO);
    List<AttendanceRecordDTO> getAllAttendanceRecords();
    void deleteAttendanceRecord(long id);
    List<AttendanceRecordDTO> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseId);
}