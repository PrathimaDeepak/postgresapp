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
import com.master.postgresApp.entities.Teacher;
import com.master.postgresApp.repository.TeacherRepository;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.MOCK, classes = PostgresAppApplication.class)
@AutoConfigureMockMvc
public class TeacherRepositoryTest {
	
	@MockBean
	private TeacherRepository teacherRepository;
	
	@Test
	public void testFindAll() {
		//given
		Teacher t1 = new Teacher(1, "Jyothi", "F");
		Teacher t2 = new Teacher(2, "Amal", "M");
		Mockito.when(teacherRepository.findAll()).thenReturn(Arrays.asList(t1, t2));
		//when
		List<Teacher> teacherList = teacherRepository.findAll();
		//then
		assertEquals(2, teacherList.size());
		assertEquals("Jyothi", teacherList.get(0).getTeacherName());
		assertEquals("Amal", teacherList.get(1).getTeacherName());
		
	}
	
	@Test
	public void testFindOne() {
		//given
		Teacher t1 = new Teacher(1, "Jyothi", "F");
		Mockito.when(teacherRepository.findById(1)).thenReturn(Optional.of(t1));
		//when
		Optional<Teacher> teacherEntry = teacherRepository.findById(1);
		//then
		assertTrue(teacherEntry.isPresent());
		assertEquals("Jyothi", teacherEntry.get().getTeacherName());
		
	}
	
	@Test
	public void testEditTeacherEntry() {
		//given
		Teacher t1 = new Teacher(1, "Jyothi", "F");
		t1.setTeacherName("Jyothi Kiran");
		Mockito.when(teacherRepository.save(t1)).thenReturn(t1);
		//when
		Teacher savedEntry = teacherRepository.save(t1);
		//then
		assertEquals("Jyothi Kiran", savedEntry.getTeacherName());
	}
	

	@Test
	public void testSaveAndDeleteTeacherEntry() {
		//given
		Teacher t1 = new Teacher(1, "Jyothi", "F");
		Mockito.when(teacherRepository.save(t1)).thenReturn(t1);
		//when
		Teacher savedEntry = teacherRepository.save(t1);
		//then
		assertEquals("Jyothi", savedEntry.getTeacherName());
		//when
		teacherRepository.delete(t1);
		//then
		Mockito.verify(teacherRepository).delete(t1);
	}
	
	

}
