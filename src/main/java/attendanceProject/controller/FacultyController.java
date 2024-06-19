package attendanceProject.controller;

import attendanceProject.controller.Dto.faculty.FacultyMapper;
import attendanceProject.controller.Dto.faculty.FacultyRequest;
import attendanceProject.controller.Dto.faculty.FacultyResponse;
import attendanceProject.domain.Faculty;
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
@Tag(name = "Faculty Management System")
@RequestMapping("/faculty")
public class FacultyController {

    private PersonService personService;

    public FacultyController(PersonService personService) {
        this.personService = personService;
    }

    @GetMapping("/all")
    @Operation(summary = "View a list of available faculty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the faculty",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacultyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Faculty not found", content = @Content)
    })
    public ResponseEntity<?> getAllFaculty() {
        List<Faculty> persons = personService.getAllFaculties();
        if (persons == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(FacultyMapper.mapToFacultiesResponse(persons), HttpStatus.OK);
    }

    @GetMapping("/faculty/{id}")
    @Operation(summary = "Get a faculty by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the faculty",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacultyResponse.class))),
            @ApiResponse(responseCode = "404", description = "Faculty not found", content = @Content)
    })
    public ResponseEntity<?> getFacultyById(@PathVariable long id) {
        Faculty person = personService.getFacultyById(id);
        if (person == null) {
            return ResponseEntity.notFound().build();
        }
        return new ResponseEntity<>(person, HttpStatus.OK);
    }
    @PutMapping("/Update faculty")
    @Operation(summary = "Update a faculty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacultyResponse.class)))
    })
    public ResponseEntity<?> updateFaculty(@RequestBody FacultyRequest facultyRequest) {
       personService.updatePerson(FacultyMapper.mapToFaculty(facultyRequest));
       return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @PostMapping("/addFaculty")
    @Operation(summary = "Add a faculty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty added",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacultyResponse.class)))
    })
    public ResponseEntity<?> addFaculty(@RequestBody FacultyRequest facultyRequest) {
        personService.addPerson(FacultyMapper.mapToFaculty(facultyRequest));
        return new ResponseEntity<>(HttpStatus.CREATED);
    }
    @DeleteMapping("/faculty/{id}")
    @Operation(summary = "Delete a faculty")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Faculty deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = FacultyResponse.class)))
    })
    public ResponseEntity<?> deleteFaculty(@PathVariable long id) {
        personService.deletePerson(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }




}
