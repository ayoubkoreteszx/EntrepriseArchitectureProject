package attendanceProject.controller.Dto.faculty;

import attendanceProject.domain.enums.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;

@Data
public class FacultyRequest {

    @Schema(description = "First name of the faculty", example = "John", required = true)
    private String firstName;

    @Schema(description = "Last name of the faculty", example = "Doe", required = true)
    private String lastName;

    @Schema(description = "Email of the faculty", example = "macha@macha.com", required = true)
    private String email;

    @Schema(description = "Phone number of the faculty", example = "1234567890", required = true)
    private LocalDate birthDay;

    @Schema(description = "Username of the faculty", example = "macha", required = true)
    private String username;

    @Schema(description = "Password of the faculty", example = "macha", required = true)
    private String password;

    @Schema(description="your gender", example="MALE", required=true)
    private GenderType gender;

    @Schema(description = " faculty hobbies", example = "tennis,swim", required = true)
    private String hobbies;


}
