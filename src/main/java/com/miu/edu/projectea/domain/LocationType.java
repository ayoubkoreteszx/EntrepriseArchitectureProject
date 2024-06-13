package com.miu.edu.projectea.domain;


import jakarta.persistence.Embedded;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Data;

@Data
@Entity
public class LocationType {
    @Id
    @GeneratedValue
    private long id;
    private String name;
    @Embedded
    private Audit audit;
}
