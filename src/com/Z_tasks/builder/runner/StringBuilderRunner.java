package com.Z_tasks.builder.runner;

import com.Z_tasks.builder.task.StringBuilderTask;
import com.exception.InvalidArgumentException;
import com.exception.StringBuilderException;
import com.generalutils.GeneralUtils;

import java.util.Scanner;
import java.util.ArrayList;

public class StringBuilderRunner{
	
	private StringBuilderTask task = new StringBuilderTask();
	
	//helper methods
	
	public void printChoiceList(){
		System.out.println("\nSno.    Functions");
		System.out.println("1  Create empty stringbuilder and append");
		System.out.println("2  Create stringbuilder and append");
		System.out.println("3  Insert strings in stringbuilder");
		System.out.println("4  Delete strings in stringbuilder");
		System.out.println("5  Replace strings in stringbuilder");
		System.out.println("6  Reverse StringBuilder");
		System.out.println("7  Delete Characters");
		System.out.println("8  Replace characters");
		System.out.println("9  Find first occurrence of string");
		System.out.println("10 Find last occurrence of string");
		System.out.println("11 Exit");
		System.out.print("Please enter the corresponding serial number to perform the requied function:");
	}
	
	public ArrayList<String> getStringFromUser(Scanner sc)throws InvalidArgumentException{
		ArrayList <String> arrList = new ArrayList<>();
		char choice = 'y';
		while(choice == 'y'){
			System.out.print("Enter the string: ");
			arrList.add(sc.nextLine());
			System.out.print("Do you want to add more strings(Enter y for yes/n for no):");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		return arrList;
	}
	
	public StringBuilder getStringBuilder(StringBuilder strBuilder ,ArrayList<String> strArr,String delimiter)
	throws InvalidArgumentException{
		int length = strArr.size();
		for(int i=0;i<length;i++){
			strBuilder = task.appendWithDelimiter(strBuilder,delimiter,strArr.get(i));
		}
		return strBuilder;
	}
	
	public StringBuilder createStringBuilderWithStrings(Scanner sc,String delimiter,int minLength)
	throws InvalidArgumentException{ 
		ArrayList <String> strArr = getStringFromUser(sc);
		StringBuilder strBuilder = task.getStringBuilder();
		strBuilder = getStringBuilder(strBuilder,strArr,delimiter);
		if( minLength == -1){
			return strBuilder;
		}
		while(GeneralUtils.findLength(strBuilder) < minLength){
			strArr.clear();
			System.out.print("Enter string to satisfy minLength: ");
			strArr.add(sc.nextLine());
			strBuilder = getStringBuilder(strBuilder,strArr,delimiter);
		}
		return strBuilder;
	}
	
	public StringBuilder addStringToStrBuilder (Scanner sc,String delimiter , StringBuilder strBuilder)
	throws InvalidArgumentException{
		ArrayList <String> arrList = getStringFromUser(sc);
		return getStringBuilder(strBuilder,arrList,delimiter);
	}
	
	public void printStrBuilderAndLength(StringBuilder strBuilder)
	throws InvalidArgumentException{
		int length = GeneralUtils.findLength(strBuilder);
		System.out.println("The string builder : "+strBuilder);
		System.out.println("The length : "+length);
	}
	
	public String getDelimiter (Scanner sc){
		System.out.print("Enter the delimiter to separate the strings: ");
		return sc.nextLine();
	}
	
	//task handling methods
	public void createAndAppendStringBuilder(Scanner sc)
	throws InvalidArgumentException{
		String delimiter =getDelimiter(sc);
		StringBuilder strBuilder = task.getStringBuilder();
		System.out.println("Initial stringbuilder");
		printStrBuilderAndLength(strBuilder);
		strBuilder = addStringToStrBuilder(sc,delimiter,strBuilder);
		printStrBuilderAndLength(strBuilder);
	}
	
	public  void createAndAddMultipleStrings (Scanner sc)
	throws InvalidArgumentException{
		String delimiter = getDelimiter(sc);
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,-1);
		printStrBuilderAndLength(strBuilder);
		strBuilder = addStringToStrBuilder(sc,delimiter,strBuilder);
		printStrBuilderAndLength(strBuilder);	
	}
	
	public void insertString (Scanner sc) 
	throws InvalidArgumentException,StringBuilderException{
		String delimiter = getDelimiter(sc);
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,-1);
		printStrBuilderAndLength(strBuilder);
		char choice = 'y';
		while(choice == 'y'){
			System.out.print("Enter the number after which string should we insert: ");
			int insertAfter = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the string: ");
			String str = sc.nextLine();
			strBuilder = task.insertString(insertAfter,delimiter,str,strBuilder);
			printStrBuilderAndLength(strBuilder);
			System.out.print("Do you want to insert another string (Enter y for yes/n for no):");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		printStrBuilderAndLength(strBuilder);
	}
	
	public void deleteString(Scanner sc)
	throws InvalidArgumentException,StringBuilderException{
		String delimiter = getDelimiter(sc);
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,-1);
		printStrBuilderAndLength(strBuilder);
		
		char choice = 'y';
		while(choice == 'y'){
			System.out.print("Enter the string number to delete: ");
			int deleteStrAt = sc.nextInt();
			sc.nextLine();
			strBuilder = task.delete(deleteStrAt,delimiter,strBuilder);
			printStrBuilderAndLength(strBuilder);
			if(GeneralUtils.findLength(strBuilder)==0){
				System.out.println("String builder ran out of strings");
				break;
			}
			System.out.print("Do you want to delete another string (Enter y for yes/n for no):");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		printStrBuilderAndLength(strBuilder);
	}
	
	public void replaceString(Scanner sc)
	throws InvalidArgumentException,StringBuilderException{
		String delimiter = getDelimiter(sc);
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,-1);
		printStrBuilderAndLength(strBuilder);
		char choice = 'y';
		while(choice == 'y'){
			System.out.print("Enter the string to replace: ");
			String toReplace = sc.nextLine();
			System.out.print("Enter the string to replace "+toReplace+" with :");
			String replaceWith = sc.nextLine();
			strBuilder = task.replace(toReplace,replaceWith,strBuilder);
			printStrBuilderAndLength(strBuilder);
			System.out.print("Do you want to replace another string (Enter y for yes/n for no):");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		printStrBuilderAndLength(strBuilder);
	}
	
	public void reverse(Scanner sc)
	throws InvalidArgumentException{
		String delimiter = getDelimiter(sc);
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,-1);
		printStrBuilderAndLength(strBuilder);
		strBuilder = task.reverse(strBuilder);
		printStrBuilderAndLength(strBuilder);
	}
	
	public void findIndex(Scanner sc,Boolean isFirst)
	throws InvalidArgumentException{
		String delimiter = getDelimiter(sc);
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,-1);
		printStrBuilderAndLength(strBuilder);
		char choice = 'y';
		while(choice == 'y'){
			System.out.print("Enter the string to find the index(fromFirst: "+isFirst+" ):");
			String str = sc.nextLine();
			int index = task.findIndex(str ,strBuilder,isFirst);
			if ( index == -1){
				System.out.println(str+" not found in "+strBuilder);
			}
			else{
				System.out.println("Index of "+str+" in "+strBuilder+" : "+index);
			}
			System.out.print("Do you want to check for another string (Enter y for yes/n for no):");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
	}
	
	public void deleteChars(Scanner sc)throws InvalidArgumentException,StringBuilderException{
		String delimiter = getDelimiter(sc);
		System.out.print("Enter the minimum number of string length to create the string builder: ");
		int minLength = sc.nextInt();
		sc.nextLine();
		if ( minLength < 0){
			throw new StringBuilderException("Minimum Length can't be negative");
		}
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,minLength);
		printStrBuilderAndLength(strBuilder);
		System.out.print("Enter the start index (inclusive): ");
		int startIndex = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter the end index (exclusive): ");
		int endIndex = sc.nextInt();
		sc.nextLine();
		strBuilder = task.delete(startIndex,endIndex,strBuilder);
		printStrBuilderAndLength(strBuilder);
	}
	
	public void replaceChars(Scanner sc)throws InvalidArgumentException,StringBuilderException{
		String delimiter = getDelimiter(sc);
		System.out.print("Enter the minimum number of string length to create the string builder: ");
		int minLength = sc.nextInt();
		sc.nextLine();
		if ( minLength < 0){
			throw new StringBuilderException("Minimum Length can't be negative");
		}
		StringBuilder strBuilder = createStringBuilderWithStrings(sc,delimiter,minLength);
		printStrBuilderAndLength(strBuilder);
		System.out.print("Enter the start index to replace(inclusive): ");
		int startIndex = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter the end index to replace (exclusive): ");
		int endIndex = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter the string to replace:");
		String replaceWith = sc.nextLine();
		strBuilder = task.replace(startIndex,endIndex,replaceWith,strBuilder);
		printStrBuilderAndLength(strBuilder);
	}
	
	public static void main (String args[]){
		Scanner sc = new Scanner(System.in);
		try{
			
			StringBuilderRunner runner = new StringBuilderRunner();
			runner.printChoiceList();
			int choice = sc.nextInt();
			sc.nextLine();
		
			//if choice = 11 => exit
			if(choice == 11){
				return;
			}
			
			do{
				try{
					switch(choice){
						case 1:
							runner.createAndAppendStringBuilder(sc);
							break;
						case 2:
							runner.createAndAddMultipleStrings(sc);
							break;
						case 3:
							runner.insertString(sc);
							break;
						case 4:
							runner.deleteString(sc);
							break;
						case 5:
							runner.replaceString(sc);
							break;
						case 6:
							runner.reverse(sc);
							break;
						case 7:
							runner.deleteChars(sc);
							break;
						case 8:
							runner.replaceChars(sc);
							break;
						case 9:
							runner.findIndex(sc,true);
							break;
						case 10:
							runner.findIndex(sc,false);
							break;
						
					}
				}
				catch(InvalidArgumentException | StringBuilderException e){
					System.out.println(e.getMessage());
					System.out.println("Printing Stack Trace...");
					e.printStackTrace();
				}
				runner.printChoiceList();
				choice = sc.nextInt();
				sc.nextLine();
				
			}while(choice!=11);
		}
		finally{
			sc.close();
			System.out.println("scanner closed");
		}
	}
}
