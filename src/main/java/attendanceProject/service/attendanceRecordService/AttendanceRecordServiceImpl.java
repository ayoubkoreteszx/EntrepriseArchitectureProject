package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Location;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.AttendanceRecordRepository;
import attendanceProject.repository.LocationRepository;
import attendanceProject.repository.PersonRepository;
import attendanceProject.repository.SessionRepository;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceDTOMapper;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    PersonRepository personRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public AttendanceRecordDTO createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
        Student student = (Student) personRepository.findById(attendanceRecordDTO.getStudentId()).get();
        Session session = sessionRepository.findById(attendanceRecordDTO.getSessionId()).get();
        Location location = locationRepository.findById(attendanceRecordDTO.getLocationId()).get();
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        attendanceRecord.setLocation(location);
        return AttendanceDTOMapper.mapToDTO(attendanceRecordRepository.save(attendanceRecord));
    }

    @Override
    public AttendanceRecordDTO getAttendanceRecordById(Long id) {
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(id).orElse(null);
        if (attendanceRecord == null) {
            return null;
        }
        return AttendanceDTOMapper.mapToDTO(attendanceRecord);
    }

    @Override
    public AttendanceRecordDTO updateAttendanceRecord(Long id, AttendanceRecordDTO attendanceRecordDTO) {
        return null;
    }

    @Override
    public List<AttendanceRecordDTO> getAllAttendanceRecords() {
        return AttendanceDTOMapper.mapToDTOList(attendanceRecordRepository.findAll());
    }

    @Override
    public void deleteAttendanceRecord(long id) {
        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public List<AttendanceRecordDTO> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseOfferingId) {
        return AttendanceDTOMapper.mapToDTOList(attendanceRecordRepository.
                findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId));
    }

    public List<AttendanceRecord> getAttendanceRecordsByStudentId(String studentId) {
        return attendanceRecordRepository.findByStudent_StudentId(studentId);
    }

    @Override
    public AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord) {
        return attendanceRecordRepository.save(attendanceRecord);
    }
}