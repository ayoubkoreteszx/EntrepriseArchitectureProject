package attendanceProject.controller.dto.course;

import lombok.Data;

import java.util.List;

@Data
public class CourseDTOResponse {
    private long id;
    private double credits;
    private String courseDescription;
    private String courseName;
    private String courseCode;
    private String department;
    private List<String> prerequisiteNames;
}
