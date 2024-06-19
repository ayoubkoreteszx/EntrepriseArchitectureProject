package attendanceProject.controller.dto.courseRegistration;

import attendanceProject.controller.dto.courseOffering.CourseOfferingResponse;
import attendanceProject.controller.dto.student.StudentResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Course Registration Response")
public class CourseRegistrationResponse {

    @Schema(description = "Course Offering", required = true)
    private CourseOfferingResponse courseOffering;
    @Schema(description = "Student", required = true)
    private StudentResponse student;

}
