package attendanceProject.service.attendanceRecordService.DTO;

import attendanceProject.domain.AttendanceRecord;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceDTOMapper {
    public static AttendanceRecordDTO mapToDTO(AttendanceRecord attendanceRecord){
        if(attendanceRecord == null)
            return null;
        AttendanceRecordDTO dto = new AttendanceRecordDTO();
        dto.setId(attendanceRecord.getId());
        dto.setScanDateTime(attendanceRecord.getScanDateTime());
        dto.setLactionName(attendanceRecord.getLocation().getName());
        dto.setStudentId(attendanceRecord.getStudent().getStudentId());
        dto.setSessionId(attendanceRecord.getSession().getId());
        return dto;
    }

    public static List<AttendanceRecordDTO> mapToDTOList(List<AttendanceRecord> attendanceRecords) {
        return attendanceRecords.stream()
                .map(AttendanceDTOMapper::mapToDTO)
                .collect(Collectors.toList());
    }
}
