package com.example.demo.student;

import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.time.Month;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
class StudentRepositoryTest {
	
	@Autowired
	private StudentRepository testRepository;

	
	@Test
	void checkIfStudentExists() {
		Student student = new Student(
				4L,
				"Mariam",
				"mariam@gmail.com",
				LocalDate.of(2000, Month.MAY, 05)
			);
		testRepository.save(student);
		Optional<Student> expected = testRepository.findStudentByEmail(student.getEmail());
		assertThat(expected.isPresent()).isTrue();
	}

//	@Test
//	void test() {
//		fail("Not yet implemented");
//	}

}
