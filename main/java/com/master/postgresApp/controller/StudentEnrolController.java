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
import com.master.postgresApp.entities.StudentDetails;
import com.master.postgresApp.entities.Subject;
import com.master.postgresApp.entities.Teacher;
import com.master.postgresApp.repository.StudentDetailsRepository;
import com.master.postgresApp.repository.StudentRepository;
import com.master.postgresApp.repository.SubjectRepository;
import com.master.postgresApp.repository.TeacherRepository;

@RestController
@RequestMapping("/enrol")
public class StudentEnrolController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(StudentEnrolController.class);
	
	@Autowired
	private StudentDetailsRepository studentDetailsRepository;
	
	@Autowired
	private StudentRepository studentRepository;
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@PostMapping
	public ResponseEntity<?> enrolStudent(@RequestBody StudentDetails studentDetails) {
		try {
			Optional<Student> studentRecord = studentRepository.findById(studentDetails.getStudentId());
			if(studentRecord.isEmpty()) {
				throw new Exception("Student Record does not exist");
			}
			
			Optional<Teacher> teacherRecord = teacherRepository.findById(studentDetails.getTeacherId());
			if(teacherRecord.isEmpty()) {
				throw new Exception("Teacher Record does not exist");
			}
			
			Optional<Subject> subjectRecord = subjectRepository.findById(studentDetails.getSubjectCode());
			if(subjectRecord.isEmpty()) {
				throw new Exception("Subject Record does not exist");
			}
			
			StudentDetails updatedRecord = studentDetailsRepository.saveAndFlush(studentDetails);
			return ResponseEntity.ok(updatedRecord);
			}catch (Exception e) {
				LOGGER.error("Exception caused while upserting student details: {}", e);
				return ResponseEntity.badRequest().body(e);
		}
	}
	
	@GetMapping("/list")
	public List<StudentDetails> retrieveStudentEnrolmentDetails(){
		return studentDetailsRepository.findAll();
	}
	
	@GetMapping("/student")
	public List<StudentDetails> retrieveStudentEnrolmentDetailsByStudentId(@RequestParam Integer studentId){
		try{
			if(studentId == null) {
				throw new Exception("Invalid Id");
			}
			return studentDetailsRepository.findByStudentId(studentId);
		}catch (Exception e) {
			LOGGER.error("Exception caused while retrieving student details: {}", e);
			return null;
		}
			
	}
	
	@GetMapping("/teacher")
	public List<StudentDetails> retrieveStudentEnrolmentDetailsByTeacherId(@RequestParam Integer teacherId){
		try{
			if(teacherId == null) {
				throw new Exception("Invalid Id");
			}
			return studentDetailsRepository.findByTeacherId(teacherId);
		}catch (Exception e) {
			LOGGER.error("Exception caused while retrieving student details: {}", e);
			return null;
		}
			
	}
	
	@GetMapping("/subject")
	public List<StudentDetails> retrieveStudentEnrolmentDetailsBySubjectCode(@RequestParam String subjectCode){
		try{
			if(subjectCode == null) {
				throw new Exception("Invalid Id");
			}
			return studentDetailsRepository.findBySubjectCode(subjectCode);
		}catch (Exception e) {
			LOGGER.error("Exception caused while retrieving student details: {}", e);
			return null;
		}
			
	}
	
	@DeleteMapping
	public void unenrolStudent(@RequestParam final Integer studentId, @RequestParam final String subjectCode) {
		try {
			Optional<Student> studentRecord = studentRepository.findById(studentId);
			if(studentRecord.isEmpty()) {
				throw new Exception("Student Record does not exist");
			}
			
			Optional<Subject> subjectRecord = subjectRepository.findById(subjectCode);
			if(subjectRecord.isEmpty()) {
				throw new Exception("Subject Record does not exist");
			}
			
			List<StudentDetails> studentDetailsList = studentDetailsRepository.findByStudentIdAndSubjectCode(studentId, subjectCode);
			if(studentDetailsList.size() <= 0) {
				throw new Exception("Student Enrolment details not found");
			}
			studentDetailsRepository.deleteAll(studentDetailsList);
			} catch (Exception e) {
				LOGGER.error("Exception caused while deleting student details : " + e);
			}
	}

}
