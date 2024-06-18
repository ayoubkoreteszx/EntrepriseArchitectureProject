package attendanceProject.controller.Dto.attendance;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AttendanceRecordDTORequest {
    private long studentId;
    private long locationId;
    private long sessionId;
}
