package attendanceProject.controller;

import attendanceProject.controller.Dto.student.StudentMapper;
import attendanceProject.controller.Dto.student.StudentRequest;
import attendanceProject.controller.Dto.student.StudentResponse;
import attendanceProject.controller.webClientConfig.ResponseMessage;
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
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController

@RequestMapping("/student")
@Tag(name = "Student Management System")
public class StudentController  {
    @Autowired
    private PersonService personService;
    @Autowired
    private WebClient webClient;
    @GetMapping("/all")
    @Operation(summary = "View a list of available students")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the students",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    public ResponseEntity<?> getAllStudent() {
        List<Student> persons = personService.getAlStudents();
        if (persons == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(StudentMapper.mapToStudentResponseList(persons), HttpStatus.OK);
    }

    @GetMapping("/student/{id}")
    @Operation(summary = "Get a student by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the student",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    public ResponseEntity<?> getStudentById(@PathVariable long id) {
        Student person = personService.getStudentById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(StudentMapper.mapToStudentResponse(person), HttpStatus.OK);
    }


    @PostMapping("/addStudent")
    @Operation(summary = "Add a student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Student added successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class)))
    })
    public ResponseEntity<?> addStudent(@RequestBody StudentRequest student) {
        Faculty facultyResponse = webClient.get()
                .uri("http://localhost:8080/faculty/faculty/" + student.getAdvisorId())
                .retrieve()
                .bodyToMono(Faculty.class)
                .block();
        if (facultyResponse == null) {
            ResponseMessage rs=new ResponseMessage();
            rs.setMessage("Faculty not found");
            return new ResponseEntity<>(rs,HttpStatus.NOT_FOUND);
        }
        Student student1 =StudentMapper.mapToStudent(student);
        student1.setAdvisor(facultyResponse);
        personService.addPerson(student1);
        return new ResponseEntity<>(student, HttpStatus.OK);
    }


    @PutMapping ("/update")
    @Operation(summary = "Update a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Person updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "404", description = "Person not updated", content = @Content)
    })
    public ResponseEntity<?> updateStudent(@RequestBody StudentRequest person) {
        ResponseEntity<?> studentResponse=getStudentById(person.getId());
        if(studentResponse.getStatusCode()==HttpStatus.NOT_FOUND){
            ResponseMessage rs=new ResponseMessage();
            rs.setMessage("Student not found");
            return new ResponseEntity<>(rs, HttpStatus.NOT_FOUND);
        }

        personService.updatePerson(StudentMapper.mapToStudent(person));
        return new ResponseEntity<>(person, HttpStatus.OK);
    }


    @DeleteMapping ("/delete/{personId}")
    @Operation(summary = "Delete a Student")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "student deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Person.class))),
            @ApiResponse(responseCode = "204", description = "Student not deleted", content = @Content)
    })
    public ResponseEntity<?> deletePerson(@PathVariable long personId) {
        personService.deletePerson(personId);
        return new ResponseEntity<>(personId, HttpStatus.NO_CONTENT);
    }
}
