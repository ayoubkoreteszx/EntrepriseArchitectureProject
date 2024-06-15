package attendanceProject.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
@Schema(description = "Details about the student")
public class Student extends Person {
    @Schema(description = "Entry date of the student", example = "2021-01-01", required = true)
    private LocalDate entry;

    @Schema(description = "Alternative Id for student", example = "123", required = true)
    private String alternativeId;

    @Schema(description = "Application Id for student", example = "123", required = true)
    private String applicationId;

    @Schema(description = "Student Id for student", example = "123", required = true)
    private String studentId;

    @ManyToOne
    @JoinColumn(name = "FacultyAdvisorId")
    @Schema(description = "Faculty advisor of the student")
    private Faculty advisor;
}
