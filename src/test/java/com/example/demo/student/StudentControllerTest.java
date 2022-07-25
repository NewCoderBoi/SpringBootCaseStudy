package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class StudentControllerTest {
	
	@Mock private StudentRepository testRepo;
	private StudentService testService;
	
	@BeforeEach
	void setUp() {
		this.testService = new StudentService(this.testRepo);
	}
	
	@Test
	void getStudentsTest() {
		testService.getStudents();
		verify(testRepo).findAll();
	}
	
	@Test
	void addStudentsTest() {
		Student s = new Student(
				4L,
				"Mariam",
				"mpriam@gmail.com",
				LocalDate.of(2001, Month.JULY, 11)
			);
		testService.addNewStudent(s);
		verify(testRepo).save(s);
	}
	
	@Test
	void updateStudentsTest() {
		Student s = new Student(
				1L,
				"Mariam",
				"mpriam@gmail.com",
				LocalDate.of(2001, Month.JULY, 11)
			);
		
		testService.updateStudent(1L, "Mariam", "mpriam@gmail.com", LocalDate.of(2001, Month.JULY, 11));
		verify(testRepo).save(s);
	}
	
	@Test
	void deleteStudentTest() {
		testService.deleteStudent(3L);
		verify(testRepo).deleteById(3L);
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
