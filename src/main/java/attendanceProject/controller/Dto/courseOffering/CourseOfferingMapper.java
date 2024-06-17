package attendanceProject.controller.Dto.courseOffering;

import attendanceProject.controller.Dto.faculty.FacultyMapper;
import attendanceProject.domain.CourseOffering;

public class CourseOfferingMapper {
    public static CourseOfferingResponse mapToCourseOfferingResponse(CourseOffering courseOffering) {
        CourseOfferingResponse courseOfferingResponse = new CourseOfferingResponse();
        courseOfferingResponse.setId(courseOffering.getId());
        courseOfferingResponse.setCapacity(courseOffering.getCapacity());
        courseOfferingResponse.setRoom(courseOffering.getRoom());
        courseOfferingResponse.setCourse(courseOffering.getCourse());
        courseOfferingResponse.setFaculty(FacultyMapper.mapToFacultyResponse(courseOffering.getFaculty()));
        courseOfferingResponse.setCourseofferingType(courseOffering.getCourseofferingType());
        courseOfferingResponse.setStartDate(courseOffering.getStartDate());
        courseOfferingResponse.setEndDate(courseOffering.getEndDate());
        return courseOfferingResponse;
    }
}
