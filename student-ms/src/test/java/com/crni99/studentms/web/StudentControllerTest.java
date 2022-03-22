package com.crni99.studentms.web;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.crni99.studentms.domain.Student;
import com.crni99.studentms.exception.NoSuchElementException;
import com.crni99.studentms.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

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

	@Autowired
	private MockMvc mockMvc;

	@Autowired
	private ObjectMapper objectMapper;

	@MockBean
	private StudentService studentService;

	private StudentController studentController;

	@BeforeEach
	void setUp() {
		studentController = new StudentController(studentService);
	}

	@Test
	void testStudentController() {
	}

	@Test
	void testSaveStudent() {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);
		studentController.saveStudent(student);

		ArgumentCaptor<Student> studentArgumentCaptor = ArgumentCaptor.forClass(Student.class);
		verify(studentService).saveStudent(studentArgumentCaptor.capture());

		Student capturetStudent = studentArgumentCaptor.getValue();
		assertThat(capturetStudent).isEqualTo(student);
	}

	@Test
	void testGetAllStudents() throws Exception {
		List<Student> students = new ArrayList<>();
		students.add(new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1));
		students.add(new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2));

		when(studentService.getAllStudents()).thenReturn(students);

		String url = "/api/v1/student-ms/all";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();

		String expectedResponse = objectMapper.writeValueAsString(students);

		assertThat(actualResponse).isEqualTo(expectedResponse);
	}

	@Test
	void testFindStudentById() throws Exception {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentService.findStudentById(ID_1)).thenReturn(student);
		String url = "/api/v1/student-ms/find/1";

		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();

		String expectedResponse = objectMapper.writeValueAsString(student);

		assertThat(actualResponse).isEqualTo(expectedResponse);
	}

	@Test
	void testFindStudentByEmail() throws Exception {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentService.findStudentByEmail(EMAIL_1)).thenReturn(student);
		String url = "/api/v1/student-ms/email/andjelicb.ognjen@gmail.com";

		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();

		String expectedResponse = objectMapper.writeValueAsString(student);

		assertThat(actualResponse).isEqualTo(expectedResponse);
	}

	@Test
	void testFindStudentByIndexNumber() throws Exception {
		Student student = new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1);

		when(studentService.findStudentByIndexNumber(INDEX_1)).thenReturn(student);
		String url = "/api/v1/student-ms/index/183";

		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();

		String expectedResponse = objectMapper.writeValueAsString(student);

		assertThat(actualResponse).isEqualTo(expectedResponse);
	}

	@Test
	void testGetStudentsBetweenTwoDOB() throws Exception {
		LocalDate date1 = LocalDate.of(1998, 8, 25);
		LocalDate date2 = LocalDate.of(2000, 12, 01);

		List<Student> students = new ArrayList<>();
		students.add(new Student(ID_1, FIRST_NAME_1, LAST_NAME_1, DOB_1, EMAIL_1, INDEX_1, IS_ON_BUDGET_1));
		students.add(new Student(ID_2, FIRST_NAME_2, LAST_NAME_2, DOB_2, EMAIL_2, INDEX_2, IS_ON_BUDGET_2));

		when(studentService.getStudentsBetweenTwoDOB(date1, date2)).thenReturn(students);

		String url = "/api/v1/student-ms/date-of-birth?dob1=1998-08-25&dob2=2000-12-01";
		MvcResult mvcResult = mockMvc.perform(get(url)).andExpect(status().isOk()).andReturn();
		String actualResponse = mvcResult.getResponse().getContentAsString();

		String expectedResponse = objectMapper.writeValueAsString(students);

		assertThat(actualResponse).isEqualTo(expectedResponse);
	}

	@Test
	void testDeleteStudentById() throws Exception {
		Mockito.doNothing().when(studentService).deleteStudentById(ID_1);

		String url = "/api/v1/student-ms/delete/1";
		mockMvc.perform(delete(url)).andExpect(status().isAccepted());

		verify(studentService, times(1)).deleteStudentById(ID_1);
	}

	@Test
	void testDeleteStudentByEmail() throws Exception {
		Mockito.doNothing().when(studentService).deleteStudentByEmail(EMAIL_1);

		String url = "/api/v1/student-ms/delete-with-email//andjelicb.ognjen@gmail.com";
		mockMvc.perform(delete(url)).andExpect(status().isAccepted());

		verify(studentService, times(1)).deleteStudentByEmail(EMAIL_1);
	}

}
