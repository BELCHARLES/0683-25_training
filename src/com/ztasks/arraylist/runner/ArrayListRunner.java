package com.ztasks.arraylist.runner;
 
import com.generalutils.GeneralUtils;
import com.ztasks.arraylist.task.ArrayListTask;
import com.generalutils.CustomClass;
import com.exception.InvalidArgumentException;
import com.exception.BoundaryCheckException;

import java.util.List;	
import java.util.Iterator;
import java.util.Scanner;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;

public class ArrayListRunner{
	
	private ArrayListTask task = new ArrayListTask();

	//helper methods
	
	private void printChoiceList(){
		System.out.println("\nSno.    Functions");
		System.out.println("1  Create array list");
		System.out.println("2  String arraylist");
		System.out.println("3  Integer Arraylist");
		System.out.println("4  Custom Object ArrayList");
		System.out.println("5  Object ArrayList");
		System.out.println("6  Find index of string");
		System.out.println("7  Traversal - Iterator & For loop");
		System.out.println("8  Get element at index");
		System.out.println("9  Find first and last index");
		System.out.println("10 Add at specified index");
		System.out.println("11 Sublist");
		System.out.println("12 Combine Lists");
		System.out.println("13 Remove element at index");
		System.out.println("14 Remove all elements present in another");
		System.out.println("15 Retainall");
		System.out.println("16 Remove all elements");
		System.out.println("17 Contains element ");
		System.out.println("18 Exit");
		System.out.print("Please enter the corresponding serial number to perform the requied function:");
	}
	
	private void printArrayListAndSize(List list) throws InvalidArgumentException{
		System.out.println("Size of array list: "+GeneralUtils.findLength(list));
		System.out.println(list);
	}
	
	public void createPrintArrayList()throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		printArrayListAndSize(task.createList());
	}
	
	public List<String> stringArrayList(Scanner sc)throws InvalidArgumentException, ClassNotFoundException,	 NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = task.createList();
		System.out.print("Enter the number of string elements to add: ");
		int num = sc.nextInt();
		sc.nextLine();
		String [] strArr = new String[num];
		for(int i=0 ; i<num ; i++){
			System.out.print("Enter the string: ");
			strArr[i]=sc.nextLine();
		}
		stringList = task.addElements(stringList,strArr);
		printArrayListAndSize(stringList);
		return stringList;
	}
	
	public void integerArrayList(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<Integer> integerList = task.createList();
		System.out.print("Enter the number of integer elements to add: ");
		int num = sc.nextInt();
		sc.nextLine();
		Integer [] intArr = new Integer[num];
		for(int i=0 ; i<num ; i++){
			System.out.print("Enter the number: ");
			intArr[i]=sc.nextInt();
			sc.nextLine();
		}
		integerList = task.addElements(integerList,intArr);
		printArrayListAndSize(integerList);
	}
	
	public void customObjArrayList(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<CustomClass> customList = task.createList();
		System.out.print("Enter the number of custom objects: ");
		int num = sc.nextInt();
		sc.nextLine();
		CustomClass [] customObjects = new CustomClass[num];
		for(int i=0 ; i<num ; i++){
			System.out.print("Enter the name of object: ");
			customObjects[i]=new CustomClass(sc.nextLine());
		}
		customList = task.addElements(customList,customObjects);
		printArrayListAndSize(customList);
	}
	
	public void ObjectArrayList(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<Object> objList = task.createList();
		System.out.print("Enter the number of integers,string,custom object(eg:2 3 2): ");
		int count1 = sc.nextInt();
		int count2 = sc.nextInt();
		int count3 = sc.nextInt();
		sc.nextLine();
		Object[] objArr = new Object[count1+count2+count3];
		int ind = 0;
		for(int i=0 ; i<count1 ; i++){
			System.out.print("Enter the number: ");
			objArr[ind++]=sc.nextInt();
			sc.nextLine();
		}
		for(int i=0 ; i<count2 ; i++){
			System.out.print("Enter the string: ");
			objArr[ind++]=sc.nextLine();
		}
		for(int i=0 ; i<count3 ; i++){
			System.out.print("Enter the name of object: ");
			objArr[ind++]=new CustomClass(sc.nextLine());
		}
		objList = task.addElements(objList,objArr);
		printArrayListAndSize(objList);
	}
	
	public void findIndex(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = stringArrayList(sc);
		System.out.println("Enter string to find index: ");
		String str = sc.nextLine();
		System.out.println("Index of "+str+" is "+task.indexOf(stringList,str));
	}
	
	/*Iterator -> returns object to traverse collections
			   -> fail fast (Concurrent modification exception)
		       -> safe removal possible(iterator.remove())*/
	/*For loop -> easy representation & traversal
			   -> use when no modifications require*/
	public void traversals(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String>stringList = stringArrayList(sc);
		System.out.println("Iterator traversal:");
		Iterator<String> strIterator = task.createIterator(stringList);
		while(strIterator.hasNext()){
			System.out.print(strIterator.next()+" ");
		}
		
		System.out.println("For loop traversal:");
		for(String str : stringList){
			System.out.print(str+" ");
		}
	}
	
	public void getElementAtIndex(Scanner sc)throws InvalidArgumentException,BoundaryCheckException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = stringArrayList(sc);
		System.out.print("Enter the index of element:");
		int ind = sc.nextInt();
		sc.nextLine();
		System.out.println("Element at index "+ind+" is "+task.get(stringList,ind));
	}
	
	public void firstAndLastIndex(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = stringArrayList(sc);
		System.out.print("Enter string to find 1st and last index: ");
		String str = sc.nextLine();
		System.out.println("First Index of "+str+" is "+task.indexOf(stringList,str));
		System.out.println("Last Index of "+str+" is "+task.lastIndexOf(stringList,str));
	}
	
	public void addAtIndex(Scanner sc)throws InvalidArgumentException,BoundaryCheckException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = stringArrayList(sc);
		char choice = 'y';
		while(choice == 'y'){
			System.out.print("Enter string to add: ");
			String str = sc.nextLine();
			System.out.print("Enter index: ");
			int ind = sc.nextInt();
			sc.nextLine();
			System.out.println("After adding");
			printArrayListAndSize(task.add(stringList,str,ind));
			System.out.print("Do you want to add more strings(Enter y/n): ");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
	}
	
	public void newListFromExistingList(Scanner sc)throws BoundaryCheckException,InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = stringArrayList(sc);
		System.out.print("Enter from(inclusive) and to(exclusive) index of sublist(eg: 2 3): ");
		int from = sc.nextInt();
		int to = sc.nextInt();
		sc.nextLine();
		List<String> stringList1 = task.newListFromExistingList(stringList,from,to);
		printArrayListAndSize(stringList1);
	}
	
	public void combineLists(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList1 = stringArrayList(sc);
		List<String> stringList2 = stringArrayList(sc);
		System.out.print("Enter 1 for list1+list2 / 0 for list2+list1: ");
		int num = sc.nextInt();
		sc.nextLine();
		List<String> stringList;
		if(num == 1){
			stringList = task.combineLists(stringList1,stringList2);
		}
		else{
			stringList = task.combineLists(stringList2,stringList1);
		}
		printArrayListAndSize(stringList);
	}
	
	private List<Float> floatArrayList(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<Float> floatList = task.createList();
		System.out.print("Enter the number of float elements to add: ");
		int num = sc.nextInt();
		sc.nextLine();
		Float[] floatArr = new Float[num];
		for(int i=0 ; i<num ; i++){
			System.out.print("Enter the float: ");
			floatArr[i]=sc.nextFloat();
		}
		floatList = task.addElements(floatList,floatArr);
		printArrayListAndSize(floatList);
		return floatList;
	}
	
	public void remove (Scanner sc)throws InvalidArgumentException,BoundaryCheckException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<Float> floatList = floatArrayList(sc);
		System.out.print("Enter index of element to remove : ");
		int ind = sc.nextInt();
		sc.nextLine();
		floatList = task.remove(floatList , ind);
		printArrayListAndSize(floatList);
	}
	
	public void removeAll (Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList1 = stringArrayList(sc);
		List<String> stringList2 = stringArrayList(sc);
		stringList1 = task.removeAll(stringList1,stringList2);
		System.out.println("After removing list2 elements from list1");
		printArrayListAndSize(stringList1);
		printArrayListAndSize(stringList2);
	}
	
	public void retainAll (Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList1 = stringArrayList(sc);
		List<String> stringList2 = stringArrayList(sc);
		stringList1 = task.retainAll(stringList1,stringList2);
		System.out.println("After removing list1 elements which are not present in list2");
		printArrayListAndSize(stringList1);
		printArrayListAndSize(stringList2);
	}
	
	private List<Long> longArrayList(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<Long> longList = task.createList();
		System.out.print("Enter the number of long elements to add: ");
		int num = sc.nextInt();
		sc.nextLine();
		Long[] longArr = new Long[num];
		for(int i=0 ; i<num ; i++){
			System.out.print("Enter the long: ");
			longArr[i]=sc.nextLong();
		}
		longList = task.addElements(longList,longArr);
		printArrayListAndSize(longList);
		return longList;
	}
	
	public void clear(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException{
		List<Long> longList = longArrayList(sc);
		longList = task.clear(longList);
		printArrayListAndSize(longList);
	}
	
	public void contains(Scanner sc)throws InvalidArgumentException, ClassNotFoundException, NoSuchMethodException, InstantiationException, InvocationTargetException, IllegalAccessException, IllegalArgumentException, IOException{
		List<String> stringList = stringArrayList(sc);
		System.out.print("Enter element to check its presence: ");
		String str = sc.nextLine();
		System.out.println(str+ " present in list: "+task.contains(stringList,str));
	}
	
	
	public static void main (String args[]){
		Scanner sc = new Scanner(System.in);
			
		ArrayListRunner runner = new ArrayListRunner();
		runner.printChoiceList();
		int choice = sc.nextInt();
		sc.nextLine();
		
		//if choice = 18 => exit
		if(choice == 18){
			return;
		}	
		do{
			try{
				switch(choice){
					case 1:
						runner.createPrintArrayList();
						break;
					case 2:
						runner.stringArrayList(sc);
						break;
					case 3:
						runner.integerArrayList(sc);
						break;	
					case 4:
						runner.customObjArrayList(sc);
						break;
					case 5:
						runner.ObjectArrayList(sc);
						break;
					case 6:
						runner.findIndex(sc);
						break;
					case 7:
						runner.traversals(sc);
						break;
					case 8:
						runner.getElementAtIndex(sc);
						break;
					case 9:
						runner.firstAndLastIndex(sc);
						break;
					case 10:
						runner.addAtIndex(sc);
						break;
					case 11:
						runner.newListFromExistingList(sc);
						break;
					case 12:
						runner.combineLists(sc);
						break;
					case 13:
						runner.remove(sc);
						break;
					case 14:
						runner.removeAll(sc);
						break;
					case 15:
						runner.retainAll(sc);
						break;
					case 16:
						runner.clear(sc);
						break;
					case 17:
						runner.contains(sc);
						break;
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			runner.printChoiceList();
			choice = sc.nextInt();
			sc.nextLine();			
		}while(choice!=18);
	}
}
