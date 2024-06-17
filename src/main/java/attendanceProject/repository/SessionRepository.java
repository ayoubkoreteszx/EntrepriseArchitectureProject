package attendanceProject.repository;

import attendanceProject.domain.Session;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;


public interface SessionRepository extends JpaRepository<Session, String> {
    List<Session> findByCourseOffering_Id(Long id);
    @Query("select s from Session s where s.courseOffering.id =: courseOfferingId " +
            "and s.date <: currentDate")
    List<Session> findCompletedSessionByCourseOffering(Long courseOfferingId,
                                                       LocalDate currentDate);
}
