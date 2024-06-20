package attendanceProject.service.auth;

import attendanceProject.domain.Person;
import attendanceProject.repository.PersonRepository;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class AuthenticationService {

    private final PersonRepository personRepository;
    private final PasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public AuthenticationService(PersonRepository personRepository,
                                 PasswordEncoder passwordEncoder, AuthenticationManager authenticationManager) {
        this.personRepository = personRepository;
        this.passwordEncoder = passwordEncoder;
        this.authenticationManager = authenticationManager;
    }

    public Person signUp(Person input) {
        Person person = new Person();
        person.setFirstName(input.getFirstName());
        person.setLastName(input.getLastName());
        person.setEmail(input.getEmail());
        person.setPassword(passwordEncoder.encode(input.getPassword()));
        person.setUsername(input.getUsername());
        return personRepository.save(person);
    }

    public Person authenticate(Person person) {
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                person.getUsername(),
                person.getPassword()));

        return personRepository.findByUsername(person.getUsername()).orElseThrow();
    }
}
