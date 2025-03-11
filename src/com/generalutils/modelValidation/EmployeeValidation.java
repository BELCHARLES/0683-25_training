package com.generalutils.modelValidation;


import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.GeneralUtils;
import com.generalutils.PropertiesHandler;
import com.generalutils.logger.CustomLogger;
import com.ztasks.jdbc.models.Employee;

public class EmployeeValidation {
	
	private static Logger LOGGER = CustomLogger.getLogger("com.ztasks.jdbc.runner.JdbcRunner", "jdbc");
	private static final String PROPERTIES_FILE_PATH ="validation_messages.properties";
	private static final Properties VALIDATIONPROPERTIES = loadProperties();
	
	private static Properties loadProperties() {
		PropertiesHandler propsHandler = new PropertiesHandler();
		Properties properties = null;
		try {
			File file = GeneralUtils.createDirFile(System.getProperty("user.dir"), PROPERTIES_FILE_PATH);
			properties = propsHandler.readProperties(file);
		} catch (InvalidArgumentException | IOException e) {
			LOGGER.log(Level.SEVERE, "Exception occurred @ EmployeeValidation.java", e);
		}
		
		return properties;
	}
	
	
	public static Map<String,String> validateEmployee(Employee employee) throws InvalidArgumentException {
		Map<String, String> errorMap = new HashMap<>();
		
		String name  = employee.getName();
		String mobile = employee.getMobile();
		String email = employee.getEmail();
		String dept = employee.getDepartment();
		
		try {
			GeneralUtils.validateTextField(name, "Name");
		}catch(ValidationException e) {
			String errorMsg = generateErrorMsg(e);
			errorMap.put(e.getField(),errorMsg);
		}
		
		try {
			GeneralUtils.validateMobile(mobile);
		}catch(ValidationException e) {
			String errorMsg = generateErrorMsg(e);
			errorMap.put(e.getField(),errorMsg);
		}
		
		try {
			GeneralUtils.validateEmail(email);
		}catch(ValidationException e) {
			String errorMsg = generateErrorMsg(e);
			errorMap.put(e.getField(),errorMsg);
		}
		
		try {
			GeneralUtils.validateTextField(dept, "Department");
		}catch(ValidationException e) {
			String errorMsg = generateErrorMsg(e);
			errorMap.put(e.getField(),errorMsg);
		}
		
		return errorMap;
		
	}


	private static String generateErrorMsg(ValidationException e) throws InvalidArgumentException {
		PropertiesHandler propsHandler = new PropertiesHandler();
		String errorCode = e.getErrorCode();
		String field = e.getField();
		String 	additionalMsg = e.getAdditionalMsg();
		String messageTemplate = propsHandler.getProperty(VALIDATIONPROPERTIES, field, additionalMsg);
		
		switch (errorCode) {
        case "E001":
            return field + " " + messageTemplate; 

        case "E002":
            return messageTemplate.replace("{FORMAT}", additionalMsg); 

        default:
            return messageTemplate; 
    }
	}

	
}
