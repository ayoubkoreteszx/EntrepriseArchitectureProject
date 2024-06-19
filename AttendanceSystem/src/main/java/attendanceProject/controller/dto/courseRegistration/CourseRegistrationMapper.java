package attendanceProject.controller.dto.courseRegistration;

import attendanceProject.controller.dto.courseOffering.CourseOfferingMapper;
import attendanceProject.controller.dto.student.StudentMapper;
import attendanceProject.domain.CourseRegistration;

import java.util.List;
import java.util.stream.Collectors;

public class CourseRegistrationMapper {
    public static CourseRegistrationResponse toCourseRegistrationResponse(CourseRegistration courseRegistration) {
        CourseRegistrationResponse courseRegistrationResponse = new CourseRegistrationResponse();
        courseRegistrationResponse.setCourseOffering(CourseOfferingMapper.mapToCourseOfferingResponse( courseRegistration.getCourseOffering()));
        courseRegistrationResponse.setStudent(StudentMapper.mapToStudentResponse(courseRegistration.getStudent()));
        return courseRegistrationResponse;
    }
    public static List<CourseRegistrationResponse> toCourseRegistrationResponseList(List<CourseRegistration> courseRegistrations) {
        return courseRegistrations.stream()
                .map(CourseRegistrationMapper::toCourseRegistrationResponse)
                .collect(Collectors.toList());
    }

}
