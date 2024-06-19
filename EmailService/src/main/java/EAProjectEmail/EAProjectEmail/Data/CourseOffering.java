package EAProjectEmail.EAProjectEmail.Data;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
@Table(name="course_offering")
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int capacity;
    private String created_by;
    private LocalDate created_date;
    private String updated_by;
    private LocalDate updated_date;
    private String room;
    private long course_id;
    private long faculty_id;
    private String courseoffering_type;
    private LocalDate startDate;
    private LocalDate endDate;
}
