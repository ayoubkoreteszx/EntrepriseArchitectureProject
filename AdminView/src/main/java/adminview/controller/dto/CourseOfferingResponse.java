package adminview.controller.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Course Offering Response ")
public class CourseOfferingResponse {
    @Schema(description = "Id of the course offering", example = "1", required = true)
    private long id;
    @Schema(description = "Capacity of the course offering", example = "100", required = true)
    private int capacity;
    @Schema(description = "location of the course offering", required = true)
    private String room;
    @Schema(description = "course related ", required = true)
    private CourseDTOResponse course;
    @Schema(description = "faculty teaching this course", required = true)
    private FacultyResponse faculty;
    @Schema(description = "course offering type", required = true)
    private CourseofferingType courseofferingType;
    @Schema(description = "start date of the course offering", required = true)
    private LocalDate startDate;
    @Schema(description = "end date of the course offering", required = true)
    private LocalDate endDate;
}
