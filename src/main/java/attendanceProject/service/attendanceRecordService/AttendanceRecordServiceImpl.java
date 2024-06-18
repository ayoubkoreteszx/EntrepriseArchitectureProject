package attendanceProject.service.attendanceRecordService;

import attendanceProject.controller.Dto.attendance.AttendanceRecordDTORequest;
import attendanceProject.controller.Dto.attendance.AttendanceRecordDTOResponse;
import attendanceProject.controller.Dto.attendance.AttendanceRecordMapper;
import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Location;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.*;
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

    private AttendanceRecordMapper attendanceRecordMapper = new AttendanceRecordMapper();

    @Override
    public AttendanceRecordDTOResponse createAttendanceRecord(AttendanceRecordDTORequest attendanceRecordDTORequest) {
        Student student =  personRepository.findByStudentId(attendanceRecordDTORequest.getStudentId());
        Session session = sessionRepository.findById(attendanceRecordDTORequest.getSessionId()).get();
        Location location = locationRepository.findById(attendanceRecordDTORequest.getLocationId()).get();
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        attendanceRecord.setLocation(location);
        return attendanceRecordMapper.toDTOResponse(attendanceRecordRepository.save(attendanceRecord));
    }

    @Override
    public AttendanceRecordDTOResponse getAttendanceRecordById(Long id) {
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(id).orElse(null);
        if (attendanceRecord == null) {
            return null;
        }
        return attendanceRecordMapper.toDTOResponse(attendanceRecord);
    }

    @Override
    public AttendanceRecordDTOResponse updateAttendanceRecord(Long id, AttendanceRecordDTORequest attendanceRecordDTOResponse) {
        return null;
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAllAttendanceRecords() {
        return attendanceRecordMapper.toDTOsResponse(attendanceRecordRepository.findAll());
    }

    @Override
    public void deleteAttendanceRecord(long id) {
        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseOfferingId) {
        return attendanceRecordMapper.toDTOsResponse(attendanceRecordRepository.
                findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId));
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAttendanceRecordsByCourseOffering(Long courseOfferingId){
        return attendanceRecordMapper.toDTOsResponse(
                attendanceRecordRepository.findBySession_CourseOffering_Id(courseOfferingId)
        );
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecordsByStudentId(long studentId) {
        return attendanceRecordRepository.findByStudent_Id(studentId);
    }

    @Override
    public AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord) {
        return attendanceRecordRepository.save(attendanceRecord);
    }
}