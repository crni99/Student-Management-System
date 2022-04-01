package com.crni99.studentms.web;

import java.time.LocalDate;
import java.util.List;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.crni99.studentms.domain.Student;
import com.crni99.studentms.service.StudentService;

@RestController
@RequestMapping("api/v1/student-ms")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@PostMapping("/add")
	public ResponseEntity<Student> saveStudent(@RequestBody Student student) {
		Student newStudent = studentService.saveStudent(student);
		return new ResponseEntity<>(newStudent, HttpStatus.CREATED);
	}

	@GetMapping("/all")
	public ResponseEntity<List<Student>> getAllStudents() {
		List<Student> students = studentService.getAllStudents();
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@GetMapping("/find/{id}")
	public ResponseEntity<Student> findStudentById(@PathVariable("id") Long id) {
		Student student = studentService.findStudentById(id);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/email/{email}")
	public ResponseEntity<Student> findStudentByEmail(@PathVariable("email") String email) {
		Student student = studentService.findStudentByEmail(email);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/index/{index}")
	public ResponseEntity<Student> findStudentByIndexNumber(@PathVariable("index") int indexNumber) {
		Student student = studentService.findStudentByIndexNumber(indexNumber);
		return new ResponseEntity<>(student, HttpStatus.OK);
	}

	@GetMapping("/date-of-birth")
	public ResponseEntity<List<Student>> getStudentsBetweenTwoDOB(
			@RequestParam("dob1") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob1,
			@RequestParam("dob2") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dob2) {
		List<Student> students = studentService.getStudentsBetweenTwoDOB(dob1, dob2);
		return new ResponseEntity<>(students, HttpStatus.OK);
	}

	@PutMapping("/update/{id}")
	public ResponseEntity<Student> updateStudentById(@RequestBody Student student, @PathVariable("id") Long id) {
		Student updated = studentService.updateStudentById(student, id);
		return new ResponseEntity<>(updated, HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete/{id}")
	public ResponseEntity<?> deleteStudentById(@PathVariable("id") Long id) {
		studentService.deleteStudentById(id);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

	@DeleteMapping("/delete-with-email/{email}")
	public ResponseEntity<?> deleteStudentByEmail(@PathVariable("email") String email) {
		studentService.deleteStudentByEmail(email);
		return new ResponseEntity<>(HttpStatus.ACCEPTED);
	}

}
