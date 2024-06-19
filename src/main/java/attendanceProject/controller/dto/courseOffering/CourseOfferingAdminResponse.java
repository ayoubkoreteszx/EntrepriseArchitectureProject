package attendanceProject.controller.Dto.courseOffering;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.util.ArrayList;
import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@Schema(description = "Course Offering Response - (admin-view) ")
public class CourseOfferingAdminResponse extends CourseOfferingResponse {
    @Schema(description = "List of registrated student")
    private List<Long> enrolledStudentIds = new ArrayList<>();
    public CourseOfferingAdminResponse(CourseOfferingResponse courseOfferingResponse) {
        super.setId(courseOfferingResponse.getId());
        super.setCapacity(courseOfferingResponse.getCapacity());
        super.setRoom(courseOfferingResponse.getRoom());
        super.setCourse(courseOfferingResponse.getCourse());
        super.setFaculty(courseOfferingResponse.getFaculty());
        super.setCourseofferingType(courseOfferingResponse.getCourseofferingType());
        super.setStartDate(courseOfferingResponse.getStartDate());
        super.setEndDate(courseOfferingResponse.getEndDate());
    }
}
