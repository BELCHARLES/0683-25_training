package com.generalutils.modelValidation;

import java.util.HashMap;
import java.util.Map;

import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.GeneralUtils;
import com.ztasks.jdbc.models.Employee;

public class EmployeeValidation {

	public static Map<String, String> validateEmployee(Employee employee) throws InvalidArgumentException {
		Map<String, String> errorMap = new HashMap<>();

		String name = employee.getName();
		String mobile = employee.getMobile();
		String email = employee.getEmail();
		String dept = employee.getDepartment();

		try {
			GeneralUtils.validateTextField(name, "Name");
		} catch (ValidationException e) {
			errorMap.put("name", e.getMessage());
		}

		try {
			GeneralUtils.validateMobile(mobile);
		} catch (ValidationException e) {
			errorMap.put("mobile", e.getMessage());
		}

		try {
			GeneralUtils.validateEmail(email);
		} catch (ValidationException e) {
			errorMap.put("email", e.getMessage());
		}

		try {
			GeneralUtils.validateTextField(dept, "Department");
		} catch (ValidationException e) {
			errorMap.put("department", e.getMessage());
		}

		return errorMap;

	}

	/*
	 * private static String generateErrorMsg(ValidationException e) throws
	 * InvalidArgumentException { PropertiesHandler propsHandler = new
	 * PropertiesHandler(); String errorCode = e.getErrorCode(); String field =
	 * e.getField(); String additionalMsg = e.getAdditionalMsg(); String
	 * messageTemplate = propsHandler.getProperty(VALIDATIONPROPERTIES, field,
	 * additionalMsg);
	 * 
	 * switch (errorCode) { case "E001": return field + " " + messageTemplate;
	 * 
	 * case "E002": return messageTemplate.replace("{FORMAT}", additionalMsg);
	 * 
	 * default: return messageTemplate; } }
	 */

}
