package attendanceProject.repository;

import attendanceProject.domain.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<AttendanceRecord, Long> {
}
