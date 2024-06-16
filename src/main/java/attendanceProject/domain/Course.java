package attendanceProject.domain;

import attendanceProject.service.DTO.CourseDTO;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
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

    public Course() {
    }




    public void UpdateAudit(LocalDate updatedDate, String updatedBy)
    {
        if (this.audit == null) {
            this.audit = new Audit();
            this.audit.createdBy = updatedBy;
            this.audit.createdDate = updatedDate;
        }
        this.audit.updatedDate = updatedDate;
        this.audit.updatedBy = updatedBy;
    }
    public void UpdateAudit(String updatedBy)
    {
        this.audit.updatedDate = LocalDate.now();
        this.audit.updatedBy = updatedBy;
    }

}
