package attendanceProject.domain;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
@Schema(description = "Details about the faculty")
public class Faculty extends Person{
    @Schema(description = "Salutation of the faculty", example = "Dr", required = true)
    private String salutation;

    @Schema(description = "Department of the faculty", example = "[Computer Science,games]", required = true)
    @ElementCollection
    @CollectionTable(name="FacultyHobbies")
    private List<String> hobbies;

}
