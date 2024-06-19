package adminview.service;

import adminview.controller.dto.AttendanceRecordDTOResponse;
import adminview.controller.dto.SessionResponse;
import adminview.feignClients.AttendanceSystemClient;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelService {

    @Autowired
    AttendanceSystemClient client;

    public ByteArrayInputStream exportAttendanceToExcel(Long offeringId) throws IOException {
        List<AttendanceRecordDTOResponse> attendanceRecordDTOList = client.getAttendanceRecordsForCourseOffering(offeringId);
        List<SessionResponse> sessions = client.allPassedSessionsForCourseOffering(offeringId);
        Map<String, Map<String, String>> data = new HashMap<>();
        for(AttendanceRecordDTOResponse attendanceRecordDTO : attendanceRecordDTOList){
            String studentId = attendanceRecordDTO.getStudentId();
            String sessionId = attendanceRecordDTO.getSessionName();
            data.computeIfAbsent(studentId, k-> new HashMap<>())
                    .put(sessionId, attendanceRecordDTO.getScanDateTime().toLocalTime().toString());
        }
//        printNestedMap(data);


        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Attendance Records");

            // Create header row with session IDs
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Student ID");

            int colNum = 1;

            for (SessionResponse session : sessions) {
//                System.out.println(session.getId());
                headerRow.createCell(colNum++).setCellValue(session.getName());
            }

            // Create data rows
            int rowNum = 1;
            for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
                Row row = sheet.createRow(rowNum++);
                String studentId = entry.getKey();
                row.createCell(0).setCellValue(studentId);

                Map<String, String> attendance = entry.getValue();
                colNum = 1;
                for (SessionResponse session : sessions) {
                    row.createCell(colNum++).setCellValue(attendance.getOrDefault(session.getName(), "Absent"));
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }
}
