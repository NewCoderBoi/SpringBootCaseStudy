package com.example.demo.student;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

@Service
public class StudentService {
	
	private final StudentRepository studentRepository;
//	private final StudentConfig studentConfig;
//	private List<Student> studentList = new ArrayList<>(Arrays.asList(
//			new Student(100L,"Debatra","fgh@fhj"),
//			new Student(101L,"ksdgfkasdgkf","fgh@fhj"),
//			new Student(102L,"dfhrtutu","fgh@fhj")));
	
	@Autowired
	public StudentService(StudentRepository studentRepository) {
//		this.studentConfig = studentConfig;
		this.studentRepository = studentRepository;
	}
	
//	@Autowired
//	public StudentService(StudentRepository studentRepository) {
//		this.studentRepository = studentRepository;
//	}
	
	public List<Student> getStudents() {
//		return studentRepository.findAll();
		return studentRepository.findAll();
	}
	
	public void addNewStudent(Student student) {
		Optional<Student> studentByEmail = studentRepository.findStudentByEmail(student.getEmail());
		if(studentByEmail.isPresent()) {
			throw new IllegalStateException("Email taken");
		}
		//System.out.println(student);
		studentRepository.save(student);
	}
	
	public void deleteStudent(Long studentId) {
		boolean exists = studentRepository.existsById(studentId);
		if(!exists) {
			throw new IllegalStateException("Student with id "+studentId+" does not exist");
		}
		studentRepository.deleteById(studentId);
	}
	
	@Transactional
	public void updateStudent(Long studentId, String name, String email, LocalDate dob) {
		Student student = studentRepository.findById(studentId)
				.orElseThrow(()-> new IllegalStateException(
						"Student with id "+studentId+" does not exist"
			));
		if(name != null && name.length()>0 && !Objects.equals(student.getName(), name)) {
			student.setName(name);
		}
		
		if(email != null && email.length()>0 && !Objects.equals(student.getEmail(), email)) {
			student.setEmail(email);
		}
		
		if(dob != null && !Objects.equals(student.getDob(), dob)) {
			student.setDob(dob);
		}
	}
}
