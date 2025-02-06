package com.Z_tasks.filehandling.runner;

import com.Z_tasks.filehandling.task.FileTask;
import com.Z_tasks.filehandling.task.PropertiesTask;
import com.Z_tasks.filehandling.task.Rainbow;
import com.Z_tasks.filehandling.task.singleton.*;
import com.Z_tasks.filehandling.task.TimeTask;
import com.generalutils.CustomClass;
import com.generalutils.GeneralUtils;
import com.exception.InvalidArgumentException;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.Properties;
import java.util.logging.FileHandler;
import java.util.logging.Filter;
import java.util.logging.Handler;
import java.util.logging.Level;
import java.util.logging.LogRecord;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.time.Duration;
import java.time.Instant;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.zone.ZoneRules;

public class FileRunner{
	
	private static Logger logger = Logger.getLogger("com.runner.FilesRunner");
	
	

	//helper methods
	
	private void printChoiceList(){
		System.out.println("\nSno.    Functions");
		System.out.println("1 Write to file");
		System.out.println("2 Store properties");
		System.out.println("3 Read Properties");
		System.out.println("4 Create directory and write to file");
		System.out.println("5 POJO class - toString ");
		System.out.println("6 POJO Encapsulation");
		System.out.println("7 Simulate reflection");
		System.out.println("8 Rainbow enum ");
		System.out.println("9 Test Singleton Class");
		System.out.println("10 Date package");
		System.out.println("12 Exit");
		System.out.println("Please enter the corresponding serial number to perform the requied function:");
	}
	
	public void writeFile(File file,Scanner sc)throws InvalidArgumentException,IOException{
		FileTask task = new FileTask();
		ArrayList<String> contents = new ArrayList<>();
		char choice ='y';
		while(choice=='y'){
			System.out.println("Enter line: ");
			contents.add(sc.nextLine());
			System.out.println("Do you want to add more lines (y/n): ");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		task.writeFile(file,contents);
		logger.info("Files written successfully");
	}
	
	public void storeProps(File file,Scanner sc)throws InvalidArgumentException,IOException{
		PropertiesTask propsTask = new PropertiesTask();
		Properties properties = new Properties();
		char choice ='y';
		String key,value;
		while(choice == 'y'){
			System.out.println("Enter property key: ");
			key = sc.nextLine();
			System.out.println("Enter property value: ");
			value = sc.nextLine();
			properties.setProperty(key, value);
			System.out.println("Do you want to add more properties (y/n): ");
			choice = sc.next().charAt(0);
			sc.nextLine();
		}
		propsTask.storeProperties(file,properties);
		logger.info("Properties stored to file successfully");
	}
	
	public void readProperties(File file,Scanner sc)throws InvalidArgumentException,IOException{
		PropertiesTask propsTask = new PropertiesTask();
		Properties properties = propsTask.readProperties(file);
		logger.log(Level.INFO, "{0}",properties);
	}
	
	public void writeToDirectory(Scanner sc)throws InvalidArgumentException,IOException{
		System.out.println("Enter the  directory path: ");
		String dirPath = sc.nextLine();
		System.out.println("Enter file name :");
		String fileName = sc.nextLine();
		File file = GeneralUtils.createDirFile(dirPath,fileName);
		writeFile(file,sc);
		System.out.println("Enter the fileName to write the properties: ");
		file = GeneralUtils.createDirFile(dirPath,sc.nextLine());
		storeProps(file,sc);
		readProperties(file,sc);
	}
	
	public void pojoWks (Scanner sc)throws InvalidArgumentException{
		System.out.println("Enter the name:");
		String name = sc.nextLine();
		System.out.println("Enter the age:");
		int age = sc.nextInt();
		sc.nextLine();
		CustomClass obj = new CustomClass(name,age);
		logger.log(Level.INFO, "{0}",obj);
	}
	
	public void pojoEncapsulation(Scanner sc)throws InvalidArgumentException{
		System.out.println("Enter the name:");
		String name = sc.nextLine();
		System.out.println("Enter the age:");
		int age = sc.nextInt();
		sc.nextLine();
		CustomClass obj = new CustomClass();
		obj.setName(name);
		obj.setAge(age);
		logger.info("Name: "+obj.getName());
		logger.info("Age: "+obj.getAge());
	}
	
	public void simulateReflection(Scanner sc)
	throws ClassNotFoundException,NoSuchMethodException,InstantiationException,IllegalAccessException,
	InvocationTargetException{
		Class<?> reflectionClass = Class.forName("com.generalutils.ReflectionClass");
		Constructor<?> defaultConstructor = reflectionClass.getConstructor();
		Object reflectionDefault = defaultConstructor.newInstance();
		logger.info("Invoked default constructor: \n"+reflectionDefault);
		
		Constructor<?> overloadedConstructor = reflectionClass.getConstructor(String.class,int.class);
		System.out.println("Enter the name: ");
		String name = sc.nextLine();
		System.out.println("Enter age: ");
		int age = sc.nextInt();
		sc.nextLine();
		Object reflectionOverloaded = overloadedConstructor.newInstance(name,age);
		logger.info("Invoked overloaded constructor: \n"+reflectionOverloaded);
		
		Method getMethod = reflectionClass.getMethod("getName");
		Object getObj = getMethod.invoke(reflectionOverloaded);
		logger.info("get ans: "+getObj);

		System.out.println("Enter the name: ");
		name = sc.nextLine();
		Method setMethod = reflectionClass.getMethod("setName",String.class);
		setMethod.invoke(reflectionOverloaded,name);
		logger.log(Level.INFO, "{0}",reflectionOverloaded);
	}
	
	public void testRainbowEnum(Scanner sc){
		for(Rainbow colour : Rainbow.values()){
			logger.info("Colour code of "+colour+" is "+colour.getColourCode()+" Ordinal: "+colour.ordinal());
		}
	}
	
	public void testSingletonClass(){
		
		EarlyInitializedSingleton instance1 = EarlyInitializedSingleton.getInstance();
		EarlyInitializedSingleton instance2 = EarlyInitializedSingleton.getInstance();
		logger.info("Early initialized instance 1 hashcode: "+instance1.hashCode());
		logger.info("Early initialized instance 2 hashcode: "+instance2.hashCode());
		
		StaticBlockSingleton staticInstance1 = StaticBlockSingleton .getInstance();
		StaticBlockSingleton  staticInstance2 = StaticBlockSingleton .getInstance();
		logger.info("Static block initialized instance 1 hashcode: "+staticInstance1.hashCode());
		logger.info("Static block initialized instance 2 hashcode: "+staticInstance2.hashCode());
		
		LazyInitializedSingleton lazyInstance1 = LazyInitializedSingleton.getInstance();
		LazyInitializedSingleton lazyInstance2 = LazyInitializedSingleton .getInstance();
		logger.info("Lazy initialized instance 1 hashcode: "+lazyInstance1.hashCode());
		logger.info("Lazy initialized instance 2 hashcode: "+lazyInstance2.hashCode());
		
		ThreadSafeSingleton threadInstance1 = ThreadSafeSingleton.getInstance();
		ThreadSafeSingleton threadInstance2 = ThreadSafeSingleton.getInstance();
		logger.info("Thread-safe instance 1 hashcode: "+threadInstance1.hashCode());
		logger.info("Thread-safe instance 2 hashcode: "+threadInstance2.hashCode());
		long startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            ThreadSafeSingleton threadSafeInstance = ThreadSafeSingleton.getInstance();
        }
        long endTime = System.nanoTime();
        logger.info("Thread-Safe Singleton execution time: " + (endTime - startTime) + " ns");

		
		DoubleCheckedSingleton doubleCheckedInstance1 = DoubleCheckedSingleton.getInstance();
		DoubleCheckedSingleton doubleCheckedInstance2 = DoubleCheckedSingleton.getInstance();
		logger.info("Double checked instance 1 hashcode: "+doubleCheckedInstance1.hashCode());
		logger.info("Double checked instance 2 hashcode: "+doubleCheckedInstance2.hashCode());
		startTime = System.nanoTime();
        for (int i = 0; i < 100000; i++) {
            DoubleCheckedSingleton doubleCheckedInstance = DoubleCheckedSingleton.getInstance();
        }
        endTime = System.nanoTime();
        logger.info("Double-Checked Locking Singleton execution time: " + (endTime - startTime) + " ns");
		
		
		BillPughSingleton billInstance1= BillPughSingleton.getInstance();
		BillPughSingleton billInstance2= BillPughSingleton.getInstance();
		logger.info("Bill Pugh instance 1 hashcode: "+billInstance1.hashCode());
		logger.info("Bill Pugh instance 2 hashcode: "+billInstance2.hashCode());
//        BillPughSingleton instance3 = (BillPughSingleton)instance2.clone();
		
		
		EnumSingleton enumInstance1 = EnumSingleton.INSTANCE;
		EnumSingleton enumInstance2 = EnumSingleton.INSTANCE;
		logger.info("Enum Singleton instance 1 hashcode: "+enumInstance1.hashCode());
		logger.info("Enum Singleton instance 2 hashcode: "+enumInstance2.hashCode());
	}
	
	public void testTimePackage(Scanner sc)throws InvalidArgumentException{
		TimeTask timeTask = new TimeTask();
		currentTimeDate(timeTask);
		currentTimeMilliSeconds(timeTask);
		currentZonedDateTime(timeTask,sc);
		dayForCurrentTime(timeTask,sc);
		monthForCurrentTime(timeTask,sc);
		yearForCurrentTime(timeTask,sc);
	}
	
	private void currentTimeDate(TimeTask timeTask) {
		logger.info("Current Time with Date: ");
		logger.info("Using Instant\n"+timeTask.getInstant());
		
//		System.out.println("Using LocalDateTime:");
//        System.out.println(timeTask.getLocalDateTime());
		
		logger.info("Using Zone Date Time:");
		logger.log(Level.INFO, "{0}",timeTask.getZonedInstant());
		
		logger.info("Using OffsetDateTime:");
		logger.log(Level.INFO, "{0}",timeTask.getOffsetInstant());
	}
	
	private void currentTimeMilliSeconds(TimeTask timeTask) {
		logger.info("\n\nCurrent time in milliseconds");
		//not monotonic
		logger.info("System.currentTimeMillis : "+timeTask.getCurrentMillisUsingSystem());
		logger.info("Using Instant.now.EpochMilli : "+timeTask.getCurrentMillisUsingInstant());
		//monotonic
		logger.info("Using System.nanoTime: "+timeTask.getElapsedMillisUsingSysNano());
	}
	
	private void currentZonedDateTime(TimeTask timeTask,Scanner sc) throws InvalidArgumentException{
		String zone1 = getPreferredZone(timeTask,sc);
		ZonedDateTime zone1DateTime = timeTask.getZonedDateTime(timeTask.toZoneId(zone1));
		String zone2 = getPreferredZone(timeTask,sc);
		ZonedDateTime zone2DateTime = timeTask.getZonedDateTime(timeTask.toZoneId(zone2));
		
		logger.info("Time in "+zone1+" is "+zone1DateTime);
		logger.info("Time in "+zone2+" is "+zone2DateTime);
		
		Duration duration = timeTask.getDurationBetween(zone1DateTime,zone2DateTime);
		logger.info("Time difference between "+zone1+" and "+zone2+" is "+duration);
		logger.info("Hours: "+timeTask.getHours(duration)+" Minutes: "+timeTask.getMinutes(duration)+" Seconds: "+timeTask.getSeconds(duration));
		
//		Period period = timeTask.getPeriodBetween(timeTask.toLocalDate(zone1DateTime),timeTask.toLocalDate(zone2DateTime));
//		System.out.println("Date difference between "+zone1+" and "+zone2+" is "+period);
	}
	
	//helper	
	private String getPreferredZone(TimeTask timeTask,Scanner sc) {
		ArrayList<String> zones = new ArrayList<>(timeTask.getAvailableZoneIds());
		int count = 1;
		for(String zone: zones) {
			System.out.println(count++ +" "+zone);
		}
		System.out.println("Enter the number corresponding to desired time zone: ");
		int choice = sc.nextInt();
		sc.nextLine();	
		return zones.get(--choice);
	}
	
	//helper
	private long getMillisFromUser(TimeTask timeTask,Scanner sc) {
		System.out.println("1.Enter custom milliseconds\n2.Use current system time");
		System.out.println("Choose option: ");
		int option = sc.nextInt();
		sc.nextLine();
		long millis;
		if(option == 1) {
			System.out.print("Enter the milliseconds: ");
			millis = sc.nextLong();
		}
		else {
			millis = timeTask.getCurrentMillisUsingInstant();
		}
		return millis;
	}
	
	public void dayForCurrentTime(TimeTask timeTask,Scanner sc) throws InvalidArgumentException{
		long millis = getMillisFromUser(timeTask,sc);
		String zone = getPreferredZone(timeTask,sc);
		logger.info("The corressponding day for "+millis+ " is "+timeTask.getDayForCurrentTime(millis,zone));
	}
	
	public void monthForCurrentTime(TimeTask timeTask,Scanner sc)throws InvalidArgumentException {
		long millis = getMillisFromUser(timeTask,sc);
		String zone = getPreferredZone(timeTask,sc);
		logger.info("The corressponding month for "+millis+ " is "+timeTask.getMonthForCurrentTime(millis,zone));
	}
	
	public void yearForCurrentTime(TimeTask timeTask,Scanner sc) throws InvalidArgumentException{
		long millis = getMillisFromUser(timeTask,sc);
		String zone = getPreferredZone(timeTask,sc);
		logger.info("The corressponding year for "+millis+ " is "+timeTask.getYearForCurrentTime(millis,zone));
	}
	
	public void checkDST(Scanner sc) throws InvalidArgumentException {
		TimeTask task = new TimeTask();
		String zone = getPreferredZone(task,sc);
		ArrayList<Integer> date= getDate(sc);
		ZonedDateTime dateTime1 = ZonedDateTime.of(date.get(0),date.get(1),date.get(2),0,0,0,0,task.toZoneId(zone));
		date= getDate(sc);
		ZonedDateTime dateTime2 = ZonedDateTime.of(date.get(0),date.get(1),date.get(2),0,0,0,0,task.toZoneId(zone));
		logger.log(Level.INFO, "{0}",dateTime1);
		logger.log(Level.INFO, "{0}",dateTime2);
	}
	
	private ArrayList<Integer> getDate(Scanner sc){
		System.out.println("Enter the date:");
		ArrayList<Integer> date = new ArrayList<>();
		System.out.print("Enter the year: ");
		date.add(sc.nextInt());
		sc.nextLine();
		System.out.print("Enter the month: ");
		date.add(sc.nextInt());
		sc.nextLine();
		System.out.print("Enter the day: ");
		date.add(sc.nextInt());
		sc.nextLine();
		return date;
	}
	
	public static void main (String args[]) throws SecurityException, IOException  {
		Scanner sc = new Scanner(System.in);
		
		Logger rootLogger = Logger.getLogger("");
        Handler[] handlers = rootLogger.getHandlers();
        for (Handler handler : handlers) {
            if (handler instanceof java.util.logging.ConsoleHandler) {
                rootLogger.removeHandler(handler);
            }
        }

		FileHandler infoHandler,severeHandler;
		infoHandler = new FileHandler("info_log%u.log");
		infoHandler.setFormatter(new SimpleFormatter());
		infoHandler.setFilter(record -> record.getLevel() == Level.INFO);
	    logger.addHandler(infoHandler);
	        
	    severeHandler = new FileHandler("severe_log%u.log");
	    severeHandler.setFormatter(new SimpleFormatter());
		severeHandler.setLevel(Level.SEVERE);
	    logger.addHandler(severeHandler);
			

		
        
        
		FileRunner runner = new FileRunner();
//		FileRunner run = (FileRunner)runner.clone();
		FileTask task = new FileTask();
		runner.printChoiceList();
		int choice = sc.nextInt();
		sc.nextLine();
		
		//if choice = 11 => exit
		if(choice == 12){
			return;
		}	
		do{
			try{
				switch(choice){
					case 1:
						System.out.println("Enter the file name: ");
						runner.writeFile(GeneralUtils.createDirFile(System.getProperty("user.dir"),sc.nextLine()),sc);
						break;
					case 2:
						System.out.println("Enter the file name: ");
						runner.storeProps(GeneralUtils.createDirFile(System.getProperty("user.dir"),sc.nextLine()),sc);
						break;
					case 3:
						System.out.println("Enter the file name: ");
						runner.readProperties(GeneralUtils.createDirFile(System.getProperty("user.dir"),sc.nextLine()),sc);
						break;
					case 4:
						runner.writeToDirectory(sc);
						break;
					case 5:
						runner.pojoWks(sc);
						break;
					case 6:
						runner.pojoEncapsulation(sc);
						break;
					case 7:
						runner.simulateReflection(sc);
						break;
					case 8:
						runner.testRainbowEnum(sc);
						break;
					case 9:
						runner.testSingletonClass();
						break;
					case 10:
						runner.testTimePackage(sc);
						break;
					case 11:
						runner.checkDST(sc);
						break;
				}
			}
			catch(Exception e){
				logger.severe(e.getMessage());
				logger.log(Level.SEVERE,"Exception occurred",e);
			}
			runner.printChoiceList();
			choice = sc.nextInt();
			sc.nextLine();			
		}while(choice!=12);
	}
}
