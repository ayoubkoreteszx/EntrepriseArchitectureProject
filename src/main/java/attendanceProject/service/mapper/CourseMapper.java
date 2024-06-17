package attendanceProject.service.mapper;

import attendanceProject.domain.Course;
import attendanceProject.service.DTO.CourseDTO;

public class CourseMapper {
    public Course CourseMapperDTO(CourseDTO courseDTO)
    {
        Course course = new Course();
        course.setCredits(courseDTO.getCredits());
        if (courseDTO.getCourseDescription() != null)
            course.setCourseDescription(courseDTO.getCourseDescription());
        if (courseDTO.getCourseName() != null)
            course.setCourseName(courseDTO.getCourseName());
        if (courseDTO.getCourseCode() != null)
            course.setCourseCode(courseDTO.getCourseCode());
        if (courseDTO.getDepartment() != null)
            course.setDepartment(courseDTO.getDepartment());
        return course;
    }
}
