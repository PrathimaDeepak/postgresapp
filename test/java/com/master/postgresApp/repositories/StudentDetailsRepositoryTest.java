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
import com.master.postgresApp.entities.StudentDetails;
import com.master.postgresApp.repository.StudentDetailsRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PostgresAppApplication.class)
@AutoConfigureMockMvc
public class StudentDetailsRepositoryTest {
	
	@MockBean
	private StudentDetailsRepository studentDetailsRepository;
	
	
	@Test
	public void testFindAll() {
		//given
		StudentDetails s1 = new StudentDetails(1, 1, "24CS101");
		StudentDetails s2 = new StudentDetails(1, 2, "24CS102");
		Mockito.when(studentDetailsRepository.findAll()).thenReturn(Arrays.asList(s1,s2));
		//when
		List<StudentDetails> studentDetailsList = studentDetailsRepository.findAll();
		//then
		assertEquals(2, studentDetailsList.size());
		assertEquals(s1.getSubjectCode(), studentDetailsList.get(0).getSubjectCode());
		assertEquals(s2.getSubjectCode(), studentDetailsList.get(1).getSubjectCode());
	}
	
	@Test
	public void testFindOne() {
		//given
		Integer id = 1;
		StudentDetails s1 = new StudentDetails(1, 1, "24CS101");
		Mockito.when(studentDetailsRepository.findById(id)).thenReturn(Optional.of(s1));
		//when
		Optional<StudentDetails> studentDetails = studentDetailsRepository.findById(id);
		//then
		assertTrue(studentDetails.isPresent());
		assertEquals(s1.getSubjectCode(), studentDetails.get().getSubjectCode());
	}
	
	@Test
	public void testEnrolStudentEntry() {
		//given
		StudentDetails s1 = new StudentDetails(3, 2 , "24CS101");
		Mockito.when(studentDetailsRepository.save(s1)).thenReturn(s1);
		//when
		StudentDetails savedEntry = studentDetailsRepository.save(s1);
		//then
		assertEquals("24CS101", savedEntry.getSubjectCode());
		//when
		studentDetailsRepository.delete(s1);
		//then
		Mockito.verify(studentDetailsRepository).delete(s1);
	}
	
	@Test
	public void testFindByStudentIdAndSubjectCode() {
		//given
		StudentDetails s1 = new StudentDetails(3, 2 , "24CS101");
		Mockito.when(studentDetailsRepository.findByStudentIdAndSubjectCode(3, "24CS101")).thenReturn(Arrays.asList(s1));
		//when
		List<StudentDetails> studentDetailsList = studentDetailsRepository.findByStudentIdAndSubjectCode(3, "24CS101");
		//then
		assertEquals(1, studentDetailsList.size());
		assertEquals(s1.getSubjectCode(), studentDetailsList.get(0).getSubjectCode());
	}
	
	@Test
	public void testFindByStudentId() {
		//given
		StudentDetails s1 = new StudentDetails(1, 1, "24CS101");
		StudentDetails s2 = new StudentDetails(1, 2, "24CS102");
		Mockito.when(studentDetailsRepository.findByStudentId(1)).thenReturn(Arrays.asList(s1,s2));
		//when
		List<StudentDetails> studentDetailsList = studentDetailsRepository.findByStudentId(1);
		//then
		assertEquals(2, studentDetailsList.size());
		assertEquals(s1.getSubjectCode(), studentDetailsList.get(0).getSubjectCode());
		assertEquals(s2.getSubjectCode(), studentDetailsList.get(1).getSubjectCode());
	}
	
	@Test
	public void testFindByTeacherId() {
		//given
		StudentDetails s1 = new StudentDetails(2, 2, "24CS102");
		StudentDetails s2 = new StudentDetails(1, 2, "24CS102");
		Mockito.when(studentDetailsRepository.findByTeacherId(2)).thenReturn(Arrays.asList(s1,s2));
		//when
		List<StudentDetails> studentDetailsList = studentDetailsRepository.findByTeacherId(2);
		//then
		assertEquals(2, studentDetailsList.size());
		assertEquals(s1.getSubjectCode(), studentDetailsList.get(0).getSubjectCode());
		assertEquals(s2.getSubjectCode(), studentDetailsList.get(1).getSubjectCode());
	}
	
	@Test
	public void testFindBySubjectCode() {
		//given
		StudentDetails s1 = new StudentDetails(2, 2, "24CS102");
		StudentDetails s2 = new StudentDetails(1, 2, "24CS102");
		Mockito.when(studentDetailsRepository.findBySubjectCode("24CS102")).thenReturn(Arrays.asList(s1,s2));
		//when
		List<StudentDetails> studentDetailsList = studentDetailsRepository.findBySubjectCode("24CS102");
		//then
		assertEquals(2, studentDetailsList.size());
		assertEquals(s1.getSubjectCode(), studentDetailsList.get(0).getSubjectCode());
		assertEquals(s2.getSubjectCode(), studentDetailsList.get(1).getSubjectCode());
	}

}
