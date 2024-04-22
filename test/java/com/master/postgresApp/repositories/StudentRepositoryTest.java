package com.master.postgresApp.repositories;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.master.postgresApp.PostgresAppApplication;
import com.master.postgresApp.entities.Student;
import com.master.postgresApp.repository.StudentRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PostgresAppApplication.class)
@AutoConfigureMockMvc
public class StudentRepositoryTest {
	
	@MockBean
	private StudentRepository studentRepository;
	
	@Test
	public void testFindAll() {
		//given
		Student s1 = new Student(1, "Rahul", 20, "Male", "CSE", 2);
		Student s2 = new Student(2, "Ankita", 22, "Female", "ISE", 4);
		Mockito.when(studentRepository.findAll()).thenReturn(Arrays.asList(s1, s2));
		//when
		List<Student> studentList = studentRepository.findAll();
		//then
		assertEquals(2, studentList.size());
		assertEquals("Rahul", studentList.get(0).getStudentName());
		assertEquals("Ankita", studentList.get(1).getStudentName());
		
	}
	
	@Test
	public void testFindOne() {
		//given
		Student s1 = new Student(1, "Rahul", 20, "Male", "CSE", 2);
		Mockito.when(studentRepository.findById(1)).thenReturn(Optional.of(s1));
		//when
		Optional<Student> studentEntry = studentRepository.findById(1);
		//then
		assertTrue(studentEntry.isPresent());
		assertEquals("Rahul", studentEntry.get().getStudentName());
		
	}
	
	@Test
	public void testEditStudentEntry() {
		//given
		Student s1 = new Student(1, "Rahul", 20, "Male", "CSE", 2);
		s1.setAge(21);
		Mockito.when(studentRepository.save(s1)).thenReturn(s1);
		//when
		Student savedEntry = studentRepository.save(s1);
		//then
		assertEquals("Rahul", savedEntry.getStudentName());
		assertEquals(21, savedEntry.getAge().intValue());
	}
	

	@Test
	public void testSaveAndDeleteStudentEntry() {
		//given
		Student s1 = new Student(3, "Raj", 22, "Male", "ME", 4);
		Mockito.when(studentRepository.save(s1)).thenReturn(s1);
		//when
		Student savedEntry = studentRepository.save(s1);
		//then
		assertEquals("Raj", savedEntry.getStudentName());
		assertEquals(22, savedEntry.getAge().intValue());
		//when
		studentRepository.delete(s1);
		//then
		Mockito.verify(studentRepository).delete(s1);
	}
	
	

}
