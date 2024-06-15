package attendanceProject.controller;

import attendanceProject.controller.request.StudentMapper;
import attendanceProject.controller.request.StudentRequest;
import attendanceProject.domain.Faculty;
import attendanceProject.domain.Person;
import attendanceProject.domain.Student;
import attendanceProject.service.personService.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController

@RequestMapping("/person")
@Tag(name = "Person Management System", description = "Operations pertaining to person in Person Management System")
public class PersonController  {
    @Autowired
    private PersonService personService;
    @GetMapping("/all")
    @Operation(summary = "View a list of available persons")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the persons",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Persons not found", content = @Content)
    })
    public ResponseEntity<?> getAllPersons() {
        List<Person> persons = personService.getAllPersons();
        if (persons == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(persons, HttpStatus.OK);
    }

    @GetMapping("/person/{id}")
    @Operation(summary = "Get a person by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the person",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Person not found", content = @Content)
    })
    public ResponseEntity<?> getPersonById(@PathVariable long id) {
        Person person = personService.getPersonById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    @PostMapping("/addStudent")
    @Operation(summary = "Add a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student added successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)))
    })
    public ResponseEntity<StudentRequest> addStudent(@RequestBody StudentRequest student) {
        personService.addPerson(StudentMapper.mapToStudent(student));
        return new ResponseEntity<>(student, HttpStatus.OK);
    }

    @Operation(summary = "Add a faculty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty added successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Faculty.class)))
    })
    @PostMapping("/addFaculty")
    public ResponseEntity<Faculty> addFaculty(@RequestBody Faculty faculty) {
        personService.addPerson(faculty);
        return new ResponseEntity<>(faculty, HttpStatus.OK);
    }



    @PutMapping ("/update")
    @Operation(summary = "Update a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Person not updated", content = @Content)
    })
    public ResponseEntity<?> updatePerson(@RequestBody Person person) {
        personService.updatePerson(person);
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    @DeleteMapping ("/delete")
    @Operation(summary = "Delete a person")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Person not deleted", content = @Content)
    })
    public ResponseEntity<?> deletePerson(@PathVariable long personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(personId, HttpStatus.NO_CONTENT);
    }
}
