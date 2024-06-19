package EAProjectEmail.EAProjectEmail.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double credits;
    private String course_description;
    private String course_name;
    private String course_code;
    private String department;
    private String created_by;
    private LocalDate created_date;
    private String updated_by;
    private LocalDate updated_date;
}
