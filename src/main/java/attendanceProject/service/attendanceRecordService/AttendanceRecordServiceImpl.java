package attendanceProject.service.attendanceRecordService;


import attendanceProject.controller.Dto.attendance.AttendanceRecordDTORequest;
import attendanceProject.controller.Dto.attendance.AttendanceRecordDTOResponse;
import attendanceProject.controller.Dto.attendance.AttendanceRecordMapper;
import attendanceProject.domain.*;
import attendanceProject.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

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

    @Autowired
    CourseOfferingRepository courseOfferingRepository;

    @Override
    public AttendanceRecordDTOResponse createAttendanceRecord(AttendanceRecordDTORequest attendanceRecordDTORequest) {
        Student student =  personRepository.findByStudentId(attendanceRecordDTORequest.getStudentId());
        Session session = sessionRepository.findById(attendanceRecordDTORequest.getSessionId()).get();
        Location location = locationRepository.findById(attendanceRecordDTORequest.getLocationId()).get();
        AttendanceRecord attendanceRecord = new AttendanceRecord();
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        attendanceRecord.setLocation(location);
        return AttendanceRecordMapper.toDTOResponse(attendanceRecordRepository.save(attendanceRecord));
    }

    @Override
    public AttendanceRecordDTOResponse getAttendanceRecordById(Long id) {
        AttendanceRecord attendanceRecord = attendanceRecordRepository.findById(id).orElse(null);
        if (attendanceRecord == null) {
            return null;
        }
        return AttendanceRecordMapper.toDTOResponse(attendanceRecord);
    }

    @Override
    public AttendanceRecordDTOResponse updateAttendanceRecord(Long id, AttendanceRecordDTORequest attendanceRecordDTOResponse) {
        return null;
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAllAttendanceRecords() {
        return AttendanceRecordMapper.toDTOsResponse(attendanceRecordRepository.findAll());
    }

    @Override
    public void deleteAttendanceRecord(long id) {
        attendanceRecordRepository.deleteById(id);
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAttendanceRecordsByStudentAndCourseOffering(Long studentId, Long courseOfferingId) {
        CourseOffering courseOffering = courseOfferingRepository.findById(courseOfferingId).get();
        if(!courseOffering.getStartDate().isAfter(LocalDate.now()))
            return AttendanceRecordMapper.toDTOsResponse(attendanceRecordRepository.
                findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId));
        throw new IllegalStateException("Course offering has not started yet.");
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAttendanceRecordsByCourseOffering(Long courseOfferingId){
        return AttendanceRecordMapper.toDTOsResponse(
                attendanceRecordRepository.findBySession_CourseOffering_Id(courseOfferingId)
        );
    }

    @Override
    public List<AttendanceRecordDTOResponse> getAttendanceRecordsByStudentId(long studentId) {
        return AttendanceRecordMapper.toDTOsResponse(
                attendanceRecordRepository.findByStudent_Id(studentId)
        );
    }

    @Override
    public AttendanceRecord saveAttendanceRecord(AttendanceRecord attendanceRecord) {
        return attendanceRecordRepository.save(attendanceRecord);
    }
}