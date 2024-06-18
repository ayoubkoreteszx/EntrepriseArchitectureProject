package attendanceProject.repository;

import attendanceProject.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

    @Query("select c from CourseOffering c where c.startDate <= :date and c.endDate >= :date")
    List<CourseOffering> findAllCourseOfferingsByDate(@Param("date") LocalDate date);
}
