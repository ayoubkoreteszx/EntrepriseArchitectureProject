package attendanceProject.service.personService;

import attendanceProject.domain.Faculty;
import attendanceProject.domain.Student;
import attendanceProject.repository.PersonRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class PersonServiceTest {
    @Mock
    private PersonRepository personRepository;

    @InjectMocks
    private PersonServiceImpl personService;

    private Faculty faculty1;
    private Faculty faculty2;

    @BeforeEach
    void setUp() {
        faculty1 = new Faculty();
        faculty1.setId(1L);
        faculty1.setFirstName("John Doe");

        faculty2 = new Faculty();
        faculty2.setId(2L);
        faculty2.setFirstName("Jane Smith");
    }

    @Test
    void testGetAllFaculties_WhenFacultiesExits() {
        // Arrange
        List<Faculty> faculties = Arrays.asList(faculty1, faculty2);
        when(personRepository.findAllFaculty()).thenReturn(faculties);

        // Act
        List<Faculty> result = personService.getAllFaculties();

        // Assert
        assertEquals(2, result.size());
        assertEquals(faculty1, result.get(0));
        assertEquals(faculty2, result.get(1));
    }
    @Test
    void testGetAllFaculties_WhenNoFacultiesExits() {
        // Arrange
        when(personRepository.findAllFaculty()).thenReturn(new ArrayList<>());

        // Act
        List<Faculty> result = personService.getAllFaculties();

        // Assert
        assertEquals(0, result.size());
    }
    @Test
    void testGetFacultyById_FacultyExists() {
        // Arrange
        when(personRepository.findByFacultyId(1L)).thenReturn(faculty1);

        // Act
        Faculty result = personService.getFacultyById(1L);

        // Assert
        assertEquals(faculty1, result);
    }
    @Test
    void testGetFacultyById_FacultyDoesNotExist() {
        // Arrange
        when(personRepository.findByFacultyId(1L)).thenReturn(null);

        // Act
        Faculty result = personService.getFacultyById(1L);

        // Assert
        assertEquals(null, result);
    }
    @Test
    void testDeletePerson_PersonExists() {
        // Arrange
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.of(faculty1));

        // Act
        personService.deletePerson(1L);

        // Assert
        Mockito.verify(personRepository, Mockito.times(1)).delete(faculty1);
    }
    @Test
    void testDeletePerson_PersonDoesNotExist() {
        // Arrange
        when(personRepository.findById(1L)).thenReturn(java.util.Optional.empty());

        // Act
        personService.deletePerson(1L);

        // Assert
        Mockito.verify(personRepository, Mockito.times(0)).delete(faculty1);
    }
    @Test
    void testUpdatePerson_PersonExists() {
        // Arrange
        when(personRepository.save(faculty1)).thenReturn(faculty1);

        // Act
        personService.updatePerson(faculty1);

        // Assert
        Mockito.verify(personRepository, Mockito.times(1)).save(faculty1);
    }
    @Test
    void testUpdatePerson_PersonDoesNotExist() {
        // Arrange
        when(personRepository.save(faculty1)).thenReturn(faculty1);

        // Act
        personService.updatePerson(faculty1);

        // Assert
        Mockito.verify(personRepository, Mockito.times(1)).save(faculty1);
    }

    @Test
    void getStudentById_WhenStudent_DontExist() {
        // Arrange
        when(personRepository.findByStudentId(1L)).thenReturn(null);

        // Act
        Student result = personService.getStudentById(1L);

        // Assert
        assertEquals(null, result);
    }
    @Test
    void getStudentById_WhenStudentExist(){
        // Arrange
        Student student = new Student();
        student.setId(1L);
        student.setFirstName("John Doe");

        when(personRepository.findByStudentId(1L)).thenReturn(student);

        // Act
        Student result = personService.getStudentById(1L);

        // Assert
        assertEquals(student, result);
    }
    @Test
    void testGetAllStudents_WhenStudentsExits() {
        // Arrange
        Student student1 = new Student();
        student1.setId(1L);
        student1.setFirstName("John Doe");
        student1.setLastName("Doe");
        student1.setAdvisor(faculty1);
        Student student2 = new Student();
        student2.setId(2L);
        student2.setFirstName("Jane Smith");
        student2.setLastName("Smith");

        List<Student> students = Arrays.asList(student1, student2);
        when(personRepository.findAllStudent()).thenReturn(students);

        // Act
        List<Student> result = personService.getAlStudents();

        // Assert
        assertEquals(2, result.size());
        assertEquals(student1, result.get(0));
        assertEquals(student2, result.get(1));
    }
}