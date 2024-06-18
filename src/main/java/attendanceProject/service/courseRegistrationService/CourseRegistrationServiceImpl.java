package attendanceProject.service.courseRegistrationService;


import attendanceProject.domain.CourseRegistration;
import attendanceProject.repository.CourseRegistrationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseRegistrationServiceImpl implements CourseRegistrationService {

    @Autowired
    CourseRegistrationRepository courseRegistrationRepository;

    @Override
    public List<Long> getRegisteredStudentsByCourseOffering(Long courseOfferingId) {
        List<CourseRegistration> courseRegistrations = courseRegistrationRepository.findByCourseOfferingId(courseOfferingId);
        List<Long> studentIds = new ArrayList<>();
        for(CourseRegistration courseRegistration : courseRegistrations)
            studentIds.add(courseRegistration.getStudent().getId());
        return studentIds;
    }
}
