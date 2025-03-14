package com.ztasks.jdbc.runner;

import java.io.IOException;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.Properties;
import org.json.JSONArray;

import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.exception.ValidationException;
import com.generalutils.logger.CustomLogger;
import com.generalutils.PropertiesHandler;
import com.generalutils.GeneralUtils;
import com.ztasks.jdbc.task.JdbcTask;
import com.ztasks.jdbc.models.Employee;
import com.ztasks.jdbc.models.EmployeeResultWrapper;
import com.ztasks.jdbc.models.Nominee;
import com.ztasks.jdbc.models.NomineeResultWrapper;

public class JdbcRunner {

	private static Logger LOGGER = CustomLogger.getLogger("com.ztasks.jdbc.runner.JdbcRunner", "jdbc");
	private static final String PROPERTIES_FILE_PATH = "validation_messages.properties";
	private static final Properties VALIDATIONPROPERTIES = loadProperties();

//	helpers..

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
		System.out.println("5 Print 1st n employees sorted and ordered manner");
		System.out.println("6 Delete employee by id");
		System.out.println("7 Add nominee details for employees");
		System.out.println("8 Print Nominee details of an employee");
		System.out.println("9 Print 1st N employee-nominee details");
		System.out.println("10 Exit");
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

	private List<Nominee> getNominees(Scanner sc) throws ValidationException {
		List<Nominee> nominees = new ArrayList<>();
		List<Employee> employees = retrieveAllEmployees();
		char choice = 'y';
		while (choice == 'y') {
			printEmployeeIdAndName(employees);
			System.out.print("Enter the id of the employee whom you want to add nominee:");
			int empId = sc.nextInt();
			sc.nextLine();

			Optional<Employee> matchedEmployee = employees.stream().filter(e -> e.getId() == empId).findFirst();

			if (!matchedEmployee.isPresent()) {
				System.out.println("Invalid Employee ID. Try again.");
				continue;
			}

			char nomineeChoice = 'y';
			while (nomineeChoice == 'y') {
				Nominee nominee = new Nominee();
				
				System.out.println("Enter the details of Nominee");
				System.out.print("Enter their name: ");
				nominee.setName(sc.nextLine());
				System.out.print("Enter age: ");
				nominee.setAge(sc.nextInt());
				sc.nextLine();
				System.out.print("Enter relationship with employee: ");
				nominee.setRelationship(sc.nextLine());
				nominee.setEmpId(empId);

				nominees.add(nominee);
				System.out.print("Do you want to add more nominee ?(y/n): ");
				nomineeChoice = sc.next().charAt(0);
				sc.nextLine();

			}	
			
			System.out.print("Do you want to add nominees for another employee (y/n): ");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		return nominees;
	}
	
	
	private void logResult(NomineeResultWrapper result) throws InvalidArgumentException {
	    System.out.println(result.getSuccessCount() + " nominee detail(s) added successfully");
	    LOGGER.info(result.getSuccessCount() + " nominee detail(s) added successfully");

	    if (!result.isAllSuccess()) {
	        LOGGER.info("The following nominees were not added due to the following ...");
	        System.out.println("The following nominees were not added due to the following ...");

	        PropertiesHandler propsHandler = new PropertiesHandler();
	        for (Map.Entry<Nominee, Map<String, String>> entry : result.getFailedEntries().entrySet()) {
	            Nominee nominee = entry.getKey();
	            Map<String, String> failureReasons = entry.getValue();

	            System.out.println("Nominee: " + nominee);
	            LOGGER.info("Nominee: " + nominee);

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


//	end of helpers

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
		JdbcTask task = new JdbcTask();

		List<Employee> employees = retrieveAllEmployees();
		printEmployeeIdAndName(employees);
		System.out.print("Enter the id of the employee for whom you want to modify details: ");
		int id = sc.nextInt();
		sc.nextLine();

		System.out.println("Choose which field to update: ");
		System.out.println("1. Mobile\n2. Email\n3. Department");
		int choice = sc.nextInt();
		sc.nextLine();

		System.out.print("Enter the new value: ");
		String newValue = sc.nextLine();

		Employee employee = task.getUpdatedEmployee(id, choice, newValue);
		LOGGER.info("Updated employee: \n" + employee.toString());
	}

	public void printFirstNEmployees(Scanner sc) throws BoundaryCheckException, ValidationException {
		JdbcTask task = new JdbcTask();

		System.out.print("Enter the number of employees to print: ");
		List<Employee> result = task.retrieveFirstNEmployees(sc.nextInt());
		sc.nextLine();

		logEmployees(result);
	}

	public void printSortedEmployeesInorder(Scanner sc) throws ValidationException, BoundaryCheckException {
		JdbcTask task = new JdbcTask();

		System.out.print("Enter the number of employees to print: ");
		int limit = sc.nextInt();
		sc.nextLine();

		System.out.println("Select the column you want to sort your data with:");
		System.out.println("1. mobile\n2. email\n3. department\n4. name\n5. id");
		int columnChoice = sc.nextInt();
		sc.nextLine();

		System.out.println("Select the order in which you want to display data: ");
		System.out.println("1. ascending order\n2. descending order");
		int orderChoice = sc.nextInt();
		sc.nextLine();
		boolean isAscending = orderChoice == 1 ? true : false;

		List<Employee> employees = task.getSortedEmployeesInorder(limit, columnChoice, isAscending);
		logEmployees(employees);
	}

	public void deleteEmployeeById(Scanner sc) throws ValidationException {
		JdbcTask task = new JdbcTask();

		List<Employee> employees = retrieveAllEmployees();
		printEmployeeIdAndName(employees);
		System.out.print("Entee the id of the employee whom you want to delete: ");
		int id = sc.nextInt();
		sc.nextLine();

		task.deleteEmployeeById(id);
		LOGGER.info("Employee with id " + id + " is deleted");
		employees = retrieveAllEmployees();
		logEmployees(employees);

	}

	public void addNominees(Scanner sc) throws ValidationException, InvalidArgumentException {
		JdbcTask task = new JdbcTask();
		List<Nominee> nominees = getNominees(sc);
		NomineeResultWrapper result = task.processAndAddNominees(nominees);
		logResult(result);
	}
	
	public void printNomineeDetailsByEmployee(Scanner sc) throws ValidationException {
		JdbcTask task = new JdbcTask();

		List<Employee> employees = retrieveAllEmployees();
		printEmployeeIdAndName(employees);
		System.out.print("Enter the id of the employee whose nominee details to be displayed: ");
		int id = sc.nextInt();
		sc.nextLine();
		
		Optional<Employee> matchedEmployee = employees.stream().filter(e -> e.getId() == id).findFirst();

		if (!matchedEmployee.isPresent()) {
			System.out.println("Invalid Employee ID. Try again.");
			return;
		}
		
		JSONArray result  = task.getNomineeByEmpId(id);
		LOGGER.info(result.toString(4));
		
	}
	
	public void printNomineeDetailsForFirstNEmployees(Scanner sc) throws BoundaryCheckException, ValidationException {
		JdbcTask task = new JdbcTask();

		System.out.print("Enter the number of employees-nominee details to print: ");
		int limit = sc.nextInt();
		sc.nextLine();

		System.out.println("Select the order in which you want to display data: ");
		System.out.println("1. ascending order\n2. descending order");
		int orderChoice = sc.nextInt();
		sc.nextLine();
		boolean isAscending = orderChoice == 1 ? true : false;
		
		JSONArray result  = task.getNomineeDetailsForFirstNEmployees(limit,isAscending);
		LOGGER.info(result.toString(4));
	}

	public static void main(String args[]) throws IOException {
		Scanner sc = new Scanner(System.in);

		JdbcRunner runner = new JdbcRunner();
		runner.printChoiceList();
		int choice = sc.nextInt();
		sc.nextLine();

		if (choice == 10) {
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
					runner.printSortedEmployeesInorder(sc);
					break;
				case 6:
					runner.deleteEmployeeById(sc);
					break;
				case 7:
					runner.addNominees(sc);
					break;
				case 8:
					runner.printNomineeDetailsByEmployee(sc);
					break;
				case 9:
					runner.printNomineeDetailsForFirstNEmployees(sc);
					break;
				}
			} catch (Exception e) {
				LOGGER.log(Level.SEVERE, "Exception occurred", e);
			}
			runner.printChoiceList();
			choice = sc.nextInt();
			sc.nextLine();
		} while (choice != 10);
	}
}
