package attendanceProject.domain;

import jakarta.persistence.Embeddable;
import lombok.Data;

import java.time.LocalDate;

@Embeddable
@Data
public class Audit {
    LocalDate createdDate;
    LocalDate updatedDate;
    String createdBy;
    String updatedBy;

    public Audit() {
    }

    public Audit(LocalDate createdDate, LocalDate updatedDate, String createdBy, String updatedBy) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
        this.createdBy = createdBy;
        this.updatedBy = updatedBy;
    }

    public Audit(LocalDate createdDate, String createdBy) {
        this.createdDate = createdDate;
        this.createdBy = createdBy;
    }

    public Audit(String username) {
        this.createdDate = LocalDate.now();
        this.updatedDate = LocalDate.now();
        this.createdBy = username;
        this.updatedBy = username;
    }
}
