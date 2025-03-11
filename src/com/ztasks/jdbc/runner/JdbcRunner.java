package com.ztasks.jdbc.runner;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;

import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.logger.CustomLogger;
import com.generalutils.PropertiesHandler;
import com.generalutils.GeneralUtils;
import com.ztasks.jdbc.task.JdbcTask;
import com.ztasks.jdbc.models.Employee;
import com.ztasks.jdbc.models.EmployeeResultWrapper;

public class JdbcRunner {

	private static Logger LOGGER = CustomLogger.getLogger("com.ztasks.jdbc.runner.JdbcRunner", "jdbc");
	private static final String PROPERTIES_FILE_PATH = "validation_messages.properties";
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

	private void printChoiceList() {
		System.out.println("\nSno.    Functions");
		System.out.println("1 Add employees");
		System.out.println("2 Retrieve details by name");
		System.out.println("3 Update details of an employee");
		System.out.println("4 Print 1st n employees");
		System.out.println("5 ");
		System.out.println("6 ");
		System.out.println("7 ");
		System.out.println("8 ");
		System.out.println("9 Exit");
		System.out.println("Please enter the corresponding serial number to perform the requied function: ");
	}

	private List<Employee> getEmployees(Scanner sc) {
		List<Employee> employees = new ArrayList<>();
		char choice = 'y';
		while (choice == 'y') {
			Employee employee = new Employee();
			System.out.println("Enter the details of Employee");
			System.out.print("Enter their name: ");
			employee.setName(sc.nextLine());
			System.out.print("Enter mobile number: ");
			employee.setMobile(sc.nextLine());
			System.out.print("Enter email: ");
			employee.setEmail(sc.nextLine());
			System.out.print("Enter department: ");
			employee.setDepartment(sc.nextLine());
			employees.add(employee);

			System.out.print("Do you want to add more employees (y/n): ");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		return employees;
	}

	private void logResult(EmployeeResultWrapper result) throws InvalidArgumentException {
		System.out.println(result.getSuccessCount() + " employee detail(s) added successfully");
		LOGGER.info(result.getSuccessCount() + "employee detail(s) added successfully");

		if (!result.isAllSuccess()) {
			LOGGER.info("The following employees were not added due to the following ...");
			System.out.println("The following employees were not added due to the following ...");

			PropertiesHandler propsHandler = new PropertiesHandler();
			for (Map.Entry<Employee, Map<String, String>> entry : result.getFailedEntries().entrySet()) {
				Employee employee = entry.getKey();
				Map<String, String> failureReasons = entry.getValue();

				System.out.println("Employee: " + employee);
				LOGGER.info("Employee: " + employee);

				for (Map.Entry<String, String> reasonEntry : failureReasons.entrySet()) {
					String field = reasonEntry.getKey();
					String errorCode = reasonEntry.getValue();

					String errorMessage = propsHandler.getProperty(VALIDATIONPROPERTIES, errorCode,
							"An error occurred");

					System.out.println("  - " + field + ": " + errorMessage);
					LOGGER.info("  - " + field + ": " + errorMessage);
				}
			}
		}
	}

	private void logEmployees(List<Employee> list) {
		if (list.isEmpty()) {
			LOGGER.info("No records found");
			System.out.println("No records found");
		}
		for (Employee emp : list) {
			System.out.println(emp);
			LOGGER.info(emp.toString());
		}
	}

	public void addUsers(Scanner sc) throws InvalidArgumentException, ValidationException {
		JdbcTask task = new JdbcTask();
		List<Employee> list = getEmployees(sc);

		EmployeeResultWrapper result = task.processAndAddEmployees(list);

		logResult(result);
	}

	public void retrieveDetailsByName(Scanner sc) throws InvalidArgumentException, ValidationException {
		JdbcTask task = new JdbcTask();

		System.out.print("Enter the name to find matching employees by name: ");
		List<Employee> result = task.retieveDetailsByName(sc.nextLine());

		logEmployees(result);
	}

	public void updateDetail(Scanner sc) throws ValidationException {
		JdbcTask task =  new JdbcTask();
		
		List<Employee> employees = retrieveAllEmployees();
		printEmployeeIdAndName(employees);
		System.out.print("Enter the id of the employee for whom you want to modify details: ");
		int id  = sc.nextInt();
		sc.nextLine();
		
		System.out.println("Choose which field to update: ");
		System.out.println("1. Mobile\n2. Email\n3. Department");
		int choice = sc.nextInt();
		sc.nextLine();
		
		System.out.print("Enter the new value: ");
		String newValue = sc.nextLine();
		
		Employee employee = task.getUpdatedEmployee(id,choice,newValue);
	}

	private List<Employee> retrieveAllEmployees() throws ValidationException {
		JdbcTask task = new JdbcTask();
		return task.getAllEmployees();
	}

	private void printEmployeeIdAndName(List<Employee> employees) {
	    if (employees == null || employees.isEmpty()) {
	        System.out.println("No employees found.");
	        return;
	    }

	    System.out.println("ID | Name");
	    System.out.println("-------------------");

	    for (Employee employee : employees) {
	        System.out.println(employee.getId() + " | " + employee.getName());
	    }
	}


	public void printFirstNEmployees(Scanner sc) throws BoundaryCheckException, ValidationException {
		JdbcTask task = new JdbcTask();

		System.out.print("Enter the number of employees to print: ");
		List<Employee> result = task.retrieveFirstNEmployees(sc.nextInt());
		sc.nextLine();

		logEmployees(result);
	}

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);

		JdbcRunner runner = new JdbcRunner();
		runner.printChoiceList();
		int choice = sc.nextInt();
		sc.nextLine();

		if (choice == 9) {
			return;
		}
		do {
			try {
				switch (choice) {
				case 1:
					runner.addUsers(sc);
					break;
				case 2:
					runner.retrieveDetailsByName(sc);
					break;
				case 3:
					runner.updateDetail(sc);
					break;
				case 4:
					runner.printFirstNEmployees(sc);
					break;
				case 5:
					break;
				case 6:
					break;
				case 7:
					break;
				case 8:
					break;
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Exception occurred", e);
			}
			runner.printChoiceList();
			choice = sc.nextInt();
			sc.nextLine();
		} while (choice != 9);
	}
}
