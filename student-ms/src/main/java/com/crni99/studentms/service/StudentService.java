package com.crni99.studentms.service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.crni99.studentms.domain.Student;
import com.crni99.studentms.exception.EmailInUseException;
import com.crni99.studentms.exception.EmptyInputException;
import com.crni99.studentms.exception.NoSuchElementException;
import com.crni99.studentms.storage.StudentRepository;

@Service
public class StudentService {

	private final StudentRepository studentRepository;

	public StudentService(StudentRepository studentRepository) {
		this.studentRepository = studentRepository;
	}

	public Student saveStudent(Student student) {
		if (student == null || student.getFirstName().isEmpty() || student.getLastName().isEmpty()
				|| student.getEmail().isEmpty() || student.getDateOfBirth() == null || student.getIndexNumber() == 0) {
			throw new EmptyInputException("You need to input all fields.");
		}
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(student.getEmail());
		if (checkIfStudentWithEmailExist != null) {
			throw new EmailInUseException("Email already in use.");
		}
		return studentRepository.save(student);
	}

	public List<Student> getAllStudents() {
		List<Student> students = (List<Student>) studentRepository.findAll();
		if (students.isEmpty()) {
			throw new NoSuchElementException("Students not found.");
		}
		return students;
	}

	public Student findStudentById(Long id) {
		return studentRepository.findStudentById(id)
				.orElseThrow(() -> new NoSuchElementException("Student with id: " + id + " does not exist."));
	}

	public Student findStudentByEmail(String email) {
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(email);
		if (checkIfStudentWithEmailExist == null) {
			throw new NoSuchElementException("Student with email: " + email + " does not exist.");
		}
		return checkIfStudentWithEmailExist;
	}

	public Student findStudentByIndexNumber(int indexNumber) {
		if (indexNumber == 0) {
			throw new EmptyInputException(
					"You need to provide index number of student to be searched. ID can not be 0.");
		}
		Student checkIfStudentWithIndexExist = studentRepository.findStudentByIndexNumber(indexNumber);
		if (checkIfStudentWithIndexExist == null) {
			throw new NoSuchElementException("Student with index: " + indexNumber + " does not exist.");
		}
		return checkIfStudentWithIndexExist;
	}

	public List<Student> getStudentsBetweenTwoDOB(LocalDate dob1, LocalDate dob2) {
		List<Student> students = studentRepository.findBetweenTwoDOB(dob1, dob2);
		if (students.isEmpty()) {
			throw new NoSuchElementException("Students do not exist in this dates of birth: " + dob1 + " - " + dob2);
		}
		return students;
	}

	@Transactional
	public Student updateStudentById(Student student, Long id) {
		Optional<Student> studentOptional = studentRepository.findStudentById(id);

		if (!studentOptional.isPresent()) {
			throw new NoSuchElementException("Student with id: " + id + " does not exist.");
		}
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(student.getEmail());
		if (checkIfStudentWithEmailExist != null) {
			throw new EmailInUseException("Email already in use.");
		}
		student.setId(id);
		return studentRepository.save(student);
	}

	public void deleteStudentById(Long id) {
		if (id == 0) {
			throw new EmptyInputException("You need to provide ID of student to be deleted. ID can not be 0.");
		}
		Optional<Student> checkIfStudentWithIdExist = studentRepository.findById(id);
		if (checkIfStudentWithIdExist.isEmpty()) {
			throw new NoSuchElementException(
					"Student can not be deleted because student with id: " + id + " does not exist.");
		}
		studentRepository.deleteById(id);
	}

	@Transactional
	public void deleteStudentByEmail(String email) {
		Student checkIfStudentWithEmailExist = studentRepository.findStudentByEmail(email);
		if (checkIfStudentWithEmailExist == null) {
			throw new NoSuchElementException(
					"Student can not be deleted because student with email: " + email + " does not exist.");
		}
		studentRepository.deleteStudentByEmail(email);
	}

}
