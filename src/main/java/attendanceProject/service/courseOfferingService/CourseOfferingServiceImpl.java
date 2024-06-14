package attendanceProject.service.courseOfferingService;

import attendanceProject.domain.CourseOffering;
import attendanceProject.repository.CourseOfferingRepository;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.List;
import java.util.Objects;

@Service
public class CourseOfferingServiceImpl implements CourseOfferingService {

    private CourseOfferingRepository courseOfferingRepository;

    public CourseOfferingServiceImpl(CourseOfferingRepository courseOfferingRepository) {
        this.courseOfferingRepository = courseOfferingRepository;
    }

    @Override
    public CourseOffering getCourseOfferingById(Long id) {
        return courseOfferingRepository.findById(id).orElse(null);
    }

    @Override
    public void deleteCourseOfferingById(Long id) {
        courseOfferingRepository.deleteById(id);
    }

    @Override
    public CourseOffering createCourseOffering(CourseOffering courseOffering) {
        return courseOfferingRepository.save(courseOffering);
    }

    @Override
    public CourseOffering updateCourseOffering(Long id, CourseOffering courseOffering) throws SQLException {
        CourseOffering oldCourseOffering = getCourseOfferingById(id);
        if (Objects.nonNull(oldCourseOffering)) {
            courseOffering.setId(id);
            courseOfferingRepository.save(courseOffering);
        } else {
            throw new SQLException("CourseOffering with id " + id + " not found");
        }
        return courseOffering;
    }

    @Override
    public List<CourseOffering> findAllCourseOfferings() {
        return courseOfferingRepository.findAll();
    }
}
