package attendanceProject.controller;

import attendanceProject.controller.dto.courseOffering.CourseOfferingAdminResponse;
import attendanceProject.controller.dto.courseOffering.CourseOfferingMapper;
import attendanceProject.controller.dto.courseOffering.CourseOfferingRequest;
import attendanceProject.controller.dto.courseOffering.CourseOfferingResponse;
import attendanceProject.controller.webClientConfig.ResponseMessage;
import attendanceProject.domain.Course;
import attendanceProject.domain.CourseOffering;
import attendanceProject.domain.Faculty;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import attendanceProject.service.courseRegistration.CourseRegistrationService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/course-offerings")
@Tag(name = "Course Offering Management System")
public class CourseOfferingController {


    private final CourseRegistrationService courseRegistrationService;
    private final CourseOfferingService courseOfferingService;
    private final WebClient webClient;

    public CourseOfferingController(CourseOfferingService courseOfferingService,
                                    WebClient webClient,
                                    CourseRegistrationService courseRegistrationService) {
        this.courseOfferingService = courseOfferingService;
        this.webClient = webClient;
        this.courseRegistrationService = courseRegistrationService;
    }

    /**
     * Provide the details of a course offering with the given id.
     */
    @GetMapping("/{id} ")
    @Operation(summary = " Get a course offering by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = " 200", description = "Found the course offering",
                    content =  @Content(mediaType = "application/json",
                            schema =  @Schema(implementation = CourseOfferingResponse.class))),
            @ApiResponse( responseCode = "404", description = "Course offering not found", content = @Content)
    })
    public ResponseEntity<CourseOfferingResponse> getCourseOfferingById(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CourseOfferingMapper.mapToCourseOfferingResponse(courseOffering));
    }

    //USE CASE 7
    @GetMapping("/admin-view/{offeringId}")
    public ResponseEntity<CourseOfferingAdminResponse> getCourseOffering(@PathVariable long offeringId) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(offeringId);
        if (Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        List<Long> studentIds = courseRegistrationService.getRegisteredStudentsByCourseOffering(offeringId);
        return ResponseEntity.ok(new CourseOfferingAdminResponse(CourseOfferingMapper.mapToCourseOfferingResponse(courseOffering)));
    }

    /**
     * Provide a list of all course offerings.
     */
    @GetMapping
    @Operation(summary = "View a list of available course offerings")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found course offerings",
                    content = @Content(mediaType = "application/json",
                        schema = @Schema(implementation = CourseOfferingResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course offerings not found", content = @Content)
    })
    public ResponseEntity<List<?>> findAllCourseOfferings() {
        List<CourseOffering> courseOfferings = courseOfferingService.findAllCourseOfferings();
        if (Objects.isNull(courseOfferings)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CourseOfferingMapper.mapToCourseOfferingResponseList(courseOfferings));
    }

    /**
     * Creates a new course offering, setting its URL as the Location header on the
     * response.
     */
    @PostMapping
    @Operation(summary = "Add a course offering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "New course offering is created",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseOffering.class)))
    })
    public ResponseEntity<Void> createCourseOffering(@RequestBody CourseOfferingRequest newCourseOffering) {
        Faculty facultyResponse = webClient.get()
                .uri("http://localhost:8080/faculty/faculty/" + newCourseOffering.getFacultyId())
                .retrieve()
                .bodyToMono(Faculty.class)
                .block();
        if (Objects.isNull(facultyResponse)) {
            ResponseMessage rs = new ResponseMessage("Faculty not found");
            return ResponseEntity.ofNullable(rs).notFound().build();
        }

        Course course = webClient.get()
                .uri("http://localhost:8080/courses/" + newCourseOffering.getId())
                .retrieve()
                .bodyToMono(Course.class)
                .block();
        if (Objects.isNull(course)) {
            ResponseMessage rs = new ResponseMessage("Course not found");
            return ResponseEntity.ofNullable(rs).notFound().build();
        }

        CourseOffering courseOffering = CourseOfferingMapper.mapToCourseOffering(newCourseOffering);
        courseOffering.setFaculty(facultyResponse);
        courseOffering.setCourse(course);
        courseOfferingService.createCourseOffering(courseOffering);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{childId}")
                        .buildAndExpand(courseOffering.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    // Updates course offering
    @PutMapping("/{id}")
    @Operation(summary = "CourseOffering update")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "CourseOffering updated",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseOffering.class))),
            @ApiResponse(responseCode = "404", description = "CourseOffering not found", content = @Content)
    })
    public ResponseEntity<Void> updateCourseOffering(@PathVariable Long id,
                                                  @RequestBody CourseOffering courseOfferingUpdate) {

       CourseOffering courseOffering = courseOfferingService.updateCourseOffering(id, courseOfferingUpdate);
       if (courseOffering.getId() != 0) {
           return ResponseEntity.noContent().build();
       }
       return ResponseEntity.notFound().build();
    }

    // Deletes course offering
    @DeleteMapping("/{id}")
    @Operation(summary = "Delete a CourseOffering")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "204", description = "Course offering deleted",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseOffering.class))),
            @ApiResponse(responseCode = "204", description = "Course offering not found", content = @Content)
    })
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (Objects.isNull(courseOffering)) {
            System.out.println("No course offering found with id " + id);
            return ResponseEntity.noContent().build();
        }
        courseOfferingService.deleteCourseOfferingById(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/{id}/sessions")
    public ResponseEntity<?> getSessions(@PathVariable Long id){
        return new ResponseEntity<>(
                courseOfferingService.findAllSessionsOfCourseOffering(id),
                HttpStatus.OK
        );
    }

    @GetMapping("/{id}/passed-sessions")
    public ResponseEntity<?> getPassedSessions(@PathVariable Long id){
        return new ResponseEntity<>(
                courseOfferingService.findAllPassedSessions(id),
                HttpStatus.OK
        );
    }
}
