package com.abc.controller;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.criteria.CriteriaBuilder.In;

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
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.abc.model.Student;
import com.abc.service.StudentService;
import com.fasterxml.jackson.databind.ObjectMapper;

@RunWith(MockitoJUnitRunner.class)
@AutoConfigureMockMvc
public class StudentControllerTest {
	
	Student student;
	List<Student> studentList;
	
	ObjectMapper objectMapper = new ObjectMapper();;

	@InjectMocks
	StudentController  studentController;

	
	private MockMvc mockMvc;

	@Autowired
	WebApplicationContext context;
	 

	@Mock
	StudentService studentService;
	
     @BeforeEach
	    void setUp() {
 		MockitoAnnotations.initMocks(this);

		this.mockMvc = MockMvcBuilders.standaloneSetup(studentController).build();
		   student=   getstudent();
		   
	    
	    }
	    
	    
	    @Test
		public void saveStudent_test() {
	    	Student student1 = new Student();
			student1.setStudentName("abhishak");
			student1.setStudentId(45);
               System.out.println("Student:"+student1.toString());
			when(studentService.saveStudent(Mockito.any(Student.class))).thenReturn(student1);
			Student expectedOutput = (Student)studentController.addStudent(student1);
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
  	public void updateBook_test()
  	{
  		when(studentService.updateStudent(student, student.getStudentId())).thenReturn(student);
  		Integer i =student.getStudentId();
  		Student expectedOutput = studentController.updateStudent(student,i);
  		assertEquals(student.toString(),expectedOutput.toString());
  		
  		
  	}
  	    
  	    
  	  @Test
  	public void deleteStudent_test()
  	{   
  		when(studentService.deletStudent(Mockito.anyInt())).thenReturn(student.getStudentId());
  		Integer id=	studentController.deletStudent(student.getStudentId());
  		System.out.println("id:"+id);
		assertEquals(student.getStudentId(), (Integer)id);

  	}
  	@Test
	public void deleteBookTest() throws Exception {
		String jsonRequest = String.valueOf(student.getStudentId());
		when(studentService.getById(Mockito.anyInt())).thenReturn(student);
		MvcResult mvcResult = mockMvc
			.perform(delete("/Delete/{id}",jsonRequest))
				.andExpect(status().isOk()).andReturn();
		String expectedOutput = mvcResult.getResponse().getContentAsString();
		System.out.println("expectedOutput:"+expectedOutput);
		Integer id=Integer.parseInt(expectedOutput);
		System.out.println("id:"+id); 
		System.out.println("student:"+student.getStudentId());
//		Student expectedOutputUser = objectMapper.readValue(expectedOutput, Student.class);
		assertEquals(student.getStudentId(),id);
		
		
		
		
	}
  
	   


	


		private Student getstudent() {
	    	Student student=new Student();
	    	student.setStudentId(45);
	    	student.setStudentName("yogesh");
	    	return student;
	    	
	    	
	    }
		private List<Student> getList() {
			List<Student> studentList = new ArrayList<>();
			Student student=new Student();
	    	student.setStudentId(45);
	    	student.setStudentName("yogesh");
	    	
	    	Student student1=new Student();
	    	student1.setStudentId(451);
	    	student1.setStudentName("Abhishak");
	    	
	    	studentList.add(student);
	    	studentList.add(student1);
	    	
	    	
			return studentList;
			
		}

}
