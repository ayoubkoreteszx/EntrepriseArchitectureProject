package attendanceProject.domain;

import jakarta.persistence.Embeddable;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
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
    /*
    Please do not remove this it will set value of createdDate and
    updatedDate automatically when the record is saved for the first time
    or updated, I do not want to use the constructor above as it will only
    update when explicitly call it during the creation of the audit object
     */
    @PrePersist
    protected void onCreate() {
        createdDate = LocalDate.now();
        updatedDate = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedDate = LocalDate.now();
    }
}
