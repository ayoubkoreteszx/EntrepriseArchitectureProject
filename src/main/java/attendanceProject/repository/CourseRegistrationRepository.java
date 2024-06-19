package attendanceProject.repository;

import attendanceProject.domain.CourseRegistration;
import attendanceProject.domain.Student;
import jakarta.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseRegistrationRepository extends JpaRepository<CourseRegistration, Long> {
    List<CourseRegistration> findByCourseOfferingId(Long courseOfferingId);

    List<CourseRegistration> findAllByStudentId(long studentId);

    List<Student> findAllByCourseOfferingId(long courseOfferingId);


    @Modifying
    @Transactional
    @Query("update CourseRegistration c set c.courseOffering.id = :id where c.id = :courseRegistrationId")
    void updateCourseRegistration(long courseRegistrationId, long id);

    @Query("delete from CourseRegistration c where c.student.id = :studentId")
    void deleteAllByStudentId(long studentId);
}
