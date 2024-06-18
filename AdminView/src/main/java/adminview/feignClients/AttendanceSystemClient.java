package adminview.feignClients;

import adminview.controller.dto.AttendanceRecordDTOResponse;
import adminview.controller.dto.SessionResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "AttendanceSystem", url = "http://localhost:8080")
public interface AttendanceSystemClient {

    @GetMapping("/course-offerings/{offeringId}/passed-sessions")
    List<SessionResponse> allPassedSessionsForCourseOffering(@PathVariable long offeringId);

    @GetMapping("/attendance-records/course-offering/{offeringId}")
    List<AttendanceRecordDTOResponse> getAttendanceRecordsForCourseOffering(@PathVariable long offeringId);
}
