package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.domain.Session;
import attendanceProject.domain.Student;
import attendanceProject.repository.AttendanceRecordRepository;
import attendanceProject.repository.PersonRepository;
import attendanceProject.repository.SessionRepository;
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
    private PersonRepository personRepository;

    @Mock
    private SessionRepository sessionRepository;

    @InjectMocks
    private AttendanceRecordServiceImpl attendanceRecordService;

    private AttendanceRecord attendanceRecord;
    private Student student;
    private Session session;

    @BeforeEach
    void setUp() {
        attendanceRecord = new AttendanceRecord();
        student = new Student();
        session = new Session();
    }

    @Test
    void createAttendanceRecord_ShouldReturnSavedRecord() {
        Long studentId = 1L;
        Long sessionId = 1L;

        when(personRepository.findById(studentId)).thenReturn(Optional.of(student));
        when(sessionRepository.findById(sessionId)).thenReturn(Optional.of(session));
        when(attendanceRecordRepository.save(any(AttendanceRecord.class))).thenReturn(attendanceRecord);

        AttendanceRecord savedRecord = attendanceRecordService.createAttendanceRecorde(attendanceRecord, studentId, sessionId);

        assertNotNull(savedRecord);
        verify(personRepository, times(1)).findById(studentId);
        verify(sessionRepository, times(1)).findById(sessionId);
        verify(attendanceRecordRepository, times(1)).save(attendanceRecord);
    }

    @Test
    void getAttendanceRecordById_ShouldReturnRecord() {
        Long id = 1L;

        when(attendanceRecordRepository.findById(id)).thenReturn(Optional.of(attendanceRecord));

        AttendanceRecord foundRecord = attendanceRecordService.getAttendanceRecordById(id);

        assertNotNull(foundRecord);
        verify(attendanceRecordRepository, times(1)).findById(id);
    }

    @Test
    void getAllAttendanceRecords_ShouldReturnAllRecords() {
        List<AttendanceRecord> records = List.of(attendanceRecord);

        when(attendanceRecordRepository.findAll()).thenReturn(records);

        List<AttendanceRecord> allRecords = attendanceRecordService.getAllAttendanceRecords();

        assertNotNull(allRecords);
        assertEquals(1, allRecords.size());
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
    void getAttendanceRecordsByStudentAndCourseOffering_ShouldReturnRecords() {
        Long studentId = 1L;
        Long courseOfferingId = 1L;
        List<AttendanceRecord> records = List.of(attendanceRecord);

        when(attendanceRecordRepository.findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId)).thenReturn(records);

        List<AttendanceRecord> foundRecords = attendanceRecordService.getAttendanceRecordsByStudentAndCourseOffering(studentId, courseOfferingId);

        assertNotNull(foundRecords);
        assertEquals(1, foundRecords.size());
        verify(attendanceRecordRepository, times(1)).findByStudent_IdAndSession_CourseOffering_Id(studentId, courseOfferingId);
    }
}