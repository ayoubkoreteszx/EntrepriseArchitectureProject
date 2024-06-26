package adminview.controller.dto;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;

@Data
public class AttendanceRecordDTOResponse {
    private long id;
    private String studentId;
    private LocalDateTime scanDateTime;
    private String locationName;
    private String locationTypeName;
    private LocalDate sessionDate;
    private LocalTime sessionStartTime;
    private LocalTime sessionEndTime;
    private String sessionName;

}
