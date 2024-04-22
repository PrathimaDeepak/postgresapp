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
import com.master.postgresApp.entities.Subject;
import com.master.postgresApp.repository.SubjectRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PostgresAppApplication.class)
@AutoConfigureMockMvc
public class SubjectRepositoryTest {
	
	@MockBean
	private SubjectRepository subjectRepository;
	
	@Test
	public void testFindAll() {
		//given
		Subject s1 = new Subject("24CS101", "Programming in C");
		Subject s2 = new Subject("24CS102", "Web Programming");
		Mockito.when(subjectRepository.findAll()).thenReturn(Arrays.asList(s1, s2));
		//when
		List<Subject> subjectList = subjectRepository.findAll();
		//then
		assertEquals(2, subjectList.size());
		assertEquals(s1.getSubjectCode(), subjectList.get(0).getSubjectCode());
		assertEquals(s2.getSubjectCode(), subjectList.get(1).getSubjectCode());
		
	}
	
	@Test
	public void testFindOne() {
		//given
		Subject s1 = new Subject("24CS101", "Programming in C");
		Mockito.when(subjectRepository.findById("24CS101")).thenReturn(Optional.of(s1));
		//when
		Optional<Subject> subjectEntry = subjectRepository.findById("24CS101");
		//then
		assertTrue(subjectEntry.isPresent());
		assertEquals(s1.getSubjectName(), subjectEntry.get().getSubjectName());
		
	}
	
	@Test
	public void testEditSubjectEntry() {
		//given
		Subject s1 = new Subject("24CS101", "Programming in C");
		s1.setSubjectName("C Programming");
		Mockito.when(subjectRepository.save(s1)).thenReturn(s1);
		//when
		Subject savedEntry = subjectRepository.save(s1);
		//then
		assertEquals("24CS101", savedEntry.getSubjectCode());
		assertEquals("C Programming", savedEntry.getSubjectName());
	}
	

	@Test
	public void testSaveAndDeleteSubjectEntry() {
		//given
		Subject s1 = new Subject("24EC203", "Logic Design");
		Mockito.when(subjectRepository.save(s1)).thenReturn(s1);
		//when
		Subject savedEntry = subjectRepository.save(s1);
		//then
		assertEquals("24EC203", savedEntry.getSubjectCode());
		assertEquals("Logic Design", savedEntry.getSubjectName());
		//when
		subjectRepository.delete(s1);
		//then
		Mockito.verify(subjectRepository).delete(s1);
	}

}
