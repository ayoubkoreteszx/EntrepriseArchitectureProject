package attendanceProject.service.personService;

import attendanceProject.domain.Faculty;
import attendanceProject.domain.Person;
import attendanceProject.domain.Student;

import java.util.List;

public interface PersonService {
    public void addPerson(Student person);
    public void addPerson(Faculty faculty);
    public void updatePerson(Person person);
    public void deletePerson(long id);
    public Person getPersonById(long id);
    public List<Person> getAllPersons();
}
