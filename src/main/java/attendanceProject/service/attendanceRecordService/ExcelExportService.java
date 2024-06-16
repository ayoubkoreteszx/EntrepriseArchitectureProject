package attendanceProject.service.attendanceRecordService;

import attendanceProject.domain.*;
import attendanceProject.repository.SessionRepository;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceDTOMapper;
import attendanceProject.service.attendanceRecordService.DTO.AttendanceRecordDTO;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ExcelExportService {
    CourseOffering courseOffering;
    Student student1;
    Student student2;
    List<Session> sessions;
    AttendanceRecord attendanceRecord1;
    AttendanceRecord attendanceRecord2;
    AttendanceRecord attendanceRecord3;
    AttendanceRecord attendanceRecord4;
    AttendanceRecord attendanceRecord5;
    AttendanceRecord attendanceRecord6;

    @Autowired
    private SessionRepository sessionRepository;

    @Autowired
    private AttendanceRecordService attendanceRecordService;


    public ExcelExportService(){
        Location location = new Location();
        location.setId(1L);
        courseOffering = new CourseOffering();
        courseOffering.setId(1);
        courseOffering.setStartDate(LocalDate.of(2024, 6,1));
        courseOffering.setEndDate(LocalDate.of(2024, 6, 25));

        student1 = new Student();
        student1.setStudentId("123");

        student2 = new Student();
        student2.setStudentId("223");

        sessions = courseOffering.generateSessions();

        attendanceRecord1 = new AttendanceRecord();
        attendanceRecord1.setSession(sessions.get(0));
        attendanceRecord1.setStudent(student1);
        attendanceRecord1.setLocation(location);

        attendanceRecord2 = new AttendanceRecord();
        attendanceRecord2.setSession(sessions.get(0));
        attendanceRecord2.setStudent(student2);
        attendanceRecord2.setLocation(location);

        attendanceRecord3 = new AttendanceRecord();
        attendanceRecord3.setSession(sessions.get(2));
        attendanceRecord3.setStudent(student1);
        attendanceRecord3.setLocation(location);

        attendanceRecord4 = new AttendanceRecord();
        attendanceRecord4.setSession(sessions.get(1));
        attendanceRecord4.setStudent(student2);
        attendanceRecord4.setLocation(location);


        attendanceRecord5 = new AttendanceRecord();
        attendanceRecord5.setSession(sessions.get(4));
        attendanceRecord5.setStudent(student1);
        attendanceRecord5.setLocation(location);

        attendanceRecord6 = new AttendanceRecord();
        attendanceRecord6.setSession(sessions.get(5));
        attendanceRecord6.setStudent(student1);
        attendanceRecord6.setLocation(location);

    }

    public ByteArrayInputStream exportAttendanceToExcel(Long offeringId) throws IOException {
        List<Student> students = List.of(student1, student2);
        List<AttendanceRecordDTO> attendanceRecordDTOList = AttendanceDTOMapper.mapToDTOList(List.of(
                attendanceRecord1, attendanceRecord2, attendanceRecord3,
                attendanceRecord4, attendanceRecord5, attendanceRecord6
        ));
        Map<String, Map<String, String>> data = new HashMap<>();
        for(AttendanceRecordDTO attendanceRecordDTO : attendanceRecordDTOList){
            String studentId = attendanceRecordDTO.getStudentId();
            String sessionId = attendanceRecordDTO.getSessionId();
            data.computeIfAbsent(studentId, k-> new HashMap<>())
                    .put(sessionId, attendanceRecordDTO.getScanDateTime().toString());
        }
//        printNestedMap(data);

        try (Workbook workbook = new XSSFWorkbook(); ByteArrayOutputStream out = new ByteArrayOutputStream()) {
            Sheet sheet = workbook.createSheet("Attendance Records");

            // Create header row with session IDs
            Row headerRow = sheet.createRow(0);
            headerRow.createCell(0).setCellValue("Student ID");

            int colNum = 1;

            for (Session session : sessions) {
//                System.out.println(session.getId());
                headerRow.createCell(colNum++).setCellValue(session.getId());
            }

            // Create data rows
            int rowNum = 1;
            for (Map.Entry<String, Map<String, String>> entry : data.entrySet()) {
                Row row = sheet.createRow(rowNum++);
                String studentId = entry.getKey();
                row.createCell(0).setCellValue(studentId);

                Map<String, String> attendance = entry.getValue();
                colNum = 1;
                for (Session session : sessions) {
                    row.createCell(colNum++).setCellValue(attendance.getOrDefault(session.getId(), "Absent"));
                }
            }
            workbook.write(out);
            return new ByteArrayInputStream(out.toByteArray());
        }
    }

    private static void printNestedMap(Map<String, Map<String, String>> nestedMap) {
        for (Map.Entry<String, Map<String, String>> outerEntry : nestedMap.entrySet()) {
            String outerKey = outerEntry.getKey();
            Map<String, String> innerMap = outerEntry.getValue();

            System.out.println("Course: " + outerKey);
            for (Map.Entry<String, String> innerEntry : innerMap.entrySet()) {
                String innerKey = innerEntry.getKey();
                String value = innerEntry.getValue();

                System.out.println("  Session: " + innerKey + " - Status: " + value);
            }
        }
    }

}
