package com.master.postgresApp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.master.postgresApp.entities.Student;
import com.master.postgresApp.entities.StudentMarks;
import com.master.postgresApp.entities.Subject;
import com.master.postgresApp.repository.StudentMarksRepository;
import com.master.postgresApp.repository.StudentRepository;
import com.master.postgresApp.repository.SubjectRepository;

@RestController
@RequestMapping("/marks")
public class StudentMarksController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentMarksController.class);
	
	@Autowired
	private StudentMarksRepository studentMarksRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping("/list")
	public List<StudentMarks> retrieveStudentMarksList(){
		return studentMarksRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<?> upsertStudentMarks(@RequestBody StudentMarks studentMarks){
		if(studentMarks.getStudentId() != null) {
			LOGGER.info("Student already exists, updating student details");
		}
		StudentMarks updatedRecord = studentMarksRepository.saveAndFlush(studentMarks);
		return ResponseEntity.ok(updatedRecord);
	}
	
	@GetMapping("/student")
	public List<StudentMarks> retrieveStudentMarksByStudentId(@RequestParam Integer studentId){
		return studentMarksRepository.findByStudentId(studentId);
	}
	
	@GetMapping("/subject")
	public List<StudentMarks> retrieveStudentMarksBySubjectCode(@RequestParam String subjectCode){
		return studentMarksRepository.findBySubjectCode(subjectCode);
	}
	
	@DeleteMapping
	public void deleteStudentMarks(@RequestParam Integer studentId, @RequestParam String subjectCode) {
		try {
			Optional<Student> studentRecord = studentRepository.findById(studentId);
			if(studentRecord.isEmpty()) {
				throw new Exception("Student Record does not exist");
			}
			
			Optional<Subject> subjectRecord = subjectRepository.findById(subjectCode);
			if(subjectRecord.isEmpty()) {
				throw new Exception("Subject Record does not exist");
			}
			
			Optional<StudentMarks> studentMarks = studentMarksRepository.findByStudentIdAndSubjectCode(studentId, subjectCode);
			if(studentMarks.isEmpty()) {
				throw new Exception("Student Marks details not found");
			}
			studentMarksRepository.delete(studentMarks.get());
			} catch (Exception e) {
				LOGGER.error("Exception caused while deleting student marks : " + e);
			}
	}
	

}
