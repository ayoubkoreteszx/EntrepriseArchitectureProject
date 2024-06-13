package com.miu.edu.projectea.repository;

import com.miu.edu.projectea.domain.Course;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CourseRepository extends JpaRepository<Course, Long> {
}
