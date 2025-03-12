package com.ztasks.jdbc.task;

import com.exception.ValidationException;

public enum EmployeeField {
	MOBILE (1,"mobile"),
	EMAIL (2,"email"),
	DEPARTMENT (3,"department"),
	NAME (4,"name"),
	ID (5,"emp_id");
	

	private final int choice;
	private final String columnName;

	EmployeeField(int choice, String columnName) {
		this.choice = choice;
		this.columnName = columnName;
	}
	
	public static String getColumnName(int choice)throws ValidationException {
		for(EmployeeField field : values()) {
			if(field.choice == choice) {
				return field.columnName;			}
		}
		throw new ValidationException("Invalid column number selected");
	}
	
}
