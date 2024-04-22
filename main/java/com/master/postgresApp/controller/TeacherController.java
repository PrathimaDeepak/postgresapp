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

import com.master.postgresApp.entities.Teacher;
import com.master.postgresApp.repository.TeacherRepository;

@RestController
@RequestMapping("/teacher")
public class TeacherController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(TeacherController.class);
	
	@Autowired
	private TeacherRepository teacherRepository;
	
	@GetMapping("/list")
	public List<Teacher> getTeacherList() {
		List<Teacher> teacherList = teacherRepository.findAll();
		return teacherList;
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<?> getTeacherById(@PathVariable("id") Integer id){
		try {
			if(id == null) {
				throw new Exception("Invalid Id");
			}
			Optional<Teacher> teacher = teacherRepository.findById(id);
			if(teacher.isPresent()) {
				return ResponseEntity.ok(teacher.get());
			} else {
				throw new Exception("Teacher does not exist");
			}
			
			} catch (Exception e) {
				LOGGER.error("Exception caused while retrieving teacher details : " + e);
				return ResponseEntity.badRequest().body(e);
			}
	}
	
	@PostMapping
	public ResponseEntity<?> upsertTeacherDetails(@RequestBody Teacher teacher) {
		if(teacher.getTeacherId() != null) {
			LOGGER.info("Teacher Entry already exists. Updating the details");
		}
		Teacher updatedRecord = teacherRepository.saveAndFlush(teacher);
		return ResponseEntity.ok(updatedRecord);
	}
	
	@DeleteMapping("/{id}")
	public void deleteTeacher(@PathVariable("id") final Integer id) {
		try {
			if(id == null) {
				throw new Exception("Teacher does not exist");
			}
			Optional<Teacher> teacher = teacherRepository.findById(id);
			if(teacher.isPresent()) {
				teacherRepository.deleteById(id);
			} else {
				throw new Exception("Teacher does not exist");
			}
			
			} catch (Exception e) {
				LOGGER.error("Exception caused while retrieving teacher details : " + e);
			}
	}

}
