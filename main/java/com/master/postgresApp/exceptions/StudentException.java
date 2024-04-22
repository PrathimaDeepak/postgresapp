package com.master.postgresApp.exceptions;

import lombok.Getter;
import lombok.Setter;

public class StudentException extends Exception {
	
	private static final long serialVersionUID = 1678444788713612834L;
	
	@Getter
	@Setter
	private ExceptionType exceptionType;
	
	public StudentException(String s, ExceptionType exceptionType) {
		super(s);
		this.exceptionType = exceptionType;
	}
	
	public enum ExceptionType {
		INVALID_ID,
		STUDENT_DOES_NOT_EXIST
	}

}
