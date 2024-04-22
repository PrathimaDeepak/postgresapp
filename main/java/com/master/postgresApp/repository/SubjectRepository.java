package com.master.postgresApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.master.postgresApp.entities.Subject;

@Repository
public interface SubjectRepository extends JpaRepository<Subject, String>{

}
