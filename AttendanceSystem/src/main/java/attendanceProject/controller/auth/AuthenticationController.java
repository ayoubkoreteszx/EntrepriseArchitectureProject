package attendanceProject.controller.auth;

import attendanceProject.domain.Person;
import attendanceProject.repository.PersonRepository;
import attendanceProject.service.auth.AuthenticationService;
import attendanceProject.service.jwt.JwtService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/auth")
@RestController
public class AuthenticationController {

    private final JwtService jwtService;
    private final AuthenticationService authenticationService;

    public AuthenticationController(JwtService jwtService, AuthenticationService authenticationService, PersonRepository personRepository) {
        this.jwtService = jwtService;
        this.authenticationService = authenticationService;
    }

    @PostMapping("/signup")
    public ResponseEntity<Person> register(@RequestBody Person person) {
        Person registeredPerson = authenticationService.signUp(person);
        return ResponseEntity.ok(registeredPerson);
    }

    @PostMapping("/login")
    public ResponseEntity<LoginResponse> authenticate(@RequestBody Person person) {
        Person authenticatedPerson = authenticationService.authenticate(person);

        String token = jwtService.generateToken(authenticatedPerson);
        LoginResponse loginResponse = new LoginResponse(token, jwtService.getExpirationTime());
        return ResponseEntity.ok(loginResponse);
    }
}
