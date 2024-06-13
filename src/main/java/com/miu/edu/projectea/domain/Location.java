package com.miu.edu.projectea.domain;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Entity
public class Location {
    @Id
    @GeneratedValue
    private long id;
    @ManyToOne
    private LocationType locationType;
    @Embedded
    private Audit audit;
    private String name;
    private int capacity;

}
