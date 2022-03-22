package com.crni99.studentms.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import com.crni99.studentms.domain.Student;
import com.crni99.studentms.storage.StudentRepository;

class StudentServiceTest {

	private static final Long ID_1 = 1L;
	private static final String FIRST_NAME_1 = "Ognjen";
	private static final String LAST_NAME_1 = "Andjelic";
	private static final LocalDate DOB_1 = LocalDate.of(1999, 12, 01);
	private static final String EMAIL_1 = "andjelicb.ognjen@gmail.com";
	private static final int INDEX_1 = 183;
	private static final boolean IS_ON_BUDGET_1 = true;

	private static final Long ID_2 = 2L;
	private static final String FIRST_NAME_2 = "Nikola";
	private static final String LAST_NAME_2 = "Petrovic";
	private static final LocalDate DOB_2 = LocalDate.of(1999, 8, 25);
	private static final String EMAIL_2 = "nikola@gmail.com";
	private static final int INDEX_2 = 169;
	private static final boolean IS_ON_BUDGET_2 = false;

	@Mock
	private StudentRepository studentRepository;

	private StudentService studentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.openMocks(this);
		studentService = new StudentService(studentRepository);
	}

	@Test
	void testStudentService() {
	}

	@Test
	void testSaveStudent() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);
		studentService.saveStudent(student);

		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
		verify(studentRepository).save(studentArgumentCaptor.capture());

		Student capturetStudent = studentArgumentCaptor.getValue();
		assertThat(capturetStudent).isEqualTo(student);
	}

	@Test
	void testGetAllStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		students.add(new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1));
		students.add(new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2));

		when(studentRepository.findAll()).thenReturn(students);

		assertThat(studentService.getAllStudents()).isEqualTo(students);
	}

	@Test
	void testFindStudentById() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findStudentById(ID_1)).thenReturn(Optional.of(student));

		assertThat(studentService.findStudentById(ID_1)).isEqualTo(student);
	}

	@Test
	void testFindStudentByEmail() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findStudentByEmail(EMAIL_1)).thenReturn(student);

		assertThat(studentService.findStudentByEmail(EMAIL_1)).isEqualTo(student);
	}

	@Test
	void testFindStudentByIndexNumber() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentRepository.findStudentByIndexNumber(INDEX_1)).thenReturn(student);

		assertThat(studentService.findStudentByIndexNumber(INDEX_1)).isEqualTo(student);
	}

	@Test
	void testGetStudentsBetweenTwoDOB() {
		LocalDate date1 = LocalDate.of(1998, 8, 25);
		LocalDate date2 = LocalDate.of(2000, 12, 01);

		List<Student> students = new ArrayList<>();
		students.add(new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1));
		students.add(new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2));

		when(studentRepository.findBetweenTwoDOB(date1, date2)).thenReturn(students);

		assertThat(studentService.getStudentsBetweenTwoDOB(date1, date2)).isEqualTo(students);
	}

	@Test
	void testDeleteStudentById() {

	}

	@Test
	void testDeleteStudentByEmail() {

	}

}
