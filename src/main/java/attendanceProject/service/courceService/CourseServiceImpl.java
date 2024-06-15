package attendanceProject.service.courceService;

import attendanceProject.service.DTO.CourseDTO;
import attendanceProject.domain.Audit;
import attendanceProject.domain.Course;
import attendanceProject.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CourseServiceImpl implements CourseService {
    @Autowired
    private CourseRepository courseRepository;

    @Override
    public Course saveCourse(CourseDTO courseDTO) {
        Course course = new Course(courseDTO);
        if (courseDTO.getPrerequisiteIds() != null) {
            List<Course> prerequisites = new ArrayList<>();
            for (Long id : courseDTO.getPrerequisiteIds()) {
                Optional<Course> prerequisite = courseRepository.findById(id);
                prerequisite.ifPresent(prerequisites::add);
            }
            course.setPrerequisites(prerequisites);
        }
        course.setAudit(new Audit("Abdallah"));

        return courseRepository.save(course);
    }

    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public List<Course> getAllCourses() {
        return courseRepository.findAll();
    }


    @Override
    public Course updateCourse(Course existingCourse, Course newCourse) {
        if (newCourse.getCredits() == 0)
            newCourse.setCredits(existingCourse.getCredits());
        if (newCourse.getCourseDescription() == null)
            newCourse.setCourseDescription(existingCourse.getCourseDescription());
        if (newCourse.getCourseName() == null)
            newCourse.setCourseName(existingCourse.getCourseName());
        if (newCourse.getCourseCode() == null)
            newCourse.setCourseCode(existingCourse.getCourseCode());
        if (newCourse.getDepartment() == null)
            newCourse.setDepartment(existingCourse.getDepartment());
        if (newCourse.getPrerequisites() == null)
            newCourse.setPrerequisites(existingCourse.getPrerequisites());
        newCourse.setAudit(existingCourse.getAudit()); //CREATE AUDIT DATA
        newCourse.UpdateAudit("Abdallah");   //UPDATE AUDIT DATA

        return courseRepository.save(newCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
