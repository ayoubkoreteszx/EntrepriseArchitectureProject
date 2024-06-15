package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Entity
@Data
public class AttendanceRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private LocalDateTime scanDateTime = LocalDateTime.now();
    @ManyToOne
    @JoinColumn(name = "studentId")
    private Student student;
    @ManyToOne
    @JoinColumn(name = "locationId")
    private  Location location;
    @ManyToOne
    @JoinColumn(name = "sessin_id")
    private Session session;
}
