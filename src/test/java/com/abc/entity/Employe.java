package com.abc.entity;

import javax.persistence.Entity;

import org.springframework.stereotype.Component;

@Entity
public class Employe {
	int empId;
	String empName;
	
	public Employe() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Employe(int empId, String empName) {
		super();
		this.empId = empId;
		this.empName = empName;
	}

	public int getEmpId() {
		return empId;
	}

	public void setEmpId(int empId) {
		this.empId = empId;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	
	

}
