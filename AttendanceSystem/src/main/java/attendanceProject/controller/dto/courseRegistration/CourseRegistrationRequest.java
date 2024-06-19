package attendanceProject.controller.dto.courseRegistration;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(description = "Course Registration Request")
public class CourseRegistrationRequest {

        @Schema(description = "Course Offering Id", example = "1", required = true)
        private long courseOfferingId;
        @Schema(description = "Student Id", example = "1", required = true)
        private long studentId;
}
