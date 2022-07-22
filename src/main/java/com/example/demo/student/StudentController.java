package com.example.demo.student;

import java.time.LocalDate;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/")
public class StudentController {
	@Autowired
	private final StudentService studentService;
	
	@Autowired
	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping(path="/")
	public List<Student> getStudents() {
		return studentService.getStudents();
	}
	
	@PostMapping
	public void registerNewStudent(@RequestBody Student student) {
		studentService.addNewStudent(student);
	}
	
	@DeleteMapping(path="{studentId}")
	public void deleteStudent(@PathVariable("studentId") Long studentId) {
		studentService.deleteStudent(studentId);
	}
	
	@PutMapping(path= "{studentId}")
	public void updateStudent(
			@PathVariable("studentId") Long studentId,
			@RequestParam(required = false) String name,
			@RequestParam(required = false) String email,
			@RequestParam(required = false) @DateTimeFormat(pattern="yyyy-MM-dd") LocalDate dob) {
		studentService.updateStudent(studentId, name, email, dob);
	}
}
