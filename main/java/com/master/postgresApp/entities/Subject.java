package com.master.postgresApp.entities;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "SUBJECT", schema = "college")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Subject implements Serializable{
	
	private static final long serialVersionUID = -1674427643873528434L;
	
	@Id
	@Column(name = "SUBJECT_CODE", length = 10)
	private String subjectCode;
	
	@Column(name = "SUBJECT_NAME", length = 50)
	private String subjectName;
	
}
