package com.aimee.model;

import lombok.Data;

@Data
public class Employee {
	private int empID;
	private String empName;
	private String empEmail;
	private String empPwd;

	public Employee() {
	}

	public Employee(int empID, String empName, String empEmail, String empPwd) {
		super();
		this.empID = empID;
		this.empName = empName;
		this.empEmail = empEmail;
		this.empPwd = empPwd;
	}

	public Employee(String empName, String empEmail, String empPwd) {
		super();
		this.empName = empName;
		this.empEmail = empEmail;
		this.empPwd = empPwd;
	}

	
}
