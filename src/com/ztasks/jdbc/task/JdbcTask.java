package com.ztasks.jdbc.task;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.GeneralUtils;
import com.generalutils.modelValidation.EmployeeValidation;
import com.ztasks.jdbc.models.Employee;
import com.ztasks.jdbc.models.EmployeeResultWrapper;

public class JdbcTask {

	public EmployeeResultWrapper processAndAddEmployees(List<Employee> list)
			throws InvalidArgumentException, ValidationException {
		try {

			int length = GeneralUtils.findLength(list);
			Map<Employee, Map<String, String>> errorHolder = new HashMap<>();
			boolean isAllSuccess = true;
			EmployeeDAO empDao = new EmployeeDAO();
			int successCount = 0;

			for (int i = 0; i < length; i++) {
				Employee employee = list.get(i);

				Map<String, String> errors = EmployeeValidation.validateEmployee(employee);
				if (!errors.isEmpty()) {
					errorHolder.put(employee, errors);
					isAllSuccess = false;
					continue;
				}

				empDao.insertEmployee(employee);
				successCount++;
			}

			return new EmployeeResultWrapper(isAllSuccess, errorHolder, successCount);
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public List<Employee> retieveDetailsByName(String name) throws InvalidArgumentException, ValidationException {
		try {
			GeneralUtils.checkObjArgIsNull(name);
			EmployeeDAO empDao = new EmployeeDAO();
			List<Employee> result = empDao.retrieveDetailsByName(name);
			return result;
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}
	
	public Employee getUpdatedEmployee(int id, int choice, String newValue) throws ValidationException {
		try {
			String columnName  = EmployeeField.getColumnName(choice);
			EmployeeDAO empDao = new EmployeeDAO();
			
			switch(choice) {
			case 1:
				GeneralUtils.validateMobile(newValue);
				break;
			case 2:
				GeneralUtils.validateEmail(newValue);
				break;
			case 3:
				GeneralUtils.validateTextField(newValue, columnName);
				break;
			}
			
			boolean isUpdated = empDao.updateEmployee(id,columnName,newValue);
			if(!isUpdated) {
				throw new ValidationException("Invalid employee id selected");
			}
			return empDao.retrieveDetailsById(id);
		}catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
		
	}

	public List<Employee> retrieveFirstNEmployees(int limit) throws BoundaryCheckException, ValidationException {
		try {
			GeneralUtils.boundaryCheck(limit, 0, 9999);
			EmployeeDAO empDao = new EmployeeDAO();
			List<Employee> result =  empDao.getEmployees(limit);
			return result;
		}  catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public List<Employee> getAllEmployees() throws ValidationException {
		try {
			EmployeeDAO empDao = new EmployeeDAO();
			return empDao.getEmployees();
		}catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

}
