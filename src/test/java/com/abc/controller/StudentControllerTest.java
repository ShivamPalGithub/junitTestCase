package com.abc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;import com.abc.model.Student;
import com.abc.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class StudentControllerTest {

	Student student; 
	List<Student> studentList;

	ObjectMapper objectMapper = new ObjectMapper();;

	@InjectMocks
	StudentController studentController;

	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;

	@Mock
	StudentService studentService;

	@BeforeEach
	void setUp() {
		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		student = getstudent();
		studentList=getStudentList();

	}

	@Test
	public void saveStudent_test() {
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(45);
		System.out.println("Student:" + student1.toString());
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student1);
		Student expectedOutput = (Student) studentController.addStudent(student1);
		assertEquals(student1.toString(), expectedOutput.toString());

	}

	@Test
	public void saveStudentTest() throws Exception {
		String jsonRequest = objectMapper.writeValueAsString(student);
		when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student);
		MvcResult mvcResult = mockMvc
				.perform(post("/add").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		Student expectedOutputUser = objectMapper.readValue(expectedOutput, Student.class);
		assertEquals(expectedOutputUser.getStudentId(), student.getStudentId());
	}

	@Test
	public void updateBook_test() {
		when(studentService.updateStudent(student, student.getStudentId())).thenReturn(student);
		Integer i = student.getStudentId();
		Student expectedOutput = studentController.updateStudent(student, i);
		assertEquals(student.toString(), expectedOutput.toString());

	} 
	 

@Test
public void updateBookTest() throws Exception {
	String jsonRequest = objectMapper.writeValueAsString(student);
	when(studentService.updateStudent(Mockito.any(Student.class),Mockito.anyInt())).thenReturn(student);
	MvcResult mvcResult = mockMvc
			.perform(put("/update/{id}").content(jsonRequest).contentType(MediaType.APPLICATION_JSON_VALUE))
			.andExpect(status().isOk()).andReturn();
	String expectedOutput = mvcResult.getResponse().getContentAsString();
	Integer id = Integer.parseInt(expectedOutput);
	             
//	Student expectedOutputStudent = objectMapper.readValue(expectedOutput, Student.class);
	assertEquals(id, student.getStudentId());
} 
 
	@Test 
	public void deleteStudent_test() {
		when(studentService.deletStudent(Mockito.anyInt())).thenReturn(student.getStudentId());
		Integer id = studentController.deletStudent(student.getStudentId());
		assertEquals(student.getStudentId(), (Integer) id);
	}

	@Test 
	public void deleteBookTest() throws Exception {
		String jsonRequest = String.valueOf(student.getStudentId());
		int id1 = Integer.parseInt(jsonRequest);


		when(studentService.getById(Mockito.anyInt())).thenReturn(student);
		MvcResult mvcResult = mockMvc.perform(delete("/Delete/{id}", jsonRequest)).andExpect(status().isOk())
				.andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		assertEquals(student.getStudentId(), (Integer) id1);

	}  
	
	
	@Test
	public void getAllStudent_Test()
	{
		when(studentService.getStudent()).thenReturn(studentList);
		List<Student> actualUserList = studentController.findAllStudent();
		assertEquals(actualUserList.size(), studentList.size());
		
	}
	 
	@Test
	public void getAllStudentTest() throws Exception {
		when(studentService.updateStudent(Mockito.any(Student.class),Mockito.anyInt())).thenReturn(student);
		MvcResult mvcResult = mockMvc
				.perform(get("/get")) 
				.andExpect(status().isOk()).andReturn();
		int status=mvcResult.getResponse().getStatus();
		System.out.println(studentList.toString());
		assertEquals(200, status);
		//String expectedOutput = mvcResult.getResponse().getContentAsString();
		//Books[] bookList = super.mapFromJson(expectedOutput, Books[].class);
		//assertEquals(bookList.length()>0);
	
	
	}
	 
	

	private Student getstudent() {
		Student student = new Student();
		student.setStudentId(45);
		student.setStudentName("yogesh");
		return student;
	} 

	private List<Student> getStudentList() {
		List<Student> studentList = new ArrayList<>();
		Student student = new Student();
		student.setStudentId(45);
		student.setStudentName("yogesh");

		Student student1 = new Student();
		student1.setStudentId(451);
		student1.setStudentName("Abhishak");

		studentList.add(student);
		studentList.add(student1);

		return studentList;

	}
}
