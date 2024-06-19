package attendanceProject.service.courceService;

import attendanceProject.controller.dto.course.CourseDTORequest;
import attendanceProject.domain.Course;
import attendanceProject.controller.dto.course.CourseDTOResponse;

import java.util.List;
import java.util.Optional;

public interface CourseService {
    //CREATE
    Course saveCourse(CourseDTORequest courseDTORequest);
    //READ
    Optional<CourseDTOResponse> getCourseByIdDTO(Long id);

    Optional<Course> getCourseById(Long id);

    List<CourseDTOResponse> getAllCourses();
    //UPDATE
    Course updateCourse(Course existingCourse, CourseDTORequest newCourse);
    //DELETE
    void deleteCourse(Long id);
}
