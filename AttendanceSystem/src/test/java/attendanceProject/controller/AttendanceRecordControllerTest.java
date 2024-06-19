package attendanceProject.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import attendanceProject.controller.dto.attendance.AttendanceRecordDTORequest;
import attendanceProject.controller.dto.attendance.AttendanceRecordDTOResponse;
import attendanceProject.service.attendanceRecordService.AttendanceRecordService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.fasterxml.jackson.databind.ObjectMapper;

@SpringBootTest
@AutoConfigureMockMvc
public class AttendanceRecordControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private AttendanceRecordService attendanceRecordService;

    @InjectMocks
    private AttendanceRecordController attendanceRecordController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(attendanceRecordController).build();
    }

    @Test
    public void testGetAttendanceRecordById() throws Exception {
        AttendanceRecordDTOResponse attendanceRecordDTO = new AttendanceRecordDTOResponse();
        attendanceRecordDTO.setId(1L);

        when(attendanceRecordService.getAttendanceRecordById(1L)).thenReturn(attendanceRecordDTO);

        mockMvc.perform(get("/attendance-records/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L));
        // add other expectations for the properties

        verify(attendanceRecordService, times(1)).getAttendanceRecordById(1L);
    }

    @Test
    public void testGetAttendanceRecordById_NotFound() throws Exception {
        when(attendanceRecordService.getAttendanceRecordById(1L)).thenReturn(null);

        mockMvc.perform(get("/attendance-records/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(attendanceRecordService, times(1)).getAttendanceRecordById(1L);
    }

    @Test
    public void testAddAttendanceRecord() throws Exception {
        AttendanceRecordDTOResponse attendanceRecordDTO = new AttendanceRecordDTOResponse();
        attendanceRecordDTO.setId(1L);
        // set other properties as needed

        when(attendanceRecordService.createAttendanceRecord(any(AttendanceRecordDTORequest.class)))
                .thenReturn(attendanceRecordDTO);

        mockMvc.perform(post("/attendance-records")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(attendanceRecordDTO)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L));
        // add other expectations for the properties

        verify(attendanceRecordService, times(1)).createAttendanceRecord(any(AttendanceRecordDTORequest.class));
    }

    @Test
    public void testDeleteAttendanceRecord() throws Exception {
        doNothing().when(attendanceRecordService).deleteAttendanceRecord(1L);

        mockMvc.perform(delete("/attendance-records/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(attendanceRecordService, times(1)).deleteAttendanceRecord(1L);
    }

    @Test
    public void testGetAllAttendanceRecords() throws Exception {
        AttendanceRecordDTOResponse attendanceRecordDTO1 = new AttendanceRecordDTOResponse();
        attendanceRecordDTO1.setId(1L);
        AttendanceRecordDTOResponse attendanceRecordDTO2 = new AttendanceRecordDTOResponse();
        attendanceRecordDTO2.setId(2L);
        List<AttendanceRecordDTOResponse> attendanceRecords = Arrays.asList(
              attendanceRecordDTO1, attendanceRecordDTO2
        );

        when(attendanceRecordService.getAllAttendanceRecords()).thenReturn(attendanceRecords);

        mockMvc.perform(get("/attendance-records")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[1].id").value(2L));
        // add other expectations for the properties

        verify(attendanceRecordService, times(1)).getAllAttendanceRecords();
    }

    // Utility method to convert object to JSON string
    private static String asJsonString(final Object obj) {
        try {
            return new ObjectMapper().writeValueAsString(obj);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}