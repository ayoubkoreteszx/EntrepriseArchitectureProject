package attendanceProject.controller;

import attendanceProject.controller.dto.student.StudentRequest;
import attendanceProject.domain.Faculty;
import attendanceProject.domain.Student;
import attendanceProject.service.personService.PersonService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.mockito.InjectMocks;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.reactive.function.client.WebClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import reactor.core.publisher.Mono;
import static org.mockito.Mockito.when;
import java.util.Arrays;
import java.util.List;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentControllerTest {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private PersonService studentService;

    @MockBean
    private WebClient webClientMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersSpec requestHeadersSpecMock;

    @SuppressWarnings("rawtypes")
    @Mock
    private WebClient.RequestHeadersUriSpec requestHeadersUriSpecMock;
    WebClient.ResponseSpec responseSpec ;
    @InjectMocks
    private StudentController studentController;


    @BeforeEach
    @SuppressWarnings("unchecked")
    public void setUp() {


        // Set up the WebClient mock chain
        responseSpec = Mockito.mock(WebClient.ResponseSpec.class);
        Mockito.when(webClientMock.get()).thenReturn(requestHeadersUriSpecMock);
        Mockito.when(requestHeadersUriSpecMock.uri(Mockito.anyString())).thenReturn(requestHeadersSpecMock);
        Mockito.when(requestHeadersSpecMock.retrieve()).thenReturn(responseSpec);


    }

    @Test
    public void testFindAll() throws Exception {
        Student student1 = new Student();
        student1.setId(1L);
        student1.setStudentId("1L");
        student1.setFirstName("Student1");
        Student student2 = new Student();
        student2.setId(2L);
        student2.setStudentId("2L");
        student2.setFirstName("Student2");

        Faculty faculty1 = new Faculty();
        faculty1.setId(1L);
        faculty1.setFirstName("John Doe");
        faculty1.setLastName("Doe");
        faculty1.setHobbies(List.of("Reading", "Swimming"));
        student1.setAdvisor(faculty1);
        student2.setAdvisor(faculty1);
        List<Student> students = Arrays.asList(student1, student2);

        when(studentService.getAlStudents()).thenReturn(students);


        mockMvc.perform(get("/student/all"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$[0].studentId").value("1L"))
                .andExpect(jsonPath("$[0].firstName").value("Student1"))
                .andExpect(jsonPath("$[0].advisor.firstName").value("John Doe"))
                .andExpect(jsonPath("$[0].advisor.lastName").value("Doe"))
                .andExpect(jsonPath("$[0].advisor.hobbies[0]").value("Reading"))
                .andExpect(jsonPath("$[0].advisor.hobbies[1]").value("Swimming"))
                .andExpect(jsonPath("$[1].studentId").value("2L"))
                .andExpect(jsonPath("$[1].firstName").value("Student2"));
    }
    @Test
    public void testFindById_Success() throws Exception {
        Student student = new Student();
        student.setId(1L);
        student.setStudentId("1L");
        student.setFirstName("Student1");

        Faculty faculty = new Faculty();
        faculty.setId(1L);
        faculty.setFirstName("John Doe");
        faculty.setLastName("Doe");
        faculty.setHobbies(List.of("Reading", "Swimming"));
        student.setAdvisor(faculty);

        when(studentService.getStudentById(1L)).thenReturn(student);

        mockMvc.perform(get("/student/student/1"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.studentId").value("1L"))
                .andExpect(jsonPath("$.firstName").value("Student1"))
                .andExpect(jsonPath("$.advisor.firstName").value("John Doe"))
                .andExpect(jsonPath("$.advisor.lastName").value("Doe"))
                .andExpect(jsonPath("$.advisor.hobbies[0]").value("Reading"))
                .andExpect(jsonPath("$.advisor.hobbies[1]").value("Swimming"));
    }
    @Test
    public void testFindById_NotFound() throws Exception {
        when(studentService.getStudentById(1L)).thenReturn(null);

        mockMvc.perform(get("/student/student/1"))
                .andExpect(status().isNotFound());
    }
    @Test
    public void testCreateStudent() throws Exception {
        //Arrange
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setAdvisorId(1L);
        studentRequest.setFirstName("John Doe");
        Faculty mockFaculty = new Faculty();
        mockFaculty.setId(1L);
        mockFaculty.setFirstName("Dr. Smith");
        //Act

        Mockito.when(responseSpec.bodyToMono(Mockito.eq(Faculty.class))).thenReturn(Mono.just(mockFaculty));

        String studentRequestJson = new ObjectMapper().writeValueAsString(studentRequest);
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/student/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.firstName").value("John Doe"))
                .andExpect(jsonPath("$.advisorId").value(1L));

        Mockito.verify(studentService, Mockito.times(1)).addPerson(Mockito.any(Student.class));
    }
    @Test
    public void testCreateStudent_FacultyNotFound() throws Exception {
        //Arrange
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setAdvisorId(1L);
        studentRequest.setFirstName("John Doe");
        //Act
        Mockito.when(responseSpec.bodyToMono(Mockito.eq(Faculty.class))).thenReturn(Mono.empty());

        String studentRequestJson = new ObjectMapper().writeValueAsString(studentRequest);
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.post("/student/addStudent")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentRequestJson))
                .andExpect(status().isNotFound());

        Mockito.verify(studentService, Mockito.times(0)).addPerson(Mockito.any(Student.class));
    }
    @Test
    public void testUpdateStudent_Success() throws Exception {
        // Arrange
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(1L);
        studentRequest.setAdvisorId(1L);
        studentRequest.setFirstName("John Doe");

        Student expectedStudent = new Student();
        expectedStudent.setId(1L);
        expectedStudent.setStudentId("1L");
        expectedStudent.setFirstName("John Doe");
        Faculty mockFaculty = new Faculty();
        mockFaculty.setId(1L);
        mockFaculty.setFirstName("Dr. Smith");
        expectedStudent.setAdvisor(mockFaculty);

        Mockito.when(studentService.getStudentById(studentRequest.getId())).thenReturn(expectedStudent);

        Mockito.doNothing().when(studentService).updatePerson(Mockito.any(Student.class));
        Mockito.when(responseSpec.bodyToMono(Mockito.eq(Faculty.class))).thenReturn(Mono.just(mockFaculty));
        String studentRequestJson = new ObjectMapper().writeValueAsString(studentRequest);

        // Act & Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/student/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentRequestJson))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(1L))
                .andExpect(jsonPath("$.advisorId").value(1L))
                .andExpect(jsonPath("$.firstName").value("John Doe"));

        Mockito.verify(studentService, Mockito.times(1)).updatePerson(Mockito.any(Student.class));
    }
    @Test
    public void UpdateStudent_WhenStudentNotFound() throws Exception {
        //Arrange
        StudentRequest studentRequest = new StudentRequest();
        studentRequest.setId(1L);
        studentRequest.setAdvisorId(1L);
        studentRequest.setFirstName("John Doe");
        //Act
        Mockito.when(responseSpec.bodyToMono(Mockito.eq(Faculty.class))).thenReturn(Mono.empty());

        String studentRequestJson = new ObjectMapper().writeValueAsString(studentRequest);
        //Assert
        mockMvc.perform(MockMvcRequestBuilders.put("/student/update")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(studentRequestJson))
                .andExpect(status().isNotFound());

        Mockito.verify(studentService, Mockito.times(0)).updatePerson(Mockito.any(Student.class));
    }
    @Test
    public void testDeleteStudent() throws Exception {
        mockMvc.perform(delete("/student/delete/1"))
                .andExpect(status().isNoContent());

        Mockito.verify(studentService, Mockito.times(1)).deletePerson(1L);
    }
}
