package adminview.feignClients;

import adminview.controller.dto.CourseRegistrationResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(name = "CourseRegistrationSystem", url = "http://localhost:8080")
public class CourseRegistrationSystem {
    @GetMapping("/course-registrations/allByStudentId/{id}")
    public ResponseEntity<?> getAllCourseRegistrationsByStudentId(long id){
        return getAllCourseRegistrationsByStudentId(id);
    }
}
