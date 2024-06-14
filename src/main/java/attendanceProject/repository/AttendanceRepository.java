package attendanceProject.repository;

import attendanceProject.domain.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
    List<AttendanceRecord> findByStudent_IdAndSession_CourseOffering_Id
            (Long studentId, Long courseOfferingId);
}