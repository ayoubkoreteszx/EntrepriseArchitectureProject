package eaproject.studentview.feignClients;

import eaproject.studentview.controller.dto.CourseOfferingResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(name = "CourseOfferingSystem", url = "http://localhost:8080")
public interface CourseOfferingSystem {
    @GetMapping("/course-offerings/{courseId}")
    CourseOfferingResponse getCourseOfferingById(@PathVariable long courseId);

}
