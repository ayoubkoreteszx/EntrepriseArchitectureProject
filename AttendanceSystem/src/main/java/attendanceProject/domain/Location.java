package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @ManyToOne
    @JoinColumn(name = "type_id")
    private LocationType locationType;
    @Embedded
    private Audit audit = new Audit();
    private String name;
    private int capacity;

}
