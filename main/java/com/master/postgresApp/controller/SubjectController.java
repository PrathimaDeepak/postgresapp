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

import com.master.postgresApp.entities.Subject;
import com.master.postgresApp.repository.SubjectRepository;

@RestController
@RequestMapping("/subject")
public class SubjectController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(SubjectController.class);
	
	@Autowired
	private SubjectRepository subjectRepository;
	
	@GetMapping("/list")
	public List<Subject> getSubjectList(){
		List<Subject> subjectList = subjectRepository.findAll();
		return subjectList;
	}
	
	@GetMapping("/{code}")
	public ResponseEntity<?> getSubjectById(@PathVariable("code") String subjectCode){
		try {
			if(subjectCode == null) {
				throw new Exception("Invalid Subject Code");
			}
			Optional<Subject> subject = subjectRepository.findById(subjectCode);
			if(subject.isPresent()) {
				return ResponseEntity.ok(subject.get());
			} else {
				throw new Exception("Subject does not exist");
			}
			
			} catch (Exception e) {
				LOGGER.error("Exception caused while retrieving subject details : " + e);
				return ResponseEntity.badRequest().body(e);
			}
	}
	
	@PostMapping
	public ResponseEntity<?> upsertSubjectDetails(@RequestBody Subject subject) {
		if(subject.getSubjectCode() != null) {
			LOGGER.info("Subject Entry already exists. Updating the details");
		}
		Subject updatedRecord = subjectRepository.saveAndFlush(subject);
		return ResponseEntity.ok(updatedRecord);
	}
	
	@DeleteMapping("/{code}")
	public void deleteSubject(@PathVariable("code") final String subjectCode) {
		try {
			if(subjectCode == null) {
				throw new Exception("Subject Code does not exist");
			}
			Optional<Subject> subject = subjectRepository.findById(subjectCode);
			if(subject.isPresent()) {
				subjectRepository.deleteById(subjectCode);
			} else {
				throw new Exception("Subject does not exist");
			}
			
			} catch (Exception e) {
				LOGGER.error("Exception caused while retrieving subject details : " + e);
			}
	}

}
