package attendanceProject.controller.Dto.courseRegistration;

import attendanceProject.controller.Dto.courseOffering.CourseOfferingResponse;
import attendanceProject.controller.Dto.student.StudentResponse;
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
