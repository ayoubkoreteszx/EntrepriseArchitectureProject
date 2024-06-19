package attendanceProject.controller;

import attendanceProject.controller.Dto.location.CreateLocationParameters;
import attendanceProject.controller.Dto.location.LocationDTO;
import attendanceProject.service.locationService.LocationService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationService locationService;

    @Test
    public void testFindAll() throws Exception {
        LocationDTO location1 = new LocationDTO();
        location1.setId(1L);
        location1.setName("Location1");
        LocationDTO location2 = new LocationDTO();
        location2.setId(2L);
        location2.setName("Location2");
        List<LocationDTO> locations = Arrays.asList(location1, location2);

        when(locationService.findAllLocations()).thenReturn(locations);

        mockMvc.perform(get("/locations"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].name").value("Location1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].name").value("Location2"));
    }

    @Test
    public void testFindById_Success() throws Exception {
        LocationDTO location = new LocationDTO();
        location.setId(1L);
        location.setName("Location1");

        when(locationService.findLocationById(1L)).thenReturn(location);

        mockMvc.perform(get("/locations/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Location1"));
    }

    @Test
    public void testFindById_NotFound() throws Exception {
        when(locationService.findLocationById(1L)).thenReturn(null);

        mockMvc.perform(get("/locations/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateLocation() throws Exception {
        LocationDTO location = new LocationDTO();
        location.setId(1L);
        location.setName("Location1");

        CreateLocationParameters parameters = new CreateLocationParameters();
        parameters.setId(1L);
        parameters.setName("Location1");

        when(locationService.createLocation(parameters)).thenReturn(location);

        mockMvc.perform(post("/locations")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("locationTypeId", "1")
                        .content("{\"id\": 1, \"name\": \"Location1\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("Location1"));
    }

    @Test
    public void testUpdateLocation() throws Exception {
        LocationDTO location = new LocationDTO();
        location.setId(1L);
        location.setName("UpdatedLocation");

        CreateLocationParameters parameters = new CreateLocationParameters();
        parameters.setId(1L);
        parameters.setName("UpdatedLocation");

        when(locationService.updateLocation(1L, parameters)).thenReturn(location);

        mockMvc.perform(put("/locations/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .param("locationTypeId", "1")
                        .content("{\"id\": 1, \"name\": \"UpdatedLocation\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.name").value("UpdatedLocation"));
    }

    @Test
    public void testDeleteLocation() throws Exception {
        mockMvc.perform(delete("/locations/1"))
                .andExpect(status().isNoContent());
    }
}