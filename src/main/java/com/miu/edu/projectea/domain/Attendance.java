package com.miu.edu.projectea.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue
    private long id;
    private LocalDate scanDate;
    @OneToMany
    private List<Person> student;
    @ManyToOne
    private  LocationType locationType;

}
