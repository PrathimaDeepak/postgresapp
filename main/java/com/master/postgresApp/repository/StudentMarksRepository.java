package com.master.postgresApp.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.master.postgresApp.entities.StudentMarks;

@Repository
public interface StudentMarksRepository extends JpaRepository<StudentMarks, Integer>{
	
	List<StudentMarks> findByStudentId(Integer studentId);
	
	List<StudentMarks> findBySubjectCode(String subjectCode);
	
	Optional<StudentMarks> findByStudentIdAndSubjectCode(Integer studentId, String subjectCode);
	
}
