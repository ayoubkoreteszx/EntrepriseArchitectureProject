package attendanceProject.controller.request;

import attendanceProject.domain.Audit;
import attendanceProject.domain.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
@Schema(description = "Details about the student")
public class StudentRequest {
    @Schema(description = "First name of the student", example = "John", required = true)
    private String firstName;

    @Schema(description = "Last name of the student", example = "Doe", required = true)
    private String lastName;

    @Schema(description = "Email of the student", example = "macha@macha.com", required = true)
    private String email;

    @Schema(description = "Phone number of the student", example = "1234567890", required = true)
    private LocalDate birthDay;

    @Schema(description = "Username of the student", example = "macha", required = true)
    private String username;

    @Schema(description = "Password of the student", example = "macha", required = true)
    private String password;

    @Schema(description="your gender", example="MALE", required=true)
    private GenderType gender;

    @Schema(description = "Entry date of the student", example = "2021-01-01", required = true)
    private LocalDate entry;

    @Schema(description = "Alternative Id for student", example = "123", required = true)
    private String alternativeId;

    @Schema(description = "Application Id for student", example = "123", required = true)
    private String applicationId;

    @Schema(description = "Student Id for student", example = "123", required = true)
    private String studentId;

    @Schema(description = "Faculty advisor of the student", example = "123", required = true)
    private long advisorId;

    @Schema(description = "Audit of the student", example = "123", required = true)
    private Audit audit;
}
