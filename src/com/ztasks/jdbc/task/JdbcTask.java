package com.ztasks.jdbc.task;

import java.sql.SQLException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONArray;

import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.GeneralUtils;
import com.generalutils.modelValidation.EmployeeValidation;
import com.generalutils.modelValidation.NomineeValidation;
import com.ztasks.jdbc.models.Employee;
import com.ztasks.jdbc.models.EmployeeResultWrapper;
import com.ztasks.jdbc.models.Nominee;
import com.ztasks.jdbc.models.NomineeResultWrapper;
import com.ztasks.jdbc.task.dao.EmployeeDAO;
import com.ztasks.jdbc.task.dao.NomineeDAO;

public class JdbcTask {

	public EmployeeResultWrapper processAndAddEmployees(List<Employee> employees)
			throws InvalidArgumentException, ValidationException {
		try {

			int length = GeneralUtils.findLength(employees);
			Map<Employee, Map<String, String>> errorHolder = new HashMap<>();
			boolean isAllSuccess = true;
			EmployeeDAO empDao = new EmployeeDAO();
			int successCount = 0;

			 Iterator<Employee> iterator = employees.iterator();
		        while (iterator.hasNext()) {
		            Employee employee = iterator.next();

		            Map<String, String> errors = EmployeeValidation.validateEmployee(employee);
		            if (!errors.isEmpty()) {
		                errorHolder.put(employee, errors);
		                iterator.remove(); 
		                isAllSuccess = false;
		                continue;
		            }

		            successCount++;
		        }

		        if (!employees.isEmpty()) {
		            empDao.insertEmployee(employees);
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
			String columnName = EmployeeField.getColumnName(choice);
			EmployeeDAO empDao = new EmployeeDAO();

			switch (choice) {
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

			boolean isUpdated = empDao.updateEmployee(id, columnName, newValue);
			if (!isUpdated) {
				throw new ValidationException("Invalid employee id selected");
			}
			return empDao.retrieveDetailsById(id);
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}

	}

	public List<Employee> retrieveFirstNEmployees(int limit) throws BoundaryCheckException, ValidationException {
		try {
			GeneralUtils.boundaryCheck(limit, 0, 9999);
			EmployeeDAO empDao = new EmployeeDAO();
			List<Employee> result = empDao.getEmployees(limit);
			return result;
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public List<Employee> getAllEmployees() throws ValidationException {
		try {
			EmployeeDAO empDao = new EmployeeDAO();
			return empDao.getEmployees();
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public List<Employee> getSortedEmployeesInorder(int limit, int columnChoice, boolean isAscending)
			throws ValidationException, BoundaryCheckException {
		try {
			GeneralUtils.boundaryCheck(limit, 0, 9999);
			String columnName = EmployeeField.getColumnName(columnChoice);
			EmployeeDAO empDao = new EmployeeDAO();
			return empDao.getEmployees(limit, true, columnName, isAscending);
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public void deleteEmployeeById(int id) throws ValidationException {
		try {
			EmployeeDAO empDao = new EmployeeDAO();
			boolean isDeleted = empDao.deleteEmployeeById(id);
			if(!isDeleted) {
				throw new ValidationException("Invalid employee id selected.");
			}
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}
	
	public NomineeResultWrapper processAndAddNominees(List<Nominee> nominees)
			throws InvalidArgumentException, ValidationException {
		try {

			int length = GeneralUtils.findLength(nominees);
			Map<Nominee, Map<String, String>> errorHolder = new HashMap<>();
			boolean isAllSuccess = true;
			NomineeDAO nomineeDao = new NomineeDAO();
			int successCount = 0;

			  Iterator<Nominee> iterator = nominees.iterator();
		        while (iterator.hasNext()) {
		            Nominee nominee = iterator.next();

		            Map<String, String> errors = NomineeValidation.validateNominee(nominee);
		            if (!errors.isEmpty()) {
		                errorHolder.put(nominee, errors);
		                iterator.remove(); 
		                isAllSuccess = false;
		                continue;
		            }

		            successCount++;
		        }

		        if (!nominees.isEmpty()) {
		            nomineeDao.insertNominee(nominees);
		        }
			return new NomineeResultWrapper(isAllSuccess, errorHolder, successCount);
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public JSONArray getNomineeByEmpId(int id) throws ValidationException {
		try {
			NomineeDAO nomineeDao = new NomineeDAO();
			return nomineeDao.getNomineeByEmpId(id);
		}catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

	public JSONArray getNomineeDetailsForFirstNEmployees(int limit, boolean isAscending) throws BoundaryCheckException, ValidationException {
		try {
			GeneralUtils.boundaryCheck(limit, 0, 9999);
			NomineeDAO nomineeDao = new NomineeDAO();
			return nomineeDao.getEmployeesNomineeDetails(limit,isAscending);
		} catch (SQLException e) {
			throw new ValidationException("An error occurred while processing your request.", e);
		}
	}

}
