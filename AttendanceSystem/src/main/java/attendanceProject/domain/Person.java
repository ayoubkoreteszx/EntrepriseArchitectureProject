package attendanceProject.domain;


import attendanceProject.domain.enums.GenderType;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;


@Schema(description = "Details about the person")
@Inheritance(strategy = InheritanceType.JOINED)
@SecondaryTable(name = "PersonAccount", pkJoinColumns = @PrimaryKeyJoinColumn(name = "person_id"))
@Entity
@Data
public class Person implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(hidden = true)
    private long id;

    @Schema(description = "Audit details of the person")
    @Embedded
    private Audit audit;

    @Schema(description = "Username of the person", example = "john.doe", required = true)
    @Column(table = "PersonAccount")
    private String username;

    @Column(table = "PersonAccount")
    @Schema(description = "Password of the person")
    private String password;

    @Schema(description = "Date of birth of the person", example = "1990-01-01", required = true)
    private LocalDate birthDay;

    @Schema(description = "Email of the person", example = "macha@macha.com", required = true)
    private String email;

    @Schema(description = "First name of the person", example = "John", required = true)
    private String firstName;

    @Schema(description = "Last name of the person", example = "Doe", required = true)
    private String lastName;

    @Schema(description=" Person Gender", example="Male", required = true)
    @Enumerated(EnumType.STRING)
    private GenderType gender;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return List.of();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
