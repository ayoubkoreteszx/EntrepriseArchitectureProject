package attendanceProject.controller.dto.course;

import attendanceProject.domain.Course;

import java.util.ArrayList;
import java.util.List;

public class CourseMapper {
    public static Course DTOToCourse(CourseDTORequest courseDTORequest)
    {
        Course course = new Course();
        course.setCredits(courseDTORequest.getCredits());
        course.setCourseDescription(courseDTORequest.getCourseDescription());
        course.setCourseName(courseDTORequest.getCourseName());
        course.setCourseCode(courseDTORequest.getCourseCode());
        course.setDepartment(courseDTORequest.getDepartment());
        return course;
    }
    public static CourseDTOResponse CourseToDTO(Course course)
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
        if (prerequisites != null) {
            for (Course prerequisite : prerequisites) {
                prerequisiteNames.add(prerequisite.getCourseName());
            }
        }
        courseDTOResponse.setPrerequisiteNames(prerequisiteNames);
        return courseDTOResponse;

    }

}
