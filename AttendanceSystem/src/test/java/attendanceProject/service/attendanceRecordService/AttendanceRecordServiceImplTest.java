package attendanceProject.service.attendanceRecordService;

import attendanceProject.controller.dto.attendance.AttendanceRecordDTORequest;
import attendanceProject.controller.dto.attendance.AttendanceRecordDTOResponse;
import attendanceProject.domain.*;
import attendanceProject.repository.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AttendanceRecordServiceImplTest {

    @Mock
    private AttendanceRecordRepository attendanceRecordRepository;

    @Mock
    private PersonRepository personRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private LocationRepository locationRepository;

    @Mock
    private CourseOfferingRepository courseOfferingRepository;

    @InjectMocks
    private AttendanceRecordServiceImpl attendanceRecordService;

    private AttendanceRecordDTORequest attendanceRecordDTORequest;
    private AttendanceRecordDTOResponse attendanceRecordDTOResponse;
    private AttendanceRecord attendanceRecord;
    private Student student;
    private Session session;
    private Location location;

    @BeforeEach
    void setUp() {
        attendanceRecordDTORequest = new AttendanceRecordDTORequest();
        attendanceRecordDTORequest.setStudentId(1L);
        attendanceRecordDTORequest.setSessionId(1L);
        attendanceRecordDTORequest.setLocationId(1L);

        attendanceRecordDTOResponse = new AttendanceRecordDTOResponse();

        attendanceRecord = new AttendanceRecord();
        student = new Student();
        student.setId(1L);
        session = new Session();
        session.setId(1L);
        location = new Location();
        location.setId(1L);
        LocationType locationType = new LocationType();
        location.setLocationType(locationType);
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        attendanceRecord.setLocation(location);

    }

    @Test
    void createAttendanceRecord_ShouldReturnSavedRecordDTO() {
        when(personRepository.findByStudentId(1L)).thenReturn(student);
        when(sessionRepository.findById(1L)).thenReturn(Optional.of(session));
        when(locationRepository.findById(1L)).thenReturn(Optional.of(location));
        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenReturn(attendanceRecord);

        AttendanceRecordDTOResponse savedRecordDTO = attendanceRecordService.createAttendanceRecord(attendanceRecordDTORequest);

        assertNotNull(savedRecordDTO);
        verify(personRepository, times(1)).findByStudentId(1L);
        verify(sessionRepository, times(1)).findById(1L);
        verify(locationRepository, times(1)).findById(1L);
        verify(attendanceRecordRepository, times(1)).save(any(AttendanceRecord.class));
    }

    @Test
    void getAttendanceRecordById_ShouldReturnRecordDTO() {
        when(attendanceRecordRepository.findById(1L)).thenReturn(Optional.of(attendanceRecord));

        AttendanceRecordDTOResponse foundRecordDTO = attendanceRecordService.getAttendanceRecordById(1L);

        assertNotNull(foundRecordDTO);
        verify(attendanceRecordRepository, times(1)).findById(1L);
    }

    @Test
    void getAllAttendanceRecords_ShouldReturnAllRecordDTOs() {
        List<AttendanceRecord> records = List.of(attendanceRecord);

        when(attendanceRecordRepository.findAll()).thenReturn(records);

        List<AttendanceRecordDTOResponse> allRecordDTOs = attendanceRecordService.getAllAttendanceRecords();

        assertNotNull(allRecordDTOs);
        assertEquals(1, allRecordDTOs.size());
        verify(attendanceRecordRepository, times(1)).findAll();
    }

    @Test
    void deleteAttendanceRecord_ShouldInvokeDeleteById() {
        Long id = 1L;

        doNothing().when(attendanceRecordRepository).deleteById(id);

        attendanceRecordService.deleteAttendanceRecord(id);

        verify(attendanceRecordRepository, times(1)).deleteById(id);
    }

    @Test
    void getAttendanceRecordsByStudentAndCourseOffering_ShouldReturnRecordDTOs() {
        Long studentId = 1L;
        Long courseOfferingId = 1L;
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setStartDate(LocalDate.of(2024, 01,11));
        List<AttendanceRecord> records = List.of(attendanceRecord);
        when(courseOfferingRepository.findById(courseOfferingId)).thenReturn(Optional.of(courseOffering));
        when(attendanceRecordRepository.findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId)).thenReturn(records);

        List<AttendanceRecordDTOResponse> foundRecordDTOs = attendanceRecordService.getAttendanceRecordsByStudentAndCourseOffering(studentId, courseOfferingId);

        assertNotNull(foundRecordDTOs);
        assertEquals(1, foundRecordDTOs.size());
        verify(attendanceRecordRepository, times(1)).findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId);
    }
}