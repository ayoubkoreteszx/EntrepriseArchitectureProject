package attendanceProject.controller.dto.faculty;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Schema(description = "Details about the faculty")
@Data
public class FacultyResponse {
    @Schema(description = "Faculty Id of the faculty", example = "123", required = true)
    private long facultyId;
    @Schema(description = "First name of the faculty", example = "John", required = true)
    private String firstName;
    @Schema(description = "Last name of the faculty", example = "Doe", required = true)
    private String lastName;
    @Schema(description = "Email of the faculty", example = "macha@macha/com", required = true)
    private String email;
    @Schema(description = " faculty hobbies", example = "[tennis,swim]", required = true)
    private List<String> hobbies;

}
