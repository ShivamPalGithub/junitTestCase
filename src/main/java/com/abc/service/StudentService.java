package com.abc.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.abc.model.Student;
import com.abc.repository.StudentRepository;

@Service
public class StudentService {
	@Autowired
	private StudentRepository studentRepository;

	public Student saveStudent(Student student) {
		return this.studentRepository.save(student);
	}
 
	public List<Student> getStudent() {
		return this.studentRepository.findAll();
	}

	public Student getById(int studentId) {
		  Student student= this.studentRepository.findById(studentId).orElse(null);
		  return student;
	}
	public List<Student> getByName(String studentName) {
		return this.studentRepository.findByStudentName(studentName);
		
	}  

	public Student updateStudent(Student student,int id) {
	Student	student1 = this.studentRepository.findById(id).orElse(null);
		student1.setStudentId(student.getStudentId());
		student1.setStudentName(student.getStudentName());
		return studentRepository.save(student1);
	} 

	
//	public int deleteUser(int id) {
//		int userId = userRepository.findById(id).orElse(null).getId();
//		userRepository.deleteById(id);
//		return userId;
//	}
	
	
	public int deletStudent(int studentId) {

		 this.studentRepository.deleteById(studentId);

		return studentId;
	}

	
}
