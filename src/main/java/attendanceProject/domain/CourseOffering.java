package attendanceProject.domain;

import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;


@Data
@Entity
public class CourseOffering {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private int capacity;
    @Embedded
    private Audit audit;
    private String room;
    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;
    @ManyToOne
    @JoinColumn(name = "faculty_id")
    private Faculty faculty;
    @Enumerated(EnumType.STRING)
    private CourseofferingType courseofferingType;
    private LocalDate startDate;
    private LocalDate endDate;

//    Generate all the sessions for the course offering
//    We have 2 sessions each day morning session 10 AM to 12:30 PM, afternoon session
//            1:30 to 3:30 (monday to saturday) and only morning session on last day
    public List<Session> generateSessions() {
        List<Session> sessions = new ArrayList<>();

        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                // Create the first session of the day
                LocalTime morningSessionStartTime = LocalTime.of(10, 0);
                LocalTime morningSessionEndTime = LocalTime.of(12, 30);
                Session morningSession = new Session();
                morningSession.setStartTime(morningSessionStartTime);
                morningSession.setEndTime(morningSessionEndTime);
                morningSession.setDate(currentDate);
                morningSession.setId(currentDate + "-AM");
                morningSession.setCourseOffering(this);
                sessions.add(morningSession);

                // If it's not the last day, create the second session of the day
                if (!currentDate.equals(endDate)) {
                    LocalTime afternoonSessionStartTime = LocalTime.of(13, 30);
                    LocalTime afternoonSessionEndTime = LocalTime.of(15, 30);
                    Session afternoonSession = new Session();
                    afternoonSession.setStartTime(afternoonSessionStartTime);
                    afternoonSession.setEndTime(afternoonSessionEndTime);
                    afternoonSession.setDate(currentDate);
                    afternoonSession.setId(currentDate+ "-PM");
                    afternoonSession.setCourseOffering(this);
                    sessions.add(afternoonSession);
                }
            }
            // Move to the next day
            currentDate = currentDate.plusDays(1);
        }

        return sessions;
    }
}
