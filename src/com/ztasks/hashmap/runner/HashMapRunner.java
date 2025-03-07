package com.ztasks.hashmap.runner;
 
import com.generalutils.GeneralUtils;
import com.ztasks.hashmap.task.HashMapTask;
import com.generalutils.CustomClass;
import com.exception.InvalidArgumentException;

import java.util.Scanner;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;


public class HashMapRunner{
	
	private HashMapTask task = new HashMapTask();

	//helper methods
	
	private void printChoiceList(){
		System.out.println("\nSno.    Functions");
		System.out.println("1  Create hash map");
		System.out.println("2  String Hash Map");
		System.out.println("3  Integer hash map");
		System.out.println("4  String key Integer values");
		System.out.println("5  String Key Custom Object Value");
		System.out.println("6  Null Key/valued map");
		System.out.println("7  Contains key");
		System.out.println("8  Contains value");
		System.out.println("9  Change all values");
		System.out.println("10 Get value");
		System.out.println("11 Get value / default value");
		System.out.println("12 Remove key");
		System.out.println("13 Remove key if value matches");
		System.out.println("14 Replace value");
		System.out.println("15 Replace if value matches");
		System.out.println("16 Combine HashMap");
		System.out.println("17 Iterate hashmap");
		System.out.println("18 Clear map");
		System.out.println("19 Exit");
		System.out.print("Please enter the corresponding serial number to perform the requied function:");
	}
	
	private void printHashMapAndSize(Map map) throws InvalidArgumentException{
		System.out.println("Size of Hashmap: "+GeneralUtils.findLength(map));
		System.out.println(map);
	}
	
	public void createPrintHashMap()throws InvalidArgumentException{
		printHashMapAndSize(task.createMap());
	}
			
	public Map<String,String> stringHashMap (Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = task.createMap();
		System.out.print("Enter the number of elements to add: ");
		int count = sc.nextInt();
		sc.nextLine();
		String key;
		String value;
		for(int i =0;i<count;i++){
			System.out.print("Enter the key: ");
			key = sc.nextLine();
			System.out.print("Enter the value: ");
			value = sc.nextLine();
			task.put(map,key,value);
		}
		printHashMapAndSize(map);
		return map;
	}
		
	public void integerHashMap (Scanner sc)throws InvalidArgumentException{
		Map<Integer,Integer> map = task.createMap();
		System.out.print("Enter the number of elements to add: ");
		int count = sc.nextInt();
		sc.nextLine();
		Integer key;
		Integer value;
		for(int i =0;i<count;i++){
			System.out.print("Enter the key: ");
			key = sc.nextInt();
			sc.nextLine();
			System.out.print("Enter the value: ");
			value = sc.nextInt();
			sc.nextLine();
			task.put(map,key,value);
		}
		printHashMapAndSize(map);
	}
	
	public void strKeyIntVal (Scanner sc)throws InvalidArgumentException{
		Map<String,Integer> map = task.createMap();
		System.out.print("Enter the number of elements to add: ");
		int count = sc.nextInt();
		sc.nextLine();
		String key;
		Integer value;
		for(int i =0;i<count;i++){
			System.out.print("Enter the key: ");
			key = sc.nextLine();
			System.out.print("Enter the value: ");
			value = sc.nextInt();
			sc.nextLine();
			task.put(map,key,value);
		}
		printHashMapAndSize(map);
	}
	
	public void strKeyCustomVal (Scanner sc)throws InvalidArgumentException{
		Map<String,CustomClass> map = task.createMap();
		System.out.print("Enter the number of elements to add: ");
		int count = sc.nextInt();
		sc.nextLine();
		String key;
		CustomClass value;
		for(int i =0;i<count;i++){
			System.out.print("Enter the key: ");
			key = sc.nextLine();
			System.out.print("Enter the value: ");
			value = new CustomClass(sc.nextLine());
			task.put(map,key,value)	;
		}
		printHashMapAndSize(map);
	}
	
	public void nullMap (Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = task.createMap();
		System.out.print("Enter the number of elements to add: ");
		int count = sc.nextInt();
		sc.nextLine();
		String key;
		String value;
		for(int i =0;i<count;i++){
			System.out.print("Enter the key: ");
			key = sc.nextLine();
			System.out.print("Enter the value: ");
			value = sc.nextLine();
			task.put(map,key,value);
		}
		System.out.print("Enter 1 for null key/0 for null value: ");
		int choice = sc.nextInt();
		sc.nextLine();
		if(choice==1){
			key=null;
			value = "Zoho";
			task.put(map,key,value);
		}
		else{
			key = "Zoho";
			value=null;
			task.put(map,key,value);
		}
		printHashMapAndSize(map);
	}
	
	public void containsKey(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = stringHashMap(sc);
		System.out.print("Enter the key: ");
		String key = sc.nextLine();
		System.out.println("Key "+key+" present in map: "+task.containsKey(map,key));
	}
	
	public void containsValue(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = stringHashMap(sc);
		System.out.print("Enter the value: ");
		String value = sc.nextLine();
		System.out.println("Value "+value+" present in map: "+task.containsValue(map,value));
	}
	
	public void changeAllValues(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = task.createMap();
		System.out.print("Enter the number of elements to add: ");
		int count = sc.nextInt();
		sc.nextLine();
		String []keyArr = new String[count];
		String value;
		for(int i =0;i<count;i++){
			System.out.print("Enter the key: ");
			keyArr[i] = sc.nextLine();
			System.out.print("Enter the value: ");
			value = sc.nextLine();
			task.put(map,keyArr[i],value);
		}
		printHashMapAndSize(map);
		System.out.println("Enter the new set of values for the existing keys in order:");
		for(int i =0;i<count;i++){
			System.out.print("Enter the value: ");
			value = sc.nextLine();
			task.put(map,keyArr[i],value);
		}
		printHashMapAndSize(map);
	}
	
	public void get(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = stringHashMap(sc);
		System.out.print("Enter the key to get its value: ");
		String key = sc.nextLine();
		System.out.println(key+" : "+task.get(map,key));
	}
	
	public void getOrDefault(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = stringHashMap(sc);
		System.out.print("Enter the key to get its value: ");
		String key = sc.nextLine();
		System.out.print("Enter a default value: ");
		String defaultVal = sc.nextLine();
		System.out.println(key+" : "+task.getOrDefault(map,key,defaultVal));
		printHashMapAndSize(map);
	}
	
	public void remove(Scanner sc)throws InvalidArgumentException{
		Map<String ,String> map = stringHashMap(sc);
		System.out.print("Enter the key to remove: ");
		String key = sc.nextLine();
		task.remove(map,key);
		System.out.println("After removing");
		printHashMapAndSize(map);
	}
	
	public void removeIfMatch(Scanner sc)throws InvalidArgumentException{
		Map<String ,String> map = stringHashMap(sc);
		System.out.print("Enter the key to remove: ");
		String key = sc.nextLine();
		System.out.print("Enter the value to match: ");
		String value = sc.nextLine();
		task.remove(map,key,value);
		System.out.println("After removing");
		printHashMapAndSize(map);
	}
	
	public void replace(Scanner sc)throws InvalidArgumentException{
		Map<String ,String> map = stringHashMap(sc);
		System.out.print("Enter the key : ");
		String key = sc.nextLine();
		System.out.print("Enter the value : ");
		String value = sc.nextLine();
		task.replace(map,key,value);
		System.out.println("After replacing");
		printHashMapAndSize(map);
	}
	
	public void replaceIfMatch(Scanner sc)throws InvalidArgumentException{
		Map<String ,String> map = stringHashMap(sc);
		System.out.print("Enter the key : ");
		String key = sc.nextLine();
		System.out.print("Enter the old value: ");
		String oldValue = sc.nextLine();
		System.out.print("Enter the new value: ");
		String newValue = sc.nextLine();
		task.replace(map,key,oldValue,newValue);
		System.out.println("After replacing");
		printHashMapAndSize(map);
	}
	
	public void combineHashMap(Scanner sc)throws InvalidArgumentException{
		System.out.println("Enter contents of hash map1:");
		Map<String,String> map1 = stringHashMap(sc);
		System.out.println("Enter contents of hash map2:");
		Map<String,String> map2 = stringHashMap(sc);
		task.putAll(map1,map2);
		System.out.println("After combining map2 with map1:");
		printHashMapAndSize(map1);
		printHashMapAndSize(map2);
	}
	
	public void iterate(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = stringHashMap(sc);
		Set<Map.Entry<String,String>> entrySet = task.entrySet(map);
		for(Map.Entry<String,String> entry:entrySet){
			System.out.println(entry);
		}
	}
	
	public void clear(Scanner sc)throws InvalidArgumentException{
		Map<String,String> map = stringHashMap(sc);
		task.clear(map);
		printHashMapAndSize(map);
	}
	
	public static void main (String args[]){
		Scanner sc = new Scanner(System.in);
			
		HashMapRunner runner = new HashMapRunner();
		runner.printChoiceList();
		int choice = sc.nextInt();
		sc.nextLine();
		
		//if choice = 19 => exit
		if(choice == 19){
			return;
		}	
		do{
			try{
				switch(choice){
					case 1:
						runner.createPrintHashMap();
						break;
					case 2:
						runner.stringHashMap(sc);
						break;
					case 3:
						runner.integerHashMap(sc);
						break;
					case 4:
						runner.strKeyIntVal(sc);
						break;
					case 5:
						runner.strKeyCustomVal(sc);
						break;
					case 6:
						runner.nullMap(sc);
						break;
					case 7:
						runner.containsKey(sc);
						break;
					case 8:
						runner.containsValue(sc);
						break;
					case 9:
						runner.changeAllValues(sc);
						break;		
					case 10:
						runner.get(sc);
						break;
					case 11:
						runner.getOrDefault(sc);
						break;
					case 12:
						runner.remove(sc);
						break;
					case 13:
						runner.removeIfMatch(sc);
						break;
					case 14:
						runner.replace(sc);
						break;
					case 15:
						runner.replaceIfMatch(sc);
						break;
					case 16:
						runner.combineHashMap(sc);
						break;
					case 17:
						runner.iterate(sc);
						break;
					case 18:
						runner.clear(sc);
						break;
				}
			}
			catch(Exception e){
				System.out.println(e.getMessage());
			}
			runner.printChoiceList();
			choice = sc.nextInt();
			sc.nextLine();			
		}while(choice!=19);
	}
}
  
