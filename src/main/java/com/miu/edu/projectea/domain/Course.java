package com.miu.edu.projectea.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Data;

import java.util.List;

@Data
@Entity
public class Course {
    @Id
    @GeneratedValue
    private long id;
    private double credits;
    private String description;
    private String name;
    private String code;
    private String department;
    @OneToMany
    List<Course> prerequisites;
}
