package attendanceProject.repository;

import attendanceProject.domain.AttendanceRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AttendanceRecordRepository extends JpaRepository<AttendanceRecord, Long> {
    @Query("select a from AttendanceRecord a where a.student.id =:studentId and " +
            "a.session.courseOffering.id =:courseOfferingId")
    List<AttendanceRecord> findByStudent_IdAndSession_CourseOffering_Id
            (@Param("studentId") Long studentId, @Param("courseOfferingId") Long courseOfferingId);
    List<AttendanceRecord> findBySession_CourseOffering_Id(Long courseOfferingId);
    List<AttendanceRecord> findByStudent_Id(long studentId);
}