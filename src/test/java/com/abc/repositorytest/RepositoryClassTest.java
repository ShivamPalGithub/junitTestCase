package com.abc.repositorytest;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.abc.model.Student;
import com.abc.repository.StudentRepository;

@SpringBootTest
public class RepositoryClassTest {

	Student student;
//	List<Student> listStudent;
//Student student=new Student();
	@Autowired
	StudentRepository studentRepository;

	@BeforeEach
	void setup() {
		student = getStudent();
//		 listStudent=new ArrayList<>();

	}

	@Test
	void addStudent_test() {
		Student st2 = new Student();
		st2.setStudentId(33);
		st2.setStudentName("kanu");
		System.out.println(student.getStudentId());
		Student saveStudent = studentRepository.save(st2);
		System.out.println(saveStudent.getStudentId());
		assertEquals(st2.getStudentId(), saveStudent.getStudentId());
	}

	@Test
	void allGetStudent_test() {
		Student student = new Student();
		student.setStudentName("kanu");
		student.setStudentId(3);
		studentRepository.save(student);
		List<Student> listStudent = studentRepository.findAll();
		assertThat(listStudent).isNotNull();
		assertThat(listStudent.size()).isEqualTo(listStudent.size());

//		assertEquals(listStudent.size(), listStudent.size());

	}

	@Test
	void deletStudent_test() {
		Student student2 = new Student();
		student2.setStudentName("kanu");
		student2.setStudentId(3);
		
		studentRepository.save(student2); 

		studentRepository.deleteById(student2.getStudentId());
     Optional<Student> studentOptional = studentRepository.findById(student2.getStudentId());

     assertThat(studentOptional).isEmpty();
}
	

	@Test
	void updateStudent_test() {
		studentRepository.save(student);
		Student saveStudent = studentRepository.findById(student.getStudentId()).get();

		System.out.println("student:" + saveStudent.toString());
		saveStudent.setStudentId(2);
		saveStudent.setStudentName("Ram");
		Student updatedStudent = studentRepository.save(saveStudent);

		assertEquals(saveStudent.toString(), updatedStudent.toString());
 
	}

	private Student getStudent() {
		Student student = new Student();
		student.setStudentName("kanu");
		student.setStudentId(2);

		return student;

	}

}
