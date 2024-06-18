package attendanceProject.domain;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
@AllArgsConstructor
public class Audit {
    LocalDate createdDate;
    LocalDate updatedDate;
    String createdBy;
    String updatedBy;

    public Audit() {
    }

    public Audit(String username) {
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
        this.createdBy = username;
        this.updatedBy = username;
    }
}
