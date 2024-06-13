package attendanceProject.repository;

import attendanceProject.domain.CourseOffering;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {
}
