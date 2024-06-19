package attendanceProject.controller;

import attendanceProject.controller.Dto.courseRegistration.CourseRegistrationMapper;
import attendanceProject.controller.Dto.courseRegistration.CourseRegistrationRequest;

import attendanceProject.controller.Dto.student.StudentMapper;
import attendanceProject.controller.webClientConfig.ResponseMessage;
import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.CourseRegistration;
import attendanceProject.domain.Student;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import attendanceProject.service.courseRegistration.CourseRegistrationService;
import attendanceProject.service.courseRegistration.CourseReigstrationServiceImplim;
import attendanceProject.service.personService.PersonService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;

@RestController
@Tag(name = "Course Registration Management System")
@RequestMapping("/course-registrations")
public class CourseRegistrationController {
    @Autowired
    private CourseReigstrationServiceImplim courseRegistrationService;

    @Autowired
    private CourseOfferingService courseOfferingService;
    @Autowired
    private PersonService personService;

    @PostMapping("/add")
    @Operation(summary = "Add a course registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "Course Registration added successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Course Offering not found", content = @Content),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    public ResponseEntity<?> addCourseRegistration(@RequestBody CourseRegistrationRequest courseRegistrationRequest) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(courseRegistrationRequest.getCourseOfferingId());
        Student student = personService.getStudentById(courseRegistrationRequest.getStudentId());
        if(courseOffering==null )
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Course Offering not found"));
        else if(student==null)
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Student not found"));
        courseRegistrationService.registerStudentToCourse(student, courseOffering);
        return ResponseEntity.status(HttpStatus.CREATED).body(new ResponseMessage("Course Registration added successfully"));
    }

    @GetMapping("/allByStudentId/{id}")
    @Operation(summary = "Get all course registration by student id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the courses registration",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseRegistration.class))),
            @ApiResponse(responseCode = "404", description = "Course registration not found", content = @Content)
    })
    public ResponseEntity<?> getCourseRegistrationById(@PathVariable long id) {
        List<CourseRegistration> courseRegistration = courseRegistrationService.getAllCourseRegistrationsByStudentId(id);
        if (courseRegistration == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Course registration not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(CourseRegistrationMapper.toCourseRegistrationResponseList(courseRegistration));
    }
    @GetMapping("/allByCourseOfferingId/{id}")
    @Operation(summary = "Get all students by course offering id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the students",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = Student.class))),
            @ApiResponse(responseCode = "404", description = "Student not found", content = @Content)
    })
    public ResponseEntity<?> getStudentsByCourseOfferingId(@PathVariable long id) {
        List<Student> students = courseRegistrationService.getAllStudentsByCourseOfferingId(id);
        if (students == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Student not found"));
        }
        return ResponseEntity.status(HttpStatus.OK).body(StudentMapper.mapToStudentResponseList(students));
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Delete a course registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course registration deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Course registration not found", content = @Content)
    })
    public ResponseEntity<?> deleteCourseRegistration(@PathVariable long id) {
        courseRegistrationService.deleteCourseRegistration(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Course registration deleted successfully"));
    }

    @DeleteMapping("/deleteAllByStudentId/{id}")
    @Operation(summary = "Delete all course registration by student id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course registration deleted successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Course registration not found", content = @Content)
    })
    public ResponseEntity<?> deleteAllCourseRegistrationsByStudentId(@PathVariable long id) {
        courseRegistrationService.deleteAllCourseRegistrationsByStudentId(id);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Course registration deleted successfully"));
    }
    @PutMapping("/update/{id}")
    @Operation(summary = "Update a course registration")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Course registration updated successfully",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = ResponseMessage.class))),
            @ApiResponse(responseCode = "404", description = "Course registration not found", content = @Content)
    })
    public ResponseEntity<?> updateCourseRegistration(@PathVariable long id, @RequestBody CourseRegistrationRequest courseRegistrationRequest) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(courseRegistrationRequest.getCourseOfferingId());
        if(courseOffering==null )
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ResponseMessage("Course Offering not found"));
        courseRegistrationService.updateCourseRegistration(id, courseOffering);
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage("Course registration updated successfully"));
    }





}
