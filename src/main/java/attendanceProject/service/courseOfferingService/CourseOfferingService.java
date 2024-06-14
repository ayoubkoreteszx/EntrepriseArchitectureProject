package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;

import java.sql.SQLException;
import java.util.List;

public interface CourseOfferingService {
    CourseOffering getCourseOfferingById(Long id);
    void deleteCourseOfferingById(Long id);
    CourseOffering createCourseOffering(CourseOffering courseOffering);
    CourseOffering updateCourseOffering(Long id, CourseOffering courseOffering) throws SQLException;
    List<CourseOffering> findAllCourseOfferings();
}
