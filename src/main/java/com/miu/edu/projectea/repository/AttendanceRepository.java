package com.miu.edu.projectea.repository;

import com.miu.edu.projectea.domain.Attendance;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
}
