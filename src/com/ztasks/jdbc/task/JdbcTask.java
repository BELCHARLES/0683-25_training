package com.ztasks.jdbc.task;

import java.util.List;

import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;
import com.ztasks.jdbc.models.Employee;

public class JdbcTask {

	public void addEmployees(List<Employee> list) throws InvalidArgumentException {
		int length = GeneralUtils.findLength(list);
		
		for(int i=0;i<length;i++) {
			
		}
	}

	

}
