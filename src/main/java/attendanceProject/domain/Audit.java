package attendanceProject.domain;

import jakarta.persistence.Embeddable;

import java.time.LocalDate;

@Embeddable
public class Audit {
    LocalDate createdDate;
    LocalDate updatedDate;
    String createdBy;
    String updatedBy;
}
