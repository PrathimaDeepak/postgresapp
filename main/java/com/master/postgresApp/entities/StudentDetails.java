package com.master.postgresApp.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STUDENT_DETAILS", schema = "college")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StudentDetails.class)
public class StudentDetails {
	
	@Id
	@Column(name = "STUDENT_ID")
	private Integer studentId;
	
	@Column(name = "TEACHER_ID")
	private Integer teacherId;
	
	@Id
	@Column(name = "SUBJECT_CODE")
	private String subjectCode;

}
