package attendanceProject.service.attendanceRecordService.DTO;

import attendanceProject.domain.AttendanceRecord;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceDTOMapper {
    public static AttendanceRecordDTO mapToDTO(AttendanceRecord attendanceRecord){
        AttendanceRecordDTO dto = new AttendanceRecordDTO();
        dto.setId(attendanceRecord.getId());
        dto.setScanDateTime(attendanceRecord.getScanDateTime());
        dto.setLocationId(attendanceRecord.getLocation().getId());
        dto.setStudentId(attendanceRecord.getStudent().getId());
        dto.setSessionId(attendanceRecord.getSession().getId());
        return dto;
    }

    public static List<AttendanceRecordDTO> mapToDTOList(List<AttendanceRecord> attendanceRecords) {
        return attendanceRecords.stream()
                .map(AttendanceDTOMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
