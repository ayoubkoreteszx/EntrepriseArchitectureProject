package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;
import attendanceProject.repository.CourseOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;
    @Override
    public CourseOffering getCourseOfferingById(Long id) {
        return courseOfferingRepository.findById(id).orElse(null);
    }
}
