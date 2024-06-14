package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;


@Data
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int capacity;
    @Embedded
    private Audit audit;
    private String room;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Enumerated(EnumType.STRING)
    private CourseofferingType courseofferingType;
    private LocalDate startDate;
    private LocalDate endDate;
//    @OneToMany
//    @JoinColumn(name = "course_offering_id")
//    private List<Session> sessions;

}
