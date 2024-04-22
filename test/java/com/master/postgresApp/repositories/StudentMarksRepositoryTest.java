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
import com.master.postgresApp.entities.StudentMarks;
import com.master.postgresApp.repository.StudentMarksRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PostgresAppApplication.class)
@AutoConfigureMockMvc
public class StudentMarksRepositoryTest {
	
	@MockBean
	private StudentMarksRepository studentMarksRepository;
	
	@Test
	public void testFindAll() {
		//given
		StudentMarks sm1 = new StudentMarks(1, "24CS101", 24, 88);
		StudentMarks sm2 = new StudentMarks(1, "24CS102", 21, 92);
		StudentMarks sm3 = new StudentMarks(2, "24CS101", 17, 77);
		StudentMarks sm4 = new StudentMarks(2, "24CS102", 15, 60);
		Mockito.when(studentMarksRepository.findAll()).thenReturn(Arrays.asList(sm1,sm2,sm3,sm4));
		//when
		List<StudentMarks> studentMarksList = studentMarksRepository.findAll();
		//then
		assertEquals(4, studentMarksList.size());
		assertEquals(sm1.getInternalMarks(), studentMarksList.get(0).getInternalMarks());
		assertEquals(sm2.getInternalMarks(), studentMarksList.get(1).getInternalMarks());
		assertEquals(sm3.getInternalMarks(), studentMarksList.get(2).getInternalMarks());
		assertEquals(sm4.getInternalMarks(), studentMarksList.get(3).getInternalMarks());
		assertEquals(sm1.getExternalMarks(), studentMarksList.get(0).getExternalMarks());
		assertEquals(sm2.getExternalMarks(), studentMarksList.get(1).getExternalMarks());
		assertEquals(sm3.getExternalMarks(), studentMarksList.get(2).getExternalMarks());
		assertEquals(sm4.getExternalMarks(), studentMarksList.get(3).getExternalMarks());
	}
	
	@Test
	public void testSaveAndDeleteStudentEntry() {
		//given
		StudentMarks sm1 = new StudentMarks(1, "24CS101", 24, 88);
		Mockito.when(studentMarksRepository.save(sm1)).thenReturn(sm1);
		//when
		StudentMarks savedEntry = studentMarksRepository.save(sm1);
		//then
		assertEquals(sm1.getInternalMarks(), savedEntry.getInternalMarks());
		assertEquals(sm1.getExternalMarks(), savedEntry.getExternalMarks());
		//when
		studentMarksRepository.delete(sm1);
		//then
		Mockito.verify(studentMarksRepository).delete(sm1);
	}
	
	@Test
	public void testFindByStudentId() {
		//given
		StudentMarks sm1 = new StudentMarks(1, "24CS101", 24, 88);
		StudentMarks sm2 = new StudentMarks(1, "24CS102", 21, 92);
		Mockito.when(studentMarksRepository.findByStudentId(1)).thenReturn(Arrays.asList(sm1,sm2));
		//when
		List<StudentMarks> studentMarksList = studentMarksRepository.findByStudentId(1);
		//then
		assertEquals(2, studentMarksList.size());
		assertEquals(sm1.getInternalMarks(), studentMarksList.get(0).getInternalMarks());
		assertEquals(sm2.getInternalMarks(), studentMarksList.get(1).getInternalMarks());
		assertEquals(sm1.getExternalMarks(), studentMarksList.get(0).getExternalMarks());
		assertEquals(sm2.getExternalMarks(), studentMarksList.get(1).getExternalMarks());
	}
	
	@Test
	public void testFindBySubjectCode() {
		//given
		StudentMarks sm1 = new StudentMarks(1, "24CS101", 24, 88);
		StudentMarks sm3 = new StudentMarks(2, "24CS101", 17, 77);
		Mockito.when(studentMarksRepository.findBySubjectCode("24CS101")).thenReturn(Arrays.asList(sm1,sm3));
		//when
		List<StudentMarks> studentMarksList = studentMarksRepository.findBySubjectCode("24CS101");
		//then
		assertEquals(2, studentMarksList.size());
		assertEquals(sm1.getInternalMarks(), studentMarksList.get(0).getInternalMarks());
		assertEquals(sm3.getInternalMarks(), studentMarksList.get(1).getInternalMarks());
		assertEquals(sm1.getExternalMarks(), studentMarksList.get(0).getExternalMarks());
		assertEquals(sm3.getExternalMarks(), studentMarksList.get(1).getExternalMarks());
	}
	
	@Test
	public void testFindByStudentIdAndSubjectCode() {
		//given
		StudentMarks sm1 = new StudentMarks(1, "24CS101", 24, 88);
		Mockito.when(studentMarksRepository.findByStudentIdAndSubjectCode(1, "24CS101")).thenReturn(Optional.of(sm1));
		//when
		Optional<StudentMarks> studentMarks = studentMarksRepository.findByStudentIdAndSubjectCode(1, "24CS101");
		//then
		assertTrue(studentMarks.isPresent());
		assertEquals(sm1.getInternalMarks(), studentMarks.get().getInternalMarks());
		assertEquals(sm1.getExternalMarks(), studentMarks.get().getExternalMarks());
	}
	
}
