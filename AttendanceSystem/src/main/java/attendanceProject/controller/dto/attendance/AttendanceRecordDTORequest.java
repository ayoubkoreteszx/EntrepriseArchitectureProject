package attendanceProject.controller.dto.attendance;

import lombok.Data;

@Data
public class AttendanceRecordDTORequest {
    private long studentId;
    private long locationId;
    private long sessionId;
}
