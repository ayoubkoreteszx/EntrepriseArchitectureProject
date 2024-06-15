package attendanceProject.service.attendanceRecordService.DTO;

import lombok.Data;

import java.time.LocalDateTime;

@Data
public class AttendanceRecordDTO {
    private long id;
    private LocalDateTime scanDateTime;
    private long studentId;
    private long sessionId;
    private long locationId;
}
