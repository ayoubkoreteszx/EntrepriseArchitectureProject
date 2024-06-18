package attendanceProject.controller.dto.course;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTO {
    private double credits;
    private String courseDescription;
    private String courseName;
    private String courseCode;
    private String department;
    private List<Long> prerequisiteIds;
}
