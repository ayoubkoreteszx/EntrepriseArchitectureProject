package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private double credits;
    private String courseDescription;
    private String courseName;
    private String courseCode;
    private String department;
    @ManyToMany
    @JoinTable(
            name = "Course_Prerequisites",
            joinColumns = @JoinColumn(name = "course_id"),
            inverseJoinColumns = @JoinColumn(name = "prerequisite_id"))
    List<Course> prerequisites;
    @Embedded
    private Audit audit;
}
