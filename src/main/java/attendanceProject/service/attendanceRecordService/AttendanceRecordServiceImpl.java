package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Location;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.*;
import attendanceProject.controller.Dto.attendance.AttendanceDTOMapper;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceRecordServiceImpl implements AttendanceRecordService {
    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    SessionRepository sessionRepository;

    @Autowired
    private LocationRepository locationRepository;

    @Override
    public AttendanceRecordDTO createAttendanceRecord(AttendanceRecordDTO attendanceRecordDTO) {
        Student student = (Student) studentRepository.findByStudentId(attendanceRecordDTO.getStudentId());
        Session session = sessionRepository.findById(attendanceRecordDTO.getSessionId()).get();
        Location location = locationRepository.findByName(attendanceRecordDTO.getLactionName());
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

    @Override
    public List<AttendanceRecordDTO> getAttendanceRecordsByCourseOffering(Long courseOfferingId){
        return AttendanceDTOMapper.mapToDTOList(
                attendanceRecordRepository.findBySession_CourseOffering_Id(courseOfferingId)
        );
    }

    @Override
    public List<AttendanceRecord> getAttendanceRecordsByStudentId(String studentId) {
        return attendanceRecordRepository.findByStudent_StudentId(studentId);
    }

    @Override
    public AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord) {
        return attendanceRecordRepository.save(attendanceRecord);
    }
}