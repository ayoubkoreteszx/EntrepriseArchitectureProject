package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Student extends Person {
    private LocalDate entry;
    private String alternativeId;
    private String applicationId;
    private String studentId;
    @ManyToOne
    @JoinColumn(name = "FacultyAdvisorId")
    private Faculty advisor;
}
