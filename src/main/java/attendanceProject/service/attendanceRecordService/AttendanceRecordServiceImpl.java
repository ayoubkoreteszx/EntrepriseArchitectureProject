package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.AttendanceRecordRepository;
import attendanceProject.repository.PersonRepository;
import attendanceProject.repository.SessionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    // to be replaced with personService
    @Autowired
    PersonRepository personRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Override
    public AttendanceRecord createAttendanceRecorde(AttendanceRecord attendanceRecord, Long studentId, Long sessionId) {
        Student student = (Student) personRepository.findById(sessionId).get();
        Session session = sessionRepository.findById(sessionId).get();
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        return attendanceRecordRepository.save(attendanceRecord);
    }

    @Override
    public AttendanceRecord getAttendanceRecordById(Long id) {
        return attendanceRecordRepository.findById(id).orElse(null);
    }

    @Override
    public AttendanceRecord updateAttendanceRecord(Long id, AttendanceRecord attendanceRecord) {
        return null;
    }

    @Override
    public List<AttendanceRecord> getAllAttendanceRecords() {
        return attendanceRecordRepository.findAll();
    }

    @Override
    public void deleteAttendanceRecord(long id) {
        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseOfferingId) {
        return attendanceRecordRepository.findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId);
    }
}
