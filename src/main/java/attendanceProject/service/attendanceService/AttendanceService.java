package attendanceProject.service.attendanceService;

import attendanceProject.domain.AttendanceRecord;

import java.util.List;

public interface AttendanceService {
    List<AttendanceRecord> getAttendanceByStudentAndCourseOffering(Long studentId, Long courseId);
}
