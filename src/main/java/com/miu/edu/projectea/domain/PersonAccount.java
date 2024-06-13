package com.miu.edu.projectea.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToOne;

@Entity
public class PersonAccount {
    @Id
    @GeneratedValue
    private long id;
    @OneToOne
    private Person person;
    private String username;
    private String password;
}
