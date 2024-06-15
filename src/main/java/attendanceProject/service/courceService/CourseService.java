package attendanceProject.service.courceService;

import attendanceProject.service.DTO.CourseDTO;
import attendanceProject.domain.Course;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    //CREATE
    Course saveCourse(CourseDTO courseDTO);
    //READ
    Optional<Course> getCourseById(Long id);
    List<Course> getAllCourses();
    //UPDATE
    Course updateCourse(Course existingCourse, Course newCourse);
    //DELETE
    void deleteCourse(Long id);
}
