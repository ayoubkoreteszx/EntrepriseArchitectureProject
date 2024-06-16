package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Location;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.*;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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
    private StudentRepository studentRepository;

    @Mock
    private SessionRepository sessionRepository;

    @Mock
    private LocationRepository locationRepository;

    @InjectMocks
    private AttendanceRecordServiceImpl attendanceRecordService;

    private AttendanceRecordDTO attendanceRecordDTO;
    private AttendanceRecord attendanceRecord;
    private Student student;
    private Session session;
    private Location location;

    @BeforeEach
    void setUp() {
        attendanceRecordDTO = new AttendanceRecordDTO();
        attendanceRecordDTO.setStudentId("123");
        attendanceRecordDTO.setSessionId("123");
        attendanceRecordDTO.setLactionName("Azerty");

        attendanceRecord = new AttendanceRecord();
        student = new Student();
        student.setId(1L);
        session = new Session();
        session.setId("123");
        location = new Location();
        location.setId(1L);
        attendanceRecord.setStudent(student);
        attendanceRecord.setSession(session);
        attendanceRecord.setLocation(location);

    }

    @Test
    void createAttendanceRecord_ShouldReturnSavedRecordDTO() {
        when(studentRepository.findByStudentId("123")).thenReturn(student);
        when(sessionRepository.findById("123")).thenReturn(Optional.of(session));
        when(locationRepository.findByName("Azerty")).thenReturn(location);
        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenReturn(attendanceRecord);

        AttendanceRecordDTO savedRecordDTO = attendanceRecordService.createAttendanceRecord(attendanceRecordDTO);

        assertNotNull(savedRecordDTO);
        verify(studentRepository, times(1)).findByStudentId("123");
        verify(sessionRepository, times(1)).findById("123");
        verify(locationRepository, times(1)).findByName("Azerty");
        verify(attendanceRecordRepository, times(1)).save(any(AttendanceRecord.class));
    }

    @Test
    void getAttendanceRecordById_ShouldReturnRecordDTO() {
        when(attendanceRecordRepository.findById(1L)).thenReturn(Optional.of(attendanceRecord));

        AttendanceRecordDTO foundRecordDTO = attendanceRecordService.getAttendanceRecordById(1L);

        assertNotNull(foundRecordDTO);
        verify(attendanceRecordRepository, times(1)).findById(1L);
    }

    @Test
    void getAllAttendanceRecords_ShouldReturnAllRecordDTOs() {
        List<AttendanceRecord> records = List.of(attendanceRecord);

        when(attendanceRecordRepository.findAll()).thenReturn(records);

        List<AttendanceRecordDTO> allRecordDTOs = attendanceRecordService.getAllAttendanceRecords();

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
        List<AttendanceRecord> records = List.of(attendanceRecord);

        when(attendanceRecordRepository.findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId)).thenReturn(records);

        List<AttendanceRecordDTO> foundRecordDTOs = attendanceRecordService.getAttendanceRecordsByStudentAndCourseOffering(studentId, courseOfferingId);

        assertNotNull(foundRecordDTOs);
        assertEquals(1, foundRecordDTOs.size());
        verify(attendanceRecordRepository, times(1)).findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId);
    }
}