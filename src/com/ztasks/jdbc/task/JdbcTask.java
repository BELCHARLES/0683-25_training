package com.ztasks.jdbc.task;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;
import com.generalutils.modelValidation.EmployeeValidation;
import com.ztasks.jdbc.models.Employee;
import com.ztasks.jdbc.models.EmployeeResultWrapper;

public class JdbcTask {

	public EmployeeResultWrapper processAndAddEmployees(List<Employee> list) throws InvalidArgumentException {
		int length = GeneralUtils.findLength(list);
		Map<Employee,Map<String,String>> errorHolder = new HashMap<>();
		boolean isAllSuccess = true;
		EmployeeDAO empDao =new EmployeeDAO();
		int successCount = 0;
		
		for(int i=0;i<length;i++) {
			Employee employee = list.get(i);
			
			Map<String,String> errors = EmployeeValidation.validateEmployee(employee);
			if(!errors.isEmpty()) {
				errorHolder.put(employee, errors);
				isAllSuccess = false;
				continue;
			}
			
			empDao.insertEmployee(employee);
			successCount++;
		}
		 
		
		return new EmployeeResultWrapper(isAllSuccess,errorHolder, successCount);
	}

	

}
