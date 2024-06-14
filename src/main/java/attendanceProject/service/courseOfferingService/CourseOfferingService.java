package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;

import java.util.List;

public interface CourseOfferingService {
    CourseOffering getCourseOfferingById(Long id);
    void deleteCourseOfferingById(Long id);
    void addCourseOffering(CourseOffering courseOffering);
    void updateCourseOffering(CourseOffering courseOffering);
    List<CourseOffering> findAllCourseOfferings();
}
