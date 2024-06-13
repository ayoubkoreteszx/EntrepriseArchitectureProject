package com.miu.edu.projectea.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class CourseRegistration {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private CourseOffering courseOffering;
    @OneToMany
    @OrderColumn(name = "sequence")
    private List<Student> student;
}
