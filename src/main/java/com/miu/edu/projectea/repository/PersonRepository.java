package com.miu.edu.projectea.repository;

import com.miu.edu.projectea.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PersonRepository extends JpaRepository<Person, Long> {
}
