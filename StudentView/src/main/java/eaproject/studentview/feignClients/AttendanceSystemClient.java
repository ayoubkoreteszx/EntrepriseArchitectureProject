package eaproject.studentview.feignClients;

import eaproject.studentview.controller.dto.AttendanceRecordDTOResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "AttendanceSystem", url = "http://localhost:8080")
public interface AttendanceSystemClient{
    @GetMapping("/attendance-records/student/{studentId}/course-offering/{offeringId}")
    List<AttendanceRecordDTOResponse> getStudentAttendanceRecordsForCourseOffering(
            @PathVariable long studentId, @PathVariable long offeringId);
    @GetMapping("/attendance-records/student/{studentId}")
    List<AttendanceRecordDTOResponse> getStudentAttendanceRecords(@PathVariable long studentId);
}

