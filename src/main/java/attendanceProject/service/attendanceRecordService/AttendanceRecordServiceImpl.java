package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Location;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.AttendanceRecordRepository;
import attendanceProject.repository.SessionRepository;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceDTOMapper;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import attendanceProject.service.locationService.LocationService;
import attendanceProject.service.personService.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    PersonService personService;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    private LocationService locationService;

    @Override
    public AttendanceRecordDTO createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
        Student student = (Student) personService.getPersonById(attendanceRecordDTO.getStudentId());
        Session session = sessionRepository.findById(attendanceRecordDTO.getSessionId()).get();
        Location location = locationService.findLocationById(attendanceRecordDTO.getLocationId());
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
}