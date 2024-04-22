package com.master.postgresApp.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "STUDENT", schema = "college")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Student implements Serializable{
	
	private static final long serialVersionUID = 4145230844959407167L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_studentSequence")
    @SequenceGenerator(name = "id_studentSequence", sequenceName = "COLLEGE.student_student_id_seq",allocationSize = 1)
	@Column(name = "STUDENT_ID")
	private Integer studentId;
	
	@Column(name = "STUDENT_NAME", length = 50)
	private String studentName;
	
	@Column(name = "AGE")
	private Integer age;
	
	@Column(name = "GENDER", length = 5)
	private String gender;
	
	@Column(name = "STREAM")
	private String stream;
	
	@Column(name = "SEMESTER")
	private Integer semester;
	
}
