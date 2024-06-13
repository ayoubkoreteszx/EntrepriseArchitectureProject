package com.miu.edu.projectea.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue
    private long id;
    private int capacity;
    @Embedded
    private Audit audit;
    private String room;
    @OneToOne
    private Course course;

}
