package com.ztasks.jdbc.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.exception.InvalidArgumentException;
import com.generalutils.logger.CustomLogger;
import com.ztasks.jdbc.task.JdbcTask;
import com.ztasks.jdbc.models.Employee;
import com.ztasks.jdbc.models.EmployeeResultWrapper;

public class JdbcRunner {
	
	private static Logger LOGGER = CustomLogger.getLogger("com.ztasks.jdbc.runner.JdbcRunner", "jdbc");
	
	private void printChoiceList() {
		System.out.println("\nSno.    Functions");
		System.out.println("1 Add employees");
		System.out.println("2 Retrieve details by name");
		System.out.println("3 ");
		System.out.println("4 ");
		System.out.println("5 ");
		System.out.println("6 ");
		System.out.println("7 ");
		System.out.println("8 ");
		System.out.println("9 Exit");
		System.out.println("Please enter the corresponding serial number to perform the requied function: ");
	}
	
	private List<Employee> getEmployees(Scanner sc) {
		List<Employee> employees = new ArrayList<>();
		char choice='y';
		while(choice == 'y') {
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
	
	public void addUsers(Scanner sc) throws InvalidArgumentException {
		JdbcTask task = new JdbcTask();
		List<Employee> list = getEmployees(sc);
		
		EmployeeResultWrapper result = task.processAndAddEmployees(list);
		
		System.out.println(result.getSuccessCount()+" employee details added successfully");
		LOGGER.info(result.getSuccessCount()+"employee details added successfully");
		if(!result.isAllSuccess()) {
			LOGGER.info("The following employees were not added due to the following ...");
			System.out.println("The following employees were not added due to the following ...");
			for (Map.Entry<Employee, Map<String, String>> entry : result.getFailedEntries().entrySet()) {
	            Employee employee = entry.getKey();
	            Map<String, String> failureReasons = entry.getValue();
	            
	            System.out.println("Employee: " + employee);
	            LOGGER.info("Employee: " + employee);
	            
	            for (Map.Entry<String, String> reasonEntry : failureReasons.entrySet()) {
	                System.out.println("  - " + reasonEntry.getKey() + ": " + reasonEntry.getValue());
	                LOGGER.info("  - " + reasonEntry.getKey() + ": " + reasonEntry.getValue());
	            }
	        }
		}
	}
	
	public void retrieve

	public static void main(String args[]) throws SecurityException, IOException {
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
					break;
				case 4:
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
