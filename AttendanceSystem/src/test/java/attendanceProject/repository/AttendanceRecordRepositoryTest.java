package attendanceProject.repository;

import attendanceProject.domain.*;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import attendanceProject.service.courceService.CourseService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDate;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

//@ExtendWith(SpringExtension.class)
@DataJpaTest
class AttendanceRecordRepositoryTest {

    @Autowired
    private AttendanceRecordRepository attendanceRecordRepository;

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private CourseOfferingRepository courseOfferingRepository;

    @Autowired
    LocationRepository locationRepository;

    @MockBean
    CourseService courseService;
    @MockBean
    AttendanceRecordService attendanceRecordService;

//    @Autowired
//    private TestEntityManager entityManager;

    @Test
    void findByStudent_IdAndSession_CourseOffering_Id() {
        // create student
        Student student = new Student();
        student.setFirstName("Student");
//        student.setId(1L);
        student = personRepository.save(student);

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setStartDate(LocalDate.of(2024, 6, 10));
        courseOffering.setEndDate(LocalDate.of(2024, 6, 11));
//        courseOffering.setId(1L);
        courseOffering = courseOfferingRepository.save(courseOffering);

        List<Session> sessions = courseOffering.generateSessions();

        sessions = sessionRepository.saveAll(sessions);


        Location location = new Location();
        location.setName("Here");
        location = locationRepository.save(location);

        AttendanceRecord record1 = new AttendanceRecord();
        record1.setSession(sessions.get(0));
        record1.setStudent(student);
        record1.setLocation(location);
//        record1.setId(1L);
        attendanceRecordRepository.save(record1);

        AttendanceRecord record2 = new AttendanceRecord();
        record2.setSession(sessions.get(1));
        record2.setStudent(student);
        record2.setLocation(location);
//        record2.setId(2L);
        attendanceRecordRepository.save(record2);

        List<AttendanceRecord> records = attendanceRecordRepository
                .findByStudent_IdAndSession_CourseOffering_Id(student.getId(), courseOffering.getId());
        assertThat(records).hasSize(2);
        assertThat(records.get(0).getStudent().getId()).isEqualTo(student.getId());
        assertThat(records.get(0).getSession().getCourseOffering().getId()).isEqualTo(courseOffering.getId());
        assertThat(records.get(1).getStudent().getId()).isEqualTo(student.getId());
        assertThat(records.get(1).getSession().getCourseOffering().getId()).isEqualTo(courseOffering.getId());
    }
}