package attendanceProject.controller;

import attendanceProject.controller.Dto.courseOffering.CourseOfferingMapper;
import attendanceProject.controller.Dto.courseOffering.CourseOfferingResponse;
import attendanceProject.domain.CourseOffering;
import attendanceProject.service.courseOfferingService.CourseOfferingService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/course-offerings")
@Tag(name = "Course Offering Management System")
public class CourseOfferingController {

    private final CourseOfferingService courseOfferingService;

    public CourseOfferingController(CourseOfferingService courseOfferingService) {
        this.courseOfferingService = courseOfferingService;
    }

    /**
     * Provide the details of a course offering with the given id.
     */
    @GetMapping("/{id}")
    @Operation(summary = "Get a course offering by id")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Found the course offering",
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = CourseOfferingResponse.class))),
            @ApiResponse(responseCode = "404", description = "Course offering not found", content = @Content)
    })
    public ResponseEntity<?> getCourseOfferingById(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(CourseOfferingMapper.mapToCourseOfferingResponse(courseOffering));
    }

    /**
     * Provide a list of all course offerings.
     */
    @GetMapping
    public ResponseEntity<List<CourseOffering>> findAllCourseOfferings() {
        return new ResponseEntity<>(
                courseOfferingService.findAllCourseOfferings(),
                HttpStatus.OK
        );
    }

    /**
     * Creates a new course offering, setting its URL as the Location header on the
     * response.
     */
    @PostMapping
    public ResponseEntity<Void> createCourseOffering(@RequestBody CourseOffering newCourseOffering) {
        CourseOffering courseOffering = courseOfferingService.createCourseOffering(newCourseOffering);
        URI location =
                ServletUriComponentsBuilder.fromCurrentRequestUri()
                        .path("/{childId}")
                        .buildAndExpand(courseOffering.getId())
                        .toUri();
        return ResponseEntity.created(location).build();
    }

    // Updates course offering
    @PutMapping("/{id}")
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
    public ResponseEntity<Void> deleteBook(@PathVariable Long id) {
        CourseOffering courseOffering = courseOfferingService.getCourseOfferingById(id);
        if (Objects.isNull(courseOffering)) {
            return ResponseEntity.notFound().build();
        }
        courseOfferingService.deleteCourseOfferingById(id);
        return ResponseEntity.noContent().build();
    }
}
