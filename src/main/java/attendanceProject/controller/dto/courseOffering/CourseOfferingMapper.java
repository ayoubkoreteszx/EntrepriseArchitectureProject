package attendanceProject.controller.dto.courseOffering;

import attendanceProject.controller.dto.course.CourseMapper;
import attendanceProject.controller.dto.faculty.FacultyMapper;
import attendanceProject.domain.CourseOffering;

import java.util.List;
import java.util.stream.Collectors;

public class CourseOfferingMapper {
    public static CourseOffering mapToCourseOffering(CourseOfferingRequest courseOfferingRequest) {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setId(courseOfferingRequest.getId());
        courseOffering.setCapacity(courseOfferingRequest.getCapacity());
        courseOffering.setRoom(courseOfferingRequest.getRoom());
        courseOffering.setCourseofferingType(courseOfferingRequest.getCourseofferingType());
        courseOffering.setStartDate(courseOfferingRequest.getStartDate());
        courseOffering.setEndDate(courseOfferingRequest.getEndDate());
        courseOffering.setAudit(courseOfferingRequest.getAudit());
        return courseOffering;
    }

    public static CourseOfferingResponse mapToCourseOfferingResponse(CourseOffering courseOffering) {
        CourseOfferingResponse courseOfferingResponse = new CourseOfferingResponse();
        courseOfferingResponse.setId(courseOffering.getId());
        courseOfferingResponse.setCapacity(courseOffering.getCapacity());
        courseOfferingResponse.setRoom(courseOffering.getRoom());
        courseOfferingResponse.setCourse(CourseMapper.CourseToDTO(courseOffering.getCourse()));
        courseOfferingResponse.setFaculty(FacultyMapper.mapToFacultyResponse(courseOffering.getFaculty()));
        courseOfferingResponse.setCourseofferingType(courseOffering.getCourseofferingType());
        courseOfferingResponse.setStartDate(courseOffering.getStartDate());
        courseOfferingResponse.setEndDate(courseOffering.getEndDate());
        return courseOfferingResponse;
    }

    public static List<CourseOfferingResponse> mapToCourseOfferingResponseList(List<CourseOffering> courseOfferings) {
        return courseOfferings.stream().map(CourseOfferingMapper::mapToCourseOfferingResponse).collect(Collectors.toList());
    }

    public static CourseOfferingAdminResponse mapToCourseOfferingAdminResponse(CourseOffering courseOffering, List<Long> studentIds) {
        CourseOfferingAdminResponse courseOfferingResponse = new CourseOfferingAdminResponse(mapToCourseOfferingResponse(courseOffering));
        courseOfferingResponse.setEnrolledStudentIds(studentIds);
        return courseOfferingResponse;
    }


}
