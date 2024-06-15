package attendanceProject.controller;

import attendanceProject.domain.CourseOffering;
import attendanceProject.service.courseOfferingService.CourseOfferingServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * These tests run the AccountController using the MockMVC framework.
 * The server does not need to be running.
 */
@WebMvcTest(CourseOfferingController.class) // WebMvcTest = MockMvc, @MockBean
public class CourseOfferingControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private CourseOfferingServiceImpl courseOfferingService;

    @Test
    void getCourseOfferingsById() throws Exception {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setCapacity(100);
        given(courseOfferingService.getCourseOfferingById(anyLong())).willReturn(courseOffering);

        mockMvc.perform(get("/sys-admin/course-offerings/0"))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("capacity").value(100));

        verify(courseOfferingService).getCourseOfferingById(anyLong());
    }

    @Test
    void getCourseOfferingsByIdFail() throws Exception {

        given(courseOfferingService.getCourseOfferingById(anyLong()))
                .willReturn(null);

        mockMvc.perform(get("/sys-admin/course-offerings/9999"))
                .andExpect(status().isNotFound());
        verify(courseOfferingService).getCourseOfferingById(anyLong());
    }

    @Test
    void getAllCourseOfferings() throws Exception {
        CourseOffering courseOffering1 = new CourseOffering();
        courseOffering1.setId(1L);
        CourseOffering courseOffering2 = new CourseOffering();
        courseOffering2.setId(2L);
        List<CourseOffering> courseOfferings = List.of(courseOffering1, courseOffering2);
        given(courseOfferingService.findAllCourseOfferings()).willReturn(courseOfferings);

        mockMvc.perform(get("/sys-admin/course-offerings"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$..id").isArray());
    }

    @Test
    void createCourseOffering() throws Exception {

        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setId(121L);

        given(courseOfferingService.createCourseOffering(any(CourseOffering.class)))
                .willReturn(courseOffering);

        mockMvc.perform(post("/sys-admin/course-offerings")
                .contentType(MediaType.APPLICATION_JSON)
                .content(asJsonString(courseOffering)))
                .andExpect(status().isCreated())
                .andExpect(header().string("Location",
                        "http://localhost/sys-admin/course-offerings/" + courseOffering.getId()));

        verify(courseOfferingService).createCourseOffering(any(CourseOffering.class));
    }

    @Test
    void updateCourseOffering() throws Exception {
        CourseOffering courseOfferingUpdated = new CourseOffering();
        courseOfferingUpdated.setId(121L);

        given(courseOfferingService.updateCourseOffering(121L, courseOfferingUpdated))
                .willReturn(courseOfferingUpdated);

        mockMvc.perform(put("/sys-admin/course-offerings/121")
                .contentType(MediaType.APPLICATION_JSON)
                .content(new ObjectMapper().writeValueAsString(courseOfferingUpdated)))
                .andDo(print())
                .andExpect(status().isNoContent());

        verify(courseOfferingService).updateCourseOffering(121L, courseOfferingUpdated);
    }

    @Test
    void deleteCourseOffering() throws Exception {
        CourseOffering courseOffering = new CourseOffering();
        courseOffering.setId(121L);

        given(courseOfferingService.getCourseOfferingById(anyLong())).willReturn(courseOffering);

        mockMvc.perform(delete("/sys-admin/course-offerings/121"))
                .andExpect(status().isNoContent());

        verify(courseOfferingService).getCourseOfferingById(anyLong());
    }

    protected static String asJsonString(final Object obj) {
        try {
            final ObjectMapper mapper = new ObjectMapper();
            final String jsonContent = mapper.writeValueAsString(obj);
            return jsonContent;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
