package attendanceProject.controller.mailTrigger;

import attendanceProject.controller.dto.course.CourseDTOResponse;
import attendanceProject.domain.Course;
import org.springframework.context.ApplicationEvent;

public class CourseEvent extends ApplicationEvent {
    private final CourseDTOResponse course;

    public CourseEvent(Object source, CourseDTOResponse course) {
        super(source);
        this.course = course;
    }

    public CourseDTOResponse getCourse() {
        return course;
    }
}
