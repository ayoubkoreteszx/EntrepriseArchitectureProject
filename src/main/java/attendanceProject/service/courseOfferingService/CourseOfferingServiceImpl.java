package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;
import attendanceProject.repository.CourseOfferingRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {
    @Autowired
    private CourseOfferingRepository courseOfferingRepository;
    @Override
    public CourseOffering getCourseOfferingById(Long id) {
        return courseOfferingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCourseOfferingById(Long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public void addCourseOffering(CourseOffering courseOffering) {
        courseOfferingRepository.save(courseOffering);
    }

    @Override
    public void updateCourseOffering(CourseOffering courseOffering) {
        courseOfferingRepository.save(courseOffering);
    }

    @Override
    public List<CourseOffering> findAllCourseOfferings() {
        return courseOfferingRepository.findAll();
    }
}
