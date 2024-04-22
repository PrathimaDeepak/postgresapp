package com.master.postgresApp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.master.postgresApp.entities.StudentDetails;

@Repository
public interface StudentDetailsRepository extends JpaRepository<StudentDetails, Integer>{
	
	List<StudentDetails> findByStudentIdAndSubjectCode(Integer studentId, String subjectCode);
	
	List<StudentDetails> findByStudentId(Integer studentId);
	
	List<StudentDetails> findByTeacherId(Integer teacherId);
	
	List<StudentDetails> findBySubjectCode(String subjectCode);

}
