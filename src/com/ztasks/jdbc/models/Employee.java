package com.ztasks.jdbc.models;

public class Employee {
	private int id;
	private String name,mobile,email,department;
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getMobile() {
		return mobile;
	}
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getDepartment() {
		return department;
	}
	public void setDepartment(String department) {
		this.department = department;
	}
	
	@Override
	public String toString() {
	    return new StringBuilder("Employee { ")
	            .append("ID=").append(id)
	            .append(", Name='").append(name).append('\'')
	            .append(", Mobile='").append(mobile).append('\'')
	            .append(", Email='").append(email).append('\'')
	            .append(", Department='").append(department).append('\'')
	            .append(" }")
	            .toString();
	}

	
	
	
}
