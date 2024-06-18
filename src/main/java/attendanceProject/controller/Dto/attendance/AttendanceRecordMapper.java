package attendanceProject.controller.Dto.attendance;

import attendanceProject.domain.AttendanceRecord;
import org.springframework.stereotype.Component;
import java.util.List;
import java.util.stream.Collectors;

public class AttendanceRecordMapper {
    public static AttendanceRecordDTOResponse toDTOResponse(AttendanceRecord attendanceRecord) {
        AttendanceRecordDTOResponse dto = new AttendanceRecordDTOResponse();
        dto.setId(attendanceRecord.getId());
        dto.setStudentId(attendanceRecord.getStudent().getStudentId());
        dto.setScanDateTime(attendanceRecord.getScanDateTime());
        dto.setLocationName(attendanceRecord.getLocation().getName());
        dto.setLocationTypeName(attendanceRecord.getLocation().getLocationType().getType());
        dto.setSessionDate(attendanceRecord.getSession().getDate());
        dto.setSessionStartTime(attendanceRecord.getSession().getStartTime());
        dto.setSessionEndTime(attendanceRecord.getSession().getEndTime());
        dto.setSessionName(attendanceRecord.getSession().getName());
        return dto;
    }

    public static List<AttendanceRecordDTOResponse> toDTOsResponse(List<AttendanceRecord> attendanceRecords) {
        return attendanceRecords.stream()
                .map(AttendanceRecordMapper::toDTOResponse)
                .collect(Collectors.toList());
    }
//    public static AttendanceRecord DTORequestToAttendanceRecord(AttendanceRecordDTORequest attendanceRecordDTORequest){
//        AttendanceRecord attendanceRecord = new AttendanceRecord();
//        attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO dto = new attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO();
//        dto.setId(attendanceRecord.getId());
//        dto.setScanDateTime(attendanceRecord.getScanDateTime());
//        dto.setLactionName(attendanceRecord.getLocation().getName());
//        dto.setStudentId(attendanceRecord.getStudent().getStudentId());
//        dto.setSessionId(attendanceRecord.getSession().getId());
//        return attendanceRecord;
//    }
//
//    public static List<AttendanceRecordDTOResponse> mapToDTOList(List<AttendanceRecord> attendanceRecords) {
//        return attendanceRecords.stream()
//                .map(AttendanceDTOMapper::mapToDTO)
//                .collect(Collectors.toList());
//    }

}
