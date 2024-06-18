package attendanceProject.service.courseRegistrationService;

import java.util.List;

public interface CourseRegistrationService {
    public List<Long> getRegisteredStudentsByCourseOffering(Long courseOfferingId);
}
