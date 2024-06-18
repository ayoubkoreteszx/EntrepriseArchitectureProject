package attendanceProject.service.attendanceRecordService;

import attendanceProject.controller.Dto.attendance.AttendanceRecordDTORequest;
import attendanceProject.controller.Dto.attendance.AttendanceRecordDTOResponse;
import attendanceProject.domain.AttendanceRecord;

import java.util.List;

public interface AttendanceRecordService {
    AttendanceRecordDTOResponse createAttendanceRecord(AttendanceRecordDTORequest attendanceRecordDTOResponse);
    AttendanceRecordDTOResponse getAttendanceRecordById(Long id);
    AttendanceRecordDTOResponse updateAttendanceRecord(Long id, AttendanceRecordDTORequest attendanceRecordDTOResponse);
    List<AttendanceRecordDTOResponse> getAllAttendanceRecords();
    void deleteAttendanceRecord(long id);
    List<AttendanceRecordDTOResponse> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseId);
    List<AttendanceRecordDTOResponse> getAttendanceRecordsByCourseOffering(Long courseOfferingId);
    public List<AttendanceRecordDTOResponse> getAttendanceRecordsByStudentId(long studentId);
    AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord);
}
