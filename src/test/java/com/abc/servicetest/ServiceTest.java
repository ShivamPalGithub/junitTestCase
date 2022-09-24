package com.abc.servicetest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.model.Student;
import com.abc.repository.StudentRepository;
import com.abc.service.StudentService;

@SpringBootTest

public class ServiceTest {
	private static final int Student = 0;
	Student student2;
	List<Student> list;
	Integer studentId;

	@InjectMocks
	StudentService studentService;

	@Mock
	StudentRepository repo;

	@BeforeEach 
	void setup() {
		Student student = getStudent();
		list = new ArrayList();
		studentId = 12;
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);
		Student student2 = new Student();
		student2.setStudentName("abhishak");
		student2.setStudentId(12);
		list.add(student1);
		list.add(student2);
 
	}

	@Test
	public void saveStudent_test() {
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);
		when(repo.save(Mockito.any(Student.class))).thenReturn(student1);
		student2 = studentService.saveStudent(student1);
		assertEquals(student1.toString(), student2.toString());
	}

	@Test
	public void getStudent_test() {
		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);

		when(repo.findAll()).thenReturn(list);
		List<Student> studentList = studentService.getStudent();
//	  when(repo.findAll(Mockito.any(List<Student>.class))).thenReturn(list);
//	  List<Student>student2 = studentService.getStudent();
		
//		assertThat(studentList).isNotNull(); 
//		assertThat(studentList.size()).isEqualTo(2);
	  assertEquals(list, studentList);

	}

	@Test
	public void updateStudent_test() {
		// given - precondition or setup
		Student student = new Student();
		student.setStudentName("abhishak"); 
		student.setStudentId(12);
		when(repo.findById(student.getStudentId())).thenReturn(Optional.of(student));
		student.setStudentId(13);
		student.setStudentName("Ram");

		when(repo.save(Mockito.any(Student.class))).thenReturn(student);
		Student updatedStudent = studentService.updateStudent(student, studentId); 
	
		assertEquals(student.toString(), updatedStudent.toString());

//		assertThat(updatedStudent.getStudentId()).isEqualTo(13);
//		assertThat(updatedStudent.getStudentName()).isEqualTo("Ram");
	}
 
	@Test
	void deletStudent_Test() {

		Student student1 = new Student();
		student1.setStudentName("abhishak");
		student1.setStudentId(12);
//		when(repo.deleteById(studentId)).thenReturn();
		student2 = studentService.saveStudent(student1);
		assertEquals(student1.toString(), student2);

	}

	private Student getStudent() {
		Student student = new Student();
		student.setStudentName("abhishak");
		student.setStudentId(12);

		return student;
	}

}
