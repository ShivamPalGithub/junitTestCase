package com.abc.entity;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.abc.model.Student;

@SpringBootApplication
public class StudentTest {

//	@Autowired
//	Employe employe;

    public Student studentTestSuit(){
      return new Student();
     }

	@Test 
    public void employeGetId() {
     Integer id= 0;
     Student student1 =null;
     student1 = studentTestSuit();
     id = student1.getStudentId();
     System.out.println(id); 
 
    }

    @Test
    public void employeSetId(Integer id) {
    Integer  id1= 0;
    Student student1 =null;
    student1 = studentTestSuit();
    student1.setStudentId(id1);
	
    } 
	
}
