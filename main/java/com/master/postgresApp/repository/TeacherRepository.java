package com.master.postgresApp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.master.postgresApp.entities.Teacher;

@Repository
public interface TeacherRepository extends JpaRepository<Teacher, Integer>{

}
