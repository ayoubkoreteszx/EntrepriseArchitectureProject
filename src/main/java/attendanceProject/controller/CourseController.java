package attendanceProject.controller;

import attendanceProject.controller.Dto.course.CourseDTORequest;
import attendanceProject.controller.Dto.course.CourseMapper;
import attendanceProject.domain.Course;
import attendanceProject.controller.Dto.course.CourseDTOResponse;
import attendanceProject.service.courceService.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/courses")
public class CourseController {
    @Autowired
    private CourseService courseService;

    @PostMapping
    public ResponseEntity<CourseDTOResponse> createCourse(@RequestBody CourseDTORequest courseDTORequest) {
        CourseMapper courseMapper = new CourseMapper();
        Course savedCourse = courseService.saveCourse(courseDTORequest);
        return ResponseEntity.ok(courseMapper.CourseToDTO(savedCourse));
    }

    @GetMapping("/{id}")
    public ResponseEntity<CourseDTOResponse> getCourseById(@PathVariable Long id) {
        Optional<CourseDTOResponse> course = courseService.getCourseByIdDTO(id);
        return course.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @GetMapping
    public ResponseEntity<List<CourseDTOResponse>> getAllCourses() {
        List<CourseDTOResponse> courses = courseService.getAllCourses();
        return ResponseEntity.ok(courses);
    }
    @PutMapping("/{id}")
    public ResponseEntity<CourseDTOResponse> updateCourse(@PathVariable Long id, @RequestBody CourseDTORequest newCourse) {
        Optional<Course> existingCourse = courseService.getCourseById(id);
        if (existingCourse.isPresent()) {
            CourseMapper courseMapper = new CourseMapper();
            Course updatedCourse = courseService.updateCourse(existingCourse.get(), newCourse);
            return ResponseEntity.ok(courseMapper.CourseToDTO(updatedCourse));
        } else {
            return ResponseEntity.notFound().build();
        }
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCourse(@PathVariable Long id) {
        courseService.deleteCourse(id);
        return ResponseEntity.noContent().build();
    }
}
