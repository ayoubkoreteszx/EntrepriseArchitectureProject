package attendanceProject.service.mapper;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.DTO.AttendanceRecordDTO;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class AttendanceRecordMapper {
    public AttendanceRecordDTO toDTO(AttendanceRecord attendanceRecord) {
        AttendanceRecordDTO dto = new AttendanceRecordDTO();
        dto.setId(attendanceRecord.getId());
        dto.setStudentId(attendanceRecord.getStudent().getStudentId());
        dto.setScanDateTime(attendanceRecord.getScanDateTime());
        dto.setLocationName(attendanceRecord.getLocation().getName());
        dto.setLocationTypeName(attendanceRecord.getLocation().getLocationType().getType());
        dto.setSessionDate(attendanceRecord.getSession().getDate());
        dto.setSessionStartTime(attendanceRecord.getSession().getStartTime());
        dto.setSessionStartTime(attendanceRecord.getSession().getEndTime());
        return dto;
    }

    public List<AttendanceRecordDTO> toDTOs(List<AttendanceRecord> attendanceRecords) {
        return attendanceRecords.stream()
                .map(this::toDTO)
                .collect(Collectors.toList());
    }
}
