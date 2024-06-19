package attendanceProject.domain;

import attendanceProject.domain.enums.CourseofferingType;
import jakarta.persistence.*;
import lombok.Data;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;


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
/*
    Generate all the sessions for the course offering
    We have 2 sessions each day morning session 10 AM to 12:30 PM, afternoon session
            1:30 to 3:30 (monday to saturday) and only morning session on last day

 */
    public List<Session> generateSessions() {
        List<Session> sessions = new ArrayList<>();

        LocalDate currentDate = startDate;

        while (!currentDate.isAfter(endDate)) {
            if (currentDate.getDayOfWeek() != DayOfWeek.SUNDAY) {
                // Create the first session of the day
                Session morningSession = createSession(currentDate,"morning");
                sessions.add(morningSession);

                // If it's not the last day, create the second session of the day
                if (!currentDate.equals(endDate)) {
                    Session afternoonSession = createSession(currentDate, "afternoon");
                    sessions.add(afternoonSession);
                }
            }
            // Move to the next day
            currentDate = currentDate.plusDays(1);
        }

        return sessions;
    }

    private Session createSession(LocalDate currentDate, String type){
        LocalTime startTime = LocalTime.of(10, 0);
        LocalTime endTime = LocalTime.of(12, 30);
        if(type.equals("morning")){
            startTime = LocalTime.of(10, 0);
            endTime = LocalTime.of(12, 30);
        } else if (type.equals("afternoon")) {
            startTime = LocalTime.of(13, 30);
            endTime = LocalTime.of(15, 30);
        }
        Session session = new Session();
        session.setStartTime(startTime);
        session.setEndTime(endTime);
        session.setDate(currentDate);
        session.setName(currentDate + "-AM");
        session.setCourseOffering(this);
        return session;
    }
}
