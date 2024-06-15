package attendanceProject.controller;

import attendanceProject.domain.LocationType;
import attendanceProject.service.locationTypeService.LocationTypeService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;


@SpringBootTest
@AutoConfigureMockMvc
public class LocationTypeControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private LocationTypeService locationTypeService;

    @Test
    public void testFindAll() throws Exception {
        LocationType locationType1 = new LocationType();
        locationType1.setId(1L);
        locationType1.setType("Type1");
        LocationType locationType2 = new LocationType();
        locationType2.setId(2L);
        locationType2.setType("Type2");
        List<LocationType> locationTypes = Arrays.asList(locationType1, locationType2);

        when(locationTypeService.findAllLocationTypes()).thenReturn(locationTypes);

        mockMvc.perform(get("/sys-admin/location-types"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].id").value(1L))
                .andExpect(jsonPath("$[0].type").value("Type1"))
                .andExpect(jsonPath("$[1].id").value(2L))
                .andExpect(jsonPath("$[1].type").value("Type2"));
    }

    @Test
    public void testFindById_Success() throws Exception {
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        locationType.setType("Type1");

        when(locationTypeService.findLocationTypeById(1L)).thenReturn(locationType);

        mockMvc.perform(get("/sys-admin/location-types/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("Type1"));
    }

    @Test
    public void testFindById_NotFound() throws Exception {
        when(locationTypeService.findLocationTypeById(1L)).thenReturn(null);

        mockMvc.perform(get("/sys-admin/location-types/1"))
                .andExpect(status().isNotFound());
    }

    @Test
    public void testCreateLocationType() throws Exception {
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        locationType.setType("Type1");

        when(locationTypeService.createLocationType(locationType)).thenReturn(locationType);

        mockMvc.perform(post("/sys-admin/location-types")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{ \"type\": \"Type1\"}"))
                .andExpect(status().isCreated());
//                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
//                .andExpect(jsonPath("$.id").value(1L))
//                .andExpect(jsonPath("$.type").value("Type1"));
    }

    @Test
    public void testUpdateLocationType() throws Exception {
        LocationType locationType = new LocationType();
        locationType.setId(1L);
        locationType.setType("UpdatedType");

        when(locationTypeService.updateLocationType(1L, locationType)).thenReturn(locationType);

        mockMvc.perform(put("/sys-admin/location-types/1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"id\": 1, \"type\": \"UpdatedType\"}"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.type").value("UpdatedType"));
    }

    @Test
    public void testDeleteLocationType() throws Exception {
        mockMvc.perform(delete("/sys-admin/location-types/1"))
                .andExpect(status().isNoContent());
    }
}