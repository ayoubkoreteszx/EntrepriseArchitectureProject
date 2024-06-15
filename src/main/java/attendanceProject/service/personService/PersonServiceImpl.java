package attendanceProject.service.personService;

import attendanceProject.domain.Faculty;
import attendanceProject.domain.Person;
import attendanceProject.domain.Student;
import attendanceProject.repository.PersonRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonServiceImpl implements PersonService{
    @Autowired
    private PersonRepository personRepository;
    @Override
    public void addPerson(Student person) {
     //   Faculty faculty = personRepository.findById(person.getFaculty().getId()).isPresent()?personRepository.findById(person.getFaculty().getId()).get():null;
        personRepository.save(person);
    }
    @Override
    public void addPerson(Faculty faculty) {
        personRepository.save(faculty);
    }

    @Override
    public void updatePerson(Person person) {
     personRepository.save(person);
    }

    @Override
    public void deletePerson(long personId) {
          Person person = personRepository.findById(personId).isPresent()?personRepository.findById(personId).get():null;
            if(person==null){
                return;
            }
            personRepository.delete(person);
    }

    @Override
    public Person getPersonById(long id) {

        return personRepository.findById(id).isPresent()?personRepository.findById(id).get():null;
    }

    @Override
    public List<Person> getAllPersons() {
        return personRepository.findAll();
    }
}
