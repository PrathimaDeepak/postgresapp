package com.master.postgresApp.controller;

import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.master.postgresApp.entities.Student;
import com.master.postgresApp.exceptions.StudentException;
import com.master.postgresApp.repository.StudentRepository;

@RestController
@RequestMapping("/student")
public class StudentController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentController.class);
	
	@Autowired
	private StudentRepository studentRepository;
	
	@GetMapping("/list")
	public List<Student> getStudentList(){
		List<Student> studentList = studentRepository.findAll();
		return studentList;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getStudentById(@PathVariable("id") final Integer id) {
		try {
		if(id == null) {
			throw new StudentException("Invalid Id", StudentException.ExceptionType.INVALID_ID);
		}
		Optional<Student> student = studentRepository.findById(id);
		if(student.isPresent()) {
			return ResponseEntity.ok(student.get());
		} else {
			throw new StudentException("Student does not exist", StudentException.ExceptionType.STUDENT_DOES_NOT_EXIST);
		}
		
		} catch (StudentException e) {
			LOGGER.error("Exception caused while retrieving student details : " + e);
			return ResponseEntity.badRequest().body(e);
		}
	}
	
	@PostMapping
	public ResponseEntity<?> upsertStudentDetails(@RequestBody Student student){
		if(student.getStudentId() != null) {
			LOGGER.info("Student already exists, updating student details");
		}
		Student updatedRecord = studentRepository.saveAndFlush(student);
		return ResponseEntity.ok(updatedRecord);
	}
	
	@DeleteMapping("/{id}")
	public void deleteStudent(@PathVariable("id") final Integer id) {
		try {
			if(id == null) {
				throw new StudentException("Invalid Id", StudentException.ExceptionType.INVALID_ID);
			}
			Optional<Student> student = studentRepository.findById(id);
			if(student.isPresent()) {
				studentRepository.deleteById(id);
			} else {
				throw new StudentException("Student does not exist", StudentException.ExceptionType.STUDENT_DOES_NOT_EXIST);
			}
			
			} catch (StudentException e) {
				LOGGER.error("Exception caused while retrieving student details : " + e);
			}
	}

}
