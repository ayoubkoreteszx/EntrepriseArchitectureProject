package attendanceProject.service.personService;

import attendanceProject.domain.Faculty;
import attendanceProject.domain.Person;
import attendanceProject.domain.Student;

import java.util.List;

public interface PersonService {
    public void addPerson(Student person);
    public void addPerson(Faculty faculty);
    public void updatePerson(Person student);
    public void deletePerson(long id);
    public Student getStudentById(long id);
    public List<Student> getAlStudents();
    public Faculty getFacultyById(long id);
    public List<Faculty> getAllFaculties();
}
