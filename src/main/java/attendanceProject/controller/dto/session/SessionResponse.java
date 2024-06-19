package attendanceProject.controller.Dto.session;

import attendanceProject.domain.Session;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Data
public class SessionResponse {
    private long id;
    private String name;
    private LocalDate date;
    private LocalTime startTime;
    private LocalTime endTime;
    private long courseOfferingId;

    public SessionResponse(Session session){
        id = session.getId();
        name = session.getName();
        date = session.getDate();
        startTime = session.getStartTime();
        endTime = session.getEndTime();
        courseOfferingId = session.getCourseOffering().getId();
    }

    public static List<SessionResponse> mapToSessionResponse(List<Session> sessions){
        return sessions.stream().map(SessionResponse::new).collect(Collectors.toList());
    }
}
