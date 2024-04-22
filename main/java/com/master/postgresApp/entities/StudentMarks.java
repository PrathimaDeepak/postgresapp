package com.master.postgresApp.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "STUDENT_MARKS", schema = "college")
@Data
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StudentMarks.class)
public class StudentMarks implements Serializable{
	
	private static final long serialVersionUID = 2333181099036612300L;

	@Id
	@Column(name = "STUDENT_ID")
	private Integer studentId;
	
	@Id
	@Column(name = "SUBJECT_CODE")
	private String subjectCode;
	
	@Column(name = "INTERNAL_MARKS")
	private Integer internalMarks;
	
	@Column(name = "EXTERNAL_MARKS")
	private Integer externalMarks;
	

}
