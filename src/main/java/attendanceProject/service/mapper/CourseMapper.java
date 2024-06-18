package attendanceProject.service.mapper;

import attendanceProject.domain.Course;
import attendanceProject.service.DTO.CourseDTORequest;
import attendanceProject.service.DTO.CourseDTOResponse;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CourseMapper {
    public Course DTOToCourse(CourseDTORequest courseDTORequest)
    {
        Course course = new Course();
        course.setCredits(courseDTORequest.getCredits());
        course.setCourseDescription(courseDTORequest.getCourseDescription());
        course.setCourseName(courseDTORequest.getCourseName());
        course.setCourseCode(courseDTORequest.getCourseCode());
        course.setDepartment(courseDTORequest.getDepartment());
        return course;
    }
    public CourseDTOResponse CourseToDTO(Course course)
    {
        CourseDTOResponse courseDTOResponse = new CourseDTOResponse();
        courseDTOResponse.setId(course.getId());
        courseDTOResponse.setCredits(course.getCredits());
        courseDTOResponse.setCourseDescription(course.getCourseDescription());
        courseDTOResponse.setCourseName(course.getCourseName());
        courseDTOResponse.setCourseCode(course.getCourseCode());
        courseDTOResponse.setDepartment(course.getDepartment());
        //courseDTOResponse.setPrerequisiteIds(course.getPrerequisites());
        List<Course> prerequisites = course.getPrerequisites();
        List<String> prerequisiteNames = new ArrayList<String>();
        for (Course prerequisite : prerequisites)
        {
            prerequisiteNames.add(prerequisite.getCourseName());
        }
        courseDTOResponse.setPrerequisiteNames(prerequisiteNames);
        return courseDTOResponse;

    }

}
