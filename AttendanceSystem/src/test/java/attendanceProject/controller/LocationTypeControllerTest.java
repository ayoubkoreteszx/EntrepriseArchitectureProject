package attendanceProject.controller;

import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.List;

import attendanceProject.controller.dto.locationType.LocationTypeRequest;
import attendanceProject.controller.dto.locationType.LocationTypeResponse;
import attendanceProject.domain.LocationType;
import attendanceProject.service.locationTypeService.LocationTypeService;
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
public class LocationTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Mock
    private LocationTypeService locationTypeService;

    @InjectMocks
    private LocationTypeController locationTypeController;
    private LocationTypeResponse locationType1;
    private LocationTypeResponse locationType2;
    private LocationTypeRequest parameters;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(locationTypeController).build();

        locationType1 = new LocationTypeResponse();
        locationType1.setId(1);
        locationType1.setType("Type A");

        locationType2 = new LocationTypeResponse();
        locationType2.setId(2);
        locationType2.setType("Type B");

        parameters = new LocationTypeRequest();
        parameters.setType("Type A");
    }

    @Test
    public void testFindAll() throws Exception {
        List<LocationTypeResponse> locationTypes = Arrays.asList(
        locationType1, locationType2
        );

        when(locationTypeService.findAllLocationTypes()).thenReturn(locationTypes);

        mockMvc.perform(get("/location-types")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].type").value("Type A"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].type").value("Type B"));

        verify(locationTypeService, times(1)).findAllLocationTypes();
    }

    @Test
    public void testFindById() throws Exception {

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(locationType1);

        mockMvc.perform(get("/location-types/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("Type A"));

        verify(locationTypeService, times(1)).findLocationTypeById(1L);
    }

    @Test
    public void testFindById_NotFound() throws Exception {
        when(locationTypeService.findLocationTypeById(1L)).thenReturn(null);

        mockMvc.perform(get("/location-types/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNotFound());

        verify(locationTypeService, times(1)).findLocationTypeById(1L);
    }

    @Test
    public void testCreateLocationType() throws Exception {

        when(locationTypeService.createLocationType(parameters)).thenReturn(locationType1);

        mockMvc.perform(post("/location-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parameters)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("Type A"));

        verify(locationTypeService, times(1)).createLocationType(parameters);
    }

    @Test
    public void testUpdateLocationType() throws Exception {
        LocationTypeResponse updatedLocationType = new LocationTypeResponse();
        updatedLocationType.setId(1L);
        updatedLocationType.setType("Updated Type A");

        when(locationTypeService.updateLocationType(1L, parameters)).thenReturn(updatedLocationType);

        mockMvc.perform(put("/location-types/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(asJsonString(parameters)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("Updated Type A"));

        verify(locationTypeService, times(1)).updateLocationType(1L, parameters);
    }

    @Test
    public void testDeleteLocationType() throws Exception {
        doNothing().when(locationTypeService).deleteLocationType(1L);

        mockMvc.perform(delete("/location-types/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isNoContent());

        verify(locationTypeService, times(1)).deleteLocationType(1L);
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