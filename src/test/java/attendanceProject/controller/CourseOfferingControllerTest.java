package attendanceProject.controller;

import attendanceProject.service.courseOfferingService.CourseOfferingServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

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
    public void getAllCourseOfferings() throws Exception {

    }
}
