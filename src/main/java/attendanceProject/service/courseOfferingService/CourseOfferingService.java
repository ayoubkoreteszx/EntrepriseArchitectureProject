package attendanceProject.service.courseOfferingService;

import attendanceProject.controller.Dto.session.SessionResponse;
import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.Session;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.List;

public interface CourseOfferingService {
    CourseOffering getCourseOfferingById(Long id);
    void deleteCourseOfferingById(Long id);
    CourseOffering createCourseOffering(CourseOffering courseOffering);
    CourseOffering updateCourseOffering(Long id, CourseOffering courseOffering);
    List<CourseOffering> findAllCourseOfferings();
    List<SessionResponse> findAllSessionsOfCourseOffering(Long courseOfferingId);
    List<SessionResponse> findAllPassedSessions(long courseOfferingId);
    List<CourseOffering> getAllCourseOfferingsByDate(LocalDate date);
}
