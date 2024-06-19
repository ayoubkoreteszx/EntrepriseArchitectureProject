package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class CourseRegistration {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "courseOfferingId")
    private CourseOffering courseOffering;
    @ManyToOne
    @JoinColumn(name = "studentId")
//    @OrderColumn(name = "sequence")
    private Student student;
}
