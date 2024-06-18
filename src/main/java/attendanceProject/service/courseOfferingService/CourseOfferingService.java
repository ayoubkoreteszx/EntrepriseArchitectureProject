package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;

import java.time.LocalDate;
import java.util.List;

public interface CourseOfferingService {
    CourseOffering getCourseOfferingById(Long id);
    void deleteCourseOfferingById(Long id);
    CourseOffering createCourseOffering(CourseOffering courseOffering);
    CourseOffering updateCourseOffering(Long id, CourseOffering courseOffering);
    List<CourseOffering> findAllCourseOfferings();
    List<CourseOffering> getAllCourseOfferingsByDate(LocalDate date);
}
