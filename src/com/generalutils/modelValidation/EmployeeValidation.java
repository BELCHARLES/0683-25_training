package com.generalutils.modelValidation;


import com.ztasks.jdbc.models.Employee;

public class EmployeeValidation {
	
	public static void validateEmployee(Employee employee) {
		String name  = employee.getName();
		String mobile = employee.getMobile();
		String email = employee.getEmail();
		String dept = employee.getDepartment();
		
		
	}
	
}
