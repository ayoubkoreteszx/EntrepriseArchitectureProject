package attendanceProject.repository;

import attendanceProject.domain.Faculty;
import attendanceProject.domain.Person;
import attendanceProject.domain.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface PersonRepository extends JpaRepository<Person, Long> {
    @Query("SELECT p FROM Person p  join fetch Student s on s.id=p.id WHERE s.id = :studentId")
    Student findByStudentId(Long studentId);
    @Query("SELECT p FROM Person p join fetch Faculty  f on p.id=f.id WHERE f.id = :facultyId")
    Faculty findByFacultyId(Long facultyId);

    @Query("SELECT s FROM Student s ")
    List<Student> findAllStudent();
    @Query("SELECT f FROM Faculty f ")
    List<Faculty> findAllFaculty();

    @Query("SELECT p FROM Person p where p.username = :username")
    Optional<Person> findByUsername(@Param("username") String username);
}
