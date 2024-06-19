package EAProjectEmail.EAProjectEmail.Data;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CourseOfferingRepository extends JpaRepository<CourseOffering, Long> {

//    @Query("SELECT c FROM course_offering c WHERE c.id = :id")
    CourseOffering findById(long courseOfferingId);
}
