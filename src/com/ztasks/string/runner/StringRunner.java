package com.ztasks.string.runner;

import com.exception.StringTaskException;
import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;
import com.ztasks.string.task.StringTask;

import java.util.Scanner;
import java.util.Arrays;
import java.util.ArrayList;

public class StringRunner {

	private StringTask task = new StringTask();
	private static Scanner sc = new Scanner(System.in);

	public void printChoiceList() {
		System.out.println("\nS.no Functions");
		System.out.println("1.   Find the length of a String");
		System.out.println("2.   Convert string  into character Array");
		System.out.println("3.   Find the character at given position");
		System.out.println("4.   Find the number of occurrences of a given letter/character");
		System.out.println("5.   Find the greatest position of the given letter/character.");
		System.out.println("6.   Print the last 'n' characters of a given String.");
		System.out.println("7.   Print the first 'n' characters of a given String");
		System.out.println("8.   Replace the first 'n' characters of a String with the given string");
		System.out.println("9.   Check whether a String starts with the given string ");
		System.out.println("10.  Check whether a String ends with the given string");
		System.out.println("11.  Convert all lowercase string to uppercase string");
		System.out.println("12.  Convert all uppercase string to lowercase string");
		System.out.println("13.  Reverse a String");
		System.out.println("14.  Accept a line with multiple Strings");
		System.out.println("15.  Concatenate multiple strings without given character");
		System.out.println("16.  Multiple strings to string array");
		System.out.println("17.  Merge multipe strings with the given character  in between");
		System.out.println("18.  Check equality of 2 strings - Case sensitive");
		System.out.println("19.  Check equality of 2 strings - Case insensitive");
		System.out.println("20.  Trim spaces");
		System.out.println("21.  Exit");
		System.out.print("Please enter the corresponding serial number to perform the requied function:");
	}

	public  void handleStringOperations(String strArr[]) {
		StringRunner runner = new StringRunner();
		int choice = sc.nextInt();
		sc.nextLine();
		
		//if choice = 21 => exit
		if(choice == 21){
			return;
		}
		
		do {
			try{
				switch (choice) {
					case 1:
						handleLength(strArr);
						break;
					case 2:
						handleCharArray();
						break;
					case 3:
						handleCharAtPos();
						break;
					case 4:
						handleCountOfChar();
						break;
					case 5:
						handleGreatestPosition();
						break;
					case 6:
						handlePrintLastNChar();
						break;
					case 7:
						handlePrintFirstNChar();
						break;
					case 8:
						handleReplaceFirstNChar();
						break;
					case 9:
						handleCheckStartsWith();
						break;
					case 10:
						handleCheckEndsWith();
						break;
					case 11:
						handleChangeCase(true);
						break;
					case 12:
						handleChangeCase(false);
						break;
					case 13:
						handleReverseString();
						break;
					case 14:
						handleAcceptMultipleString();
						break;
					case 15:
						handleConcWithoutGivenChar();
						break;
					case 16:
						handleSplit();
						break;
					case 17:
						handleMergeStrWithChar();
						break;
					case 18:
						handleEqualsWithCaseOption(true);
						break;
					case 19:
						handleEqualsWithCaseOption(false);
						break;
					case 20:
						handleTrimExtraSpace();
						break;
					default:
						System.out.println("Please enter a valid choice");
						break;
				}
		    }
			catch (InvalidArgumentException | StringTaskException e){
			System.out.println(e.getMessage());
			System.out.println("Printing Stack Trace...");
			e.printStackTrace();
		}
		runner.printChoiceList();
		choice = sc.nextInt();
		sc.nextLine();
		} while (choice != 21);
	}
	
	public static String getStringFromUser() {
		System.out.print("Enter the string :");
		String input=sc.next();
		sc.nextLine();
		return input;
	}

	public static String getSentenceFromUser() {
		System.out.print("Enter the strings :");
		return sc.nextLine();
	}

	public static ArrayList<String> getMulStrFromUser() {
		ArrayList<String> stringList = new ArrayList<>();
		char choice = 'y';
		while (choice == 'y') {
			System.out.print("Enter a string: ");
			stringList.add(sc.nextLine());
			System.out.print("Do you want to add more strings? (y/n): ");
			choice = sc.next().charAt(0);
			sc.nextLine(); 
		}
		return stringList;
	}

	public static char getCharFromUser() {
		System.out.print("Enter the character :");
		return sc.next().charAt(0);
	}

	public  static int getIntFromUser() {
		System.out.print("Enter the number :");
		int number=sc.nextInt();
		sc.nextLine();
		return number;
	}

	public void handleTrimExtraSpace()throws InvalidArgumentException {
		String str = getSentenceFromUser();
		System.out.println("The string before trimming : \"" + str + "\"");
		System.out.println("The length of the string before trimming: " + task.findLength(str));
		str = task.trimExtraSpace(str);
		System.out.println("The string after trimming: \"" + str + "\"");
		System.out.println("The length of the string after trimming: " + str.length());
	}

	public void handleEqualsWithCaseOption(boolean isCaseSensitive)throws InvalidArgumentException {
		System.out.println("Enter the strings to check whether they are equal (case sensitivity): "+isCaseSensitive);
		String str1 = getStringFromUser();
		String str2 = getStringFromUser();
		System.out.println(task.equalsWithCaseOption(str1, str2,isCaseSensitive));
	}

	public void handleMergeStrWithChar() throws InvalidArgumentException{
		ArrayList <String> strArr = getMulStrFromUser();
		System.out.println("Enter the string to merge with:");
		String merger = getStringFromUser();
		System.out.println("The string after merging:" + task.mergeStrWithChar(strArr, merger));
	}

	public void handleSplit() throws InvalidArgumentException{
		String str = getSentenceFromUser();
		System.out.println("Enter the delimeter to split the strings:");
		String str1 = getStringFromUser();
		String strArr[] = task.split(str, str1);
		System.out.print("The string array : " + Arrays.toString(strArr));
	}

	public void handleAcceptMultipleString() {
		String str = getSentenceFromUser();
		System.out.println("You entered: " + str);
	}

	public void handleConcWithoutGivenChar() throws InvalidArgumentException{
		String str = getSentenceFromUser();
		System.out.println("Enter the string to be removed:");
		String str1 = getStringFromUser();
		System.out.println("Enter the string to replce with :");
		String str2 = getStringFromUser();
		System.out.print(
				"After concatenation of strings without " + str1 + ":" + task.concWithoutGivenChar(str, str1, str2));
	}

	public void handleReverseString() throws InvalidArgumentException {
		String str = getStringFromUser();
		System.out.println(str + "->" + task.reverseString(str));
	}


	public void handleChangeCase(boolean toUpper)throws InvalidArgumentException {
		String str = getStringFromUser();
		System.out.println(str + "->" + task.changeCase(str,toUpper));
	}

	public void handleCheckEndsWith()throws InvalidArgumentException {
		String str = getStringFromUser();
		System.out.println("Enter the string to check for:");
		String ender = getStringFromUser();
		boolean ans = task.checkEndsWith(str, ender);
		System.out.println("The String " + str + " ends with " + ender + " : " + ans);
	}

	public void handleCheckStartsWith()throws InvalidArgumentException {
		String str = getStringFromUser();
		System.out.println("Enter the string to check for:");
		String starter = getStringFromUser();
		boolean ans = task.checkStartsWith(str, starter);
		System.out.println("The String " + str + " starts with " + starter + " : " + ans);
	}

	public void handleReplaceFirstNChar() throws StringTaskException,InvalidArgumentException{
		String str = getStringFromUser();
		System.out.println("Enter the number of characters");
		int n = getIntFromUser();
		System.out.println("Enter the string to be replaced with:");
		String repStr = getStringFromUser();
		String outputStr = task.replaceFirstNChar(str, n, repStr);
		System.out.println("On replacing the first " + n + " characters of the string " + str + " with " + repStr
				+ " , it became  " + outputStr);
	}

	public void handlePrintFirstNChar() throws StringTaskException,InvalidArgumentException{
		String str = getStringFromUser();
		System.out.println("Enter the number of characters");
		int n = getIntFromUser();
		String outputStr = task.getFirstNChar(str, n);
		System.out.println("The first " + n + " characters of the string " + str + " is " + outputStr);
	}

	public void handlePrintLastNChar() throws StringTaskException,InvalidArgumentException{
		String str = getStringFromUser();
		System.out.println("Enter the number of characters");
		int n = getIntFromUser();
		String outputStr = task.getLastNChar(str, n);
		System.out.println("The last " + n + " characters of the string " + str + " is " + outputStr);
	}

	public void handleGreatestPosition() throws InvalidArgumentException{
		String str = getStringFromUser();
		char choice = 'y';
		while (choice == 'y') {
			char letter = getCharFromUser();
			int index = task.greatestPosOfChar(str, letter);
			if (index != -1) {
				System.out.println(
						"The greatest position of  " + letter + " in the string " + str + " is " + (index + 1));
			} else {
				System.out.println("Character " + letter + " is not found in the string " + str);
			}
			System.out.print("Do you want to check for another letter/character (Enter 'y' for yes and 'n' for no) :");
			choice = sc.next().charAt(0);
		}
	}

	public void handleCountOfChar()throws InvalidArgumentException {
		String str = getStringFromUser();
		char choice = 'y';
		while (choice == 'y') {
			char letter = getCharFromUser();
			int count = task.countOfChar(str, letter);
			System.out.println("The  number of occurrences of " + letter + " in the string " + str + " is " + count);
			System.out.print("Do you want to check for another letter/character (Enter 'y' for yes and 'n' for no) :");
			choice = sc.next().charAt(0);
		}
	}

	public void handleCharAtPos() throws StringTaskException,InvalidArgumentException {
		String str = getStringFromUser();
		int n = getIntFromUser();
		char posChar = task.charAtPos(str, n);
		System.out.println("The character at position " + n + " of the string " + str + " is " + posChar);
	}

	public void handleCharArray()throws InvalidArgumentException {
		String str = getStringFromUser();
		char[] charArray = task.convertToCharArray(str);
		System.out.println(str + " on converting to character array... ");
		for (char c : charArray) {
			System.out.print(c + " ");
		}
		System.out.println();
	}

	public void handleLength(String strArr[])throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(strArr);
		if (strArr.length > 0) {
			int length = task.findLength(strArr[0]);
			System.out.println("The length of the string " + strArr[0] + " = " + length);
		} else {
			System.out.println(
					"No string provided as command line argument ,try running the file again with the arguments");
		}
	}

	public static void main(String args[]) {
		try{
			StringRunner runner = new StringRunner();
			runner.printChoiceList();
			runner.handleStringOperations(args);
		}
		finally{
			sc.close();
		} 
	}
}
