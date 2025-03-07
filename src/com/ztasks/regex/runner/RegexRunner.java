package com.ztasks.regex.runner;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.Logger;

import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;
import com.generalutils.logger.CustomLogger;
import com.ztasks.regex.task.RegexTask;

public class RegexRunner {

	private static Logger LOGGER = CustomLogger.getLogger("com.ztasks.regex.runner.RegexRunner", "regex");

	private void printChoiceList() {
		System.out.println("\nSno.    Functions");
		System.out.println("1 Mobile number validation");
		System.out.println("2 AlphaNumeric characters");
		System.out.println("3 Match 2 strings(string,matchingString)");
		System.out.println("4 Case Insensitive matching of strings");
		System.out.println("5 Email validation");
		System.out.println("6 Validate string length");
		System.out.println("7 List - matching string list(matching indices map)");
		System.out.println("8 Extract html tags from input");
		System.out.println("9 Exit");
		System.out.println("Please enter the corresponding serial number to perform the requied function: ");
	}
	
	private List<String> createListOfStrings(Scanner sc) {
		List<String> list = new ArrayList<String>();
		char choice = 'y';
		while (choice == 'y') {
			System.out.print("Enter the string: ");
			list.add(sc.nextLine());
			System.out.print("Do you want to add more strings? (y/n)");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		return list;
	}
	
	 private static List<String> findAndAddMissingElements(List<String> matchingList, List<String> list) {
	        List<String> missingElements = new ArrayList<>();
	        
	        for (String item : matchingList) {
	            if (!list.contains(item)) {
	                missingElements.add(item);
	                list.add(item); 
	            }
	        }

	        if (!missingElements.isEmpty()) {
	            System.out.println("The following elements were missing and have been added to the list: " + missingElements);
	        }
	        return list;
	    }

	public void validateMobile(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.println("i.Should have 10 numbers.\n" + "ii.First digit can start with 7 or 8 or 9 only.\n"
				+ "iii.Should not accept string & special characters");
		System.out.print("Enter your mobile number: ");
		String mobile = sc.nextLine();
		boolean result = task.validateMobile(mobile);
		LOGGER.info(mobile+" is valid mobile number? "+result);
	}

	public void isAlphaNumeric(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.println("Enter alphanumeric characters: ");
		String str = sc.nextLine();
		boolean result = task.validateAlphaNumeric(str);
		LOGGER.info(str+ "is alphanumeric? "+result);
	}

	public void matchStringPatterns(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.println("Enter the string: ");
		String str = sc.nextLine();
		System.out.println("Enter the matching string to check with: ");
		String matchingStr = sc.nextLine();

		boolean result = task.checkStartsWith(str, matchingStr);
		LOGGER.info(str + " starts with " + matchingStr + " : " + result);

		result = task.checkEndsWith(str, matchingStr);
		LOGGER.info(str + " ends with " + matchingStr + " : " + result);

		result = task.checkContains(str, matchingStr);
		LOGGER.info(str + " contains  " + matchingStr + " : " + result);

		result = task.checkExactMatch(str, matchingStr);
		LOGGER.info(str + " exactly matches with  " + matchingStr + " : " + result);
	}

	public void findCaseInsensitiveMatches(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();

		System.out.print("Enter the matching string: ");
		String matchingStr = sc.nextLine();
		System.out.println("Enter list of strings which may include duplicates of matching string");
		List<String> list = createListOfStrings(sc);

		List<Boolean> resultList = task.validateCaseInsensitive(list, matchingStr);

		int length = GeneralUtils.findLength(list);
		for (int i = 0; i < length; i++) {
			LOGGER.info(list.get(i) + " matching with " + matchingStr + " ---> " + resultList.get(i));
		}

	}

	public void validateMail(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.print("Enter mail id :");
		String mail = sc.nextLine();
		boolean result = task.validateMail(mail);
		LOGGER.info(mail+" is valid mail?? "+result);
	}
	
	public void validateStringLength(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.print("Enter string of length 1-6:");
		String str = sc.nextLine();
		boolean result = task.validateStringLength(str);
		LOGGER.info(str+" is valid string of length 1-6?? "+result);
	}
	
	public void findMatchingIndices(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.println("Enter the list of matching strings:");
		List<String> matchingList = createListOfStrings(sc);
		System.out.println("Enter the list of strings (must contain all values of matching strings list) ");
		List<String> list = createListOfStrings(sc);
		
		list = findAndAddMissingElements(matchingList, list);
		Map<String,List<Integer>> map = task.getMatchingIndices(list,matchingList);
		LOGGER.info(map.toString());
	}
	
	public void handleHtmlTagExtraction(Scanner sc) throws InvalidArgumentException {
		RegexTask task = new RegexTask();
		System.out.println("Enter the string with html tags");
		String input = sc.nextLine();
		List<String> result= task.extractHtmlTags(input);
		LOGGER.info("HTML Tags: "+result.toString());
	}

	public static void main(String args[]) throws SecurityException, IOException {
		Scanner sc = new Scanner(System.in);

		RegexRunner runner = new RegexRunner();
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
					runner.validateMobile(sc);
					break;
				case 2:
					runner.isAlphaNumeric(sc);
					break;
				case 3:
					runner.matchStringPatterns(sc);
					break;
				case 4:
					runner.findCaseInsensitiveMatches(sc);
					break;
				case 5:
					runner.validateMail(sc);
					break;
				case 6:
					runner.validateStringLength(sc);
					break;
				case 7:
					runner.findMatchingIndices(sc);
					break;
				case 8:
					runner.handleHtmlTagExtraction(sc);
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
