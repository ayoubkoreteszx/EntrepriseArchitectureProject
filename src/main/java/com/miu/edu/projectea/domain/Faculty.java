package com.miu.edu.projectea.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.List;

@Data
public class Faculty extends Person{
    @OneToMany

    private CourseOffering courseOffering;
    @OneToMany
    private List<Student> student;
    @ElementCollection
    @CollectionTable(name="FacultyHobbies")
    private List<String> hobbies;

}
