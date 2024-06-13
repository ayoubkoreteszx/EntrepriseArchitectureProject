package com.miu.edu.projectea.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Inheritance(strategy = InheritanceType.JOINED)
@Entity
public abstract class Person {
    @Id
    @GeneratedValue
    private long id;
    @Embedded
    private Audit audit;
    @OneToOne(mappedBy = "person")
    private PersonAccount personAccount;
    private LocalDate birthDay;
    private String email;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
}
