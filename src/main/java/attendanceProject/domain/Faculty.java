package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Faculty extends Person{
    private String salutation;
    @ElementCollection
    @CollectionTable(name="FacultyHobbies")
    private List<String> hobbies;

}
