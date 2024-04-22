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
@Table(name = "TEACHER", schema = "college")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Teacher implements Serializable{

	private static final long serialVersionUID = -2749844997065114032L;
	
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "id_teacherSequence")
    @SequenceGenerator(name = "id_teacherSequence", sequenceName = "COLLEGE.teacher_teacher_id_seq",allocationSize = 1)
	@Column(name = "TEACHER_ID")
	private Integer teacherId;
	
	@Column(name = "TEACHER_NAME", length = 50)
	private String teacherName;
	
	@Column(name = "GENDER", length = 5)
	private String gender;

}

