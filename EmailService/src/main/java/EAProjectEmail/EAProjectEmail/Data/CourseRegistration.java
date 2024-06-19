package EAProjectEmail.EAProjectEmail.Data;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Persistent;

@Getter
@Setter
@Entity
//@EntityListeners(CourseRegistrationListener.class)
@Table(name="course_registration")
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id")
    private long id;
    @Column(name="course_offering_id")
    private long courseOfferingId;
    @Column(name="student_id")
    private long studentId;
}
