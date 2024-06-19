package attendanceProject.service.courceService;

import attendanceProject.controller.dto.course.CourseDTORequest;
import attendanceProject.controller.dto.course.CourseDTOResponse;
import attendanceProject.controller.dto.course.CourseMapper;
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


    private List<Course> prerequisitesIdsToObjects(List<Long> prerequisiteIds)
    {
        List<Course> prerequisites = new ArrayList<>();
        for (Long id : prerequisiteIds) {
            Optional<Course> prerequisite = courseRepository.findById(id);
            prerequisite.ifPresent(prerequisites::add);
        }
        return prerequisites;
    }

    @Override
    public Course saveCourse(CourseDTORequest courseDTORequest) {
        CourseMapper courseMapper = new CourseMapper();
        Course course = courseMapper.DTOToCourse(courseDTORequest);
        if (courseDTORequest.getPrerequisiteIds() != null) {
            List<Course> prerequisites = prerequisitesIdsToObjects(courseDTORequest.getPrerequisiteIds());
            course.setPrerequisites(prerequisites);
        }
        course.setAudit(new Audit("Abdallah"));

        return courseRepository.save(course);
    }

    @Override
    public Optional<CourseDTOResponse> getCourseByIdDTO(Long id) {
        Optional<Course> course = courseRepository.findById(id);
        if (course.isPresent())
        {
            CourseMapper courseMapper = new CourseMapper();
            return Optional.of(courseMapper.CourseToDTO(course.get()));

        }else
            return Optional.empty();
    }
    @Override
    public Optional<Course> getCourseById(Long id) {
        return courseRepository.findById(id);
    }
    @Override
    public List<CourseDTOResponse> getAllCourses() {
        CourseMapper courseMapper = new CourseMapper();
        List<Course> courses = courseRepository.findAll();
        List<CourseDTOResponse> courseDTOResponseList= new ArrayList<CourseDTOResponse>();
        for (Course course : courses) {
            courseDTOResponseList.add(courseMapper.CourseToDTO(course));
        }
        return courseDTOResponseList;
    }


    @Override
    public Course updateCourse(Course existingCourse, CourseDTORequest newCourse) {

        if (newCourse.getCredits() != 0)
            existingCourse.setCredits(newCourse.getCredits());
        if (newCourse.getCourseDescription() != null)
            existingCourse.setCourseDescription(newCourse.getCourseDescription());
        if (newCourse.getCourseName() != null)
            existingCourse.setCourseName(newCourse.getCourseName());
        if (newCourse.getCourseCode() != null)
            existingCourse.setCourseCode(newCourse.getCourseCode());
        if (newCourse.getDepartment() != null)
            existingCourse.setDepartment(newCourse.getDepartment());
        if (newCourse.getPrerequisiteIds() != null)
        {
            List<Course> prerequisites = prerequisitesIdsToObjects(newCourse.getPrerequisiteIds());
            existingCourse.setPrerequisites(prerequisites);
        }
        existingCourse.UpdateAudit("Abdallah");   //UPDATE AUDIT DATA

        return courseRepository.save(existingCourse);
    }

    @Override
    public void deleteCourse(Long id) {
        courseRepository.deleteById(id);
    }
}
