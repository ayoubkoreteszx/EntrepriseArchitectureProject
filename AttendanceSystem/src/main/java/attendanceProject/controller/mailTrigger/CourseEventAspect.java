package attendanceProject.controller.mailTrigger;

import attendanceProject.controller.dto.course.CourseDTOResponse;
import attendanceProject.controller.dto.course.CourseMapper;
import attendanceProject.domain.Course;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.http.ResponseEntity;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class CourseEventAspect {

//    @Autowired
//    private ApplicationEventPublisher eventPublisher;

    @Autowired
    JmsTemplate jmsTemplate;

    // Pointcut for methods getCourseById and updateCourse in CourseController
    @Pointcut("execution(* attendanceProject.controller.CourseController.createCourse(..)) || " +
            "execution(* attendanceProject.controller.CourseController.updateCourse(..))")
    public void courseMethods() {
        // Pointcut for specified methods
    }

    @AfterReturning(pointcut = "courseMethods()", returning = "result")
    public void afterCourseMethods(Object result) throws JsonProcessingException {
        System.out.println("EMAIL AOP IS RUNNING!!");

        ObjectMapper objectMapper = new ObjectMapper();
        if (result instanceof ResponseEntity) {
            ResponseEntity<?> responseEntity = (ResponseEntity<?>) result;
            if (responseEntity.getStatusCode().is2xxSuccessful() && responseEntity.getBody() instanceof CourseDTOResponse) {
                CourseDTOResponse courseDTOResponse = (CourseDTOResponse) responseEntity.getBody();
//                eventPublisher.publishEvent(new CourseEvent(this, courseDTOResponse));
                String courseDTOResponseString = objectMapper.writeValueAsString(courseDTOResponse);
                jmsTemplate.convertAndSend("newCourseRecord",courseDTOResponseString);
                System.out.println("EMAIL AOP PUBLISHED EVENT!!");
            }
        }
    }


}
