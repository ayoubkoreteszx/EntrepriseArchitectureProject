package com.miu.edu.projectea.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;

@Data
@Entity
public class Student extends Person {
    private LocalDate enrollmentDate;
    private String QRCode;
    private String applicationId;

}
