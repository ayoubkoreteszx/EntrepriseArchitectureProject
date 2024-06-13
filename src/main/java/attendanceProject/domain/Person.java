package attendanceProject.domain;

import jakarta.persistence.*;

import java.time.LocalDate;

@Inheritance(strategy = InheritanceType.JOINED)
@SecondaryTable(name = "PersonAccount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "person_id"))
@Entity
public abstract class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    @Embedded
    private Audit audit;
    @Column(table = "PersonAccount")
    private String username;
    @Column(table = "PersonAccount")
    private String password;
    private LocalDate birthDay;
    private String email;
    private String firstName;
    private String lastName;
    @Enumerated(EnumType.STRING)
    private GenderType gender;
}
