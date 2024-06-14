package attendanceProject.service.attendanceService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.repository.AttendanceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceServiceImpl implements AttendanceService {
    @Autowired
    private AttendanceRepository attendanceRepository;
    @Override
    public List<AttendanceRecord> getAttendanceByStudentAndCourseOffering(Long studentId, Long courseOfferingId) {
        return attendanceRepository.findByStudent_StudentIdAndSession_CourseOffering_Id(studentId, courseOfferingId);
    }
}
