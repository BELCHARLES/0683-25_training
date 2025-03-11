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
	
	public String toString() {
        return "Employee { " +
                "ID=" + id +
                ", Name='" + name + '\'' +
                ", Mobile='" + mobile + '\'' +
                ", Email='" + email + '\'' +
                ", Department='" + department + '\'' +
                " }";
    }
	
	
	
}
