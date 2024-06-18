package attendanceProject.controller.dto.attendance;

import attendanceProject.domain.AttendanceRecord;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;

import java.util.List;
import java.util.stream.Collectors;

public class AttendanceDTOMapper {
    public static attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO mapToDTO(AttendanceRecord attendanceRecord){
        if(attendanceRecord == null)
            return null;
        attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO dto = new attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO();
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
