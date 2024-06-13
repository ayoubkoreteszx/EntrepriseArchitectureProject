package attendanceProject.domain;


import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class LocationType {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private String type;
    @Embedded
    private Audit audit;
}
