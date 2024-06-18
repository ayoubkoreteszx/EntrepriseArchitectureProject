package attendanceProject.controller.dto.student;

import attendanceProject.controller.dto.faculty.FacultyResponse;
import attendanceProject.domain.enums.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Details about the student")
public class StudentResponse {
    @Schema(description = "Student Id of the student", example = "123", required = true)
    private String studentId;
    @Schema(description = "First name of the student", example = "John", required = true)
    private String firstName;
    @Schema(description = "Last name of the student", example = "Doe", required = true)
    private String lastName;
    @Schema(description = "Email of the student", example = "macha@macha.com", required = true)
    private String email;
    @Schema(description = "Phone number of the student", example = "12.12.2012", required = true)
    private LocalDate birthDay;
    @Schema(description = "Username of the student", example = "macha", required = true)
    private String username;
    @Schema(description = "Gender of the student", example = "Male", required = true)
    private GenderType gender;
    @Schema(description = "Entry date of the student", example = "2021-01-01", required = true)
    private LocalDate entry;
    @Schema(description = "Alternative Id for student", example = "123", required = true)
    private String alternativeId;
    @Schema(description = "Application Id for student", example = "123", required = true)
    private String applicationId;
    @Schema(description = "Faculty advisor of the student", example = "123", required = true)
    private FacultyResponse advisor;


}
