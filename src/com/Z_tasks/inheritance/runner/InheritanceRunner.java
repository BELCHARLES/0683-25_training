package com.Z_tasks.inheritance.runner;

import com.Z_tasks.inheritance.Car.*;
import com.Z_tasks.inheritance.Birds.*;

import java.util.Scanner;

public class InheritanceRunner{

	private void printChoiceList(){
		System.out.println("\nSno.    Functions");
		System.out.println("1  Display classes used");
		System.out.println("2  Test derived class methods");
		System.out.println("3  Test inherited methods");
		System.out.println("4  Polymorphic test - passing derived objs to base class reference");
		System.out.println("5  Understand polymorphism - passing base obj & same level obj to derived reference");
		System.out.println("6  Explore behaviour of overridden method : maintenance()");
		System.out.println("7  Overloaded constructor");
		System.out.println("8  Abstract class and abstract methods");
		System.out.println("9  Abstract & non abstract methods");
		System.out.println("10 Exit");
		System.out.print("Please enter the corresponding serial number to perform the requied function:");
	}
	
	private Object[] getDerivedAttributes(Scanner sc) {
		Object[] attributes = new Object[4];	
		System.out.print("Enter number of airbags: ");
		attributes[0] = sc.nextInt();
		System.out.print("Enter number of seats: ");
		attributes[1] = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter model: ");
		attributes[2] = sc.nextLine();
		System.out.print("Enter color: ");
		attributes[3] = sc.nextLine(); 
		return attributes;
	}
	
	private Object[] getInheritedAttributes(Scanner sc) {
		Object[] attributes = new Object[4];	
		System.out.print("Enter year of make: ");
		attributes[0] = sc.nextInt();
		sc.nextLine();
		System.out.print("Enter engine number: ");
		attributes[1] = sc.nextLine();
		System.out.print("Enter type: ");
		attributes[2] = sc.nextLine(); 
		return attributes;
	}

	private void identifyObjectType(Car obj){
		//obj.getClass().getSimpleName()
		if(obj instanceof Swift){
			System.out.println("Hatch");
		}
		else if(obj instanceof XUV){
			System.out.println("SUV");
		}
		else{
			System.out.println("Sedan");
		}
	}
	
	private void acceptSwiftObj(Swift obj){
		System.out.println("Object instanceof Swift: "+(obj instanceof Swift));
	}
	
	public void displayStructure(){ 
		System.out.println("Classes used:");
		System.out.println("Example 1");
		System.out.println("Base class : Car");
		System.out.println("Subclasses : Swift,SCross,XUV");
		System.out.println("Example 2");
		System.out.println("Base class : BirdAbstract");
		System.out.println("Subclasses : ParrotMod");
		System.out.println("Example 3");
		System.out.println("Base class : Bird");
		System.out.println("Subclasses : Duck");
	}
	
	public void testDerivedClassMethods(Scanner sc){
		Swift obj = new Swift();
		System.out.println("Enter attributes of Swift");
		Object [] attributes = getDerivedAttributes(sc);
		obj.setAirBags((int)attributes[0]); 
		obj.setSeats((int)attributes[1]);
		obj.setModel((String)attributes[2]);
		obj.setColour((String)attributes[3]);
		System.out.println("No.of airbags: "+obj.getAirBags());
		System.out.println("No.of seats: "+obj.getSeats());
		System.out.println("Model : "+obj.getModel());
		System.out.println("Colour : "+obj.getColour());
	}
	
	public void testInheritedMethods(Scanner sc){
		SCross obj = new SCross();
		System.out.println("Enter attributes of SCross");
		Object attributes[] = getInheritedAttributes(sc);
		obj.setYearOfMake((int)attributes[0]);
		obj.setEngineNumber((String)attributes[1]);
		obj.setType((String)attributes[2]);
		
		attributes = getDerivedAttributes(sc);
		obj.setAirBags((int)attributes[0]); 
		obj.setSeats((int)attributes[1]);
		obj.setModel((String)attributes[2]);
		obj.setColour((String)attributes[3]);
		System.out.println("Year of Make: "+obj.getYearOfMake());
		System.out.println("Engine number: "+obj.getEngineNumber());
		System.out.println("Type: "+obj.getType());
		System.out.println("No.of airbags: "+obj.getAirBags());
		System.out.println("No.of seats: "+obj.getSeats());
		System.out.println("Model : "+obj.getModel());
		System.out.println("Colour : "+obj.getColour());
	}
	
	public void testPolymorphism(){
		System.out.println("Swift object - Car reference");
		Swift swift = new Swift();
		identifyObjectType(swift);
		System.out.println("XUV object - Car reference");
		XUV xuv = new XUV();
		identifyObjectType(xuv);
		System.out.println("SCross object - Car reference");
		SCross scross = new SCross();
		identifyObjectType(scross);
	}
	
	public void understandPolymorphism(){
		System.out.println("Passing swift obj to Swift Reference");
		Swift swift = new Swift();
		acceptSwiftObj(swift);
		System.out.println("Passing Car obj to Swift Reference");
		//Car obj = new Swift();
		//acceptSwiftObj(obj);
		System.out.println(" error: incompatible types: Car cannot be converted to Swift acceptSwiftObj(obj);");
		System.out.println("Passing Scross obj to Swift Reference");
		//SCross scross = new SCross();
		//acceptSwiftObj(scross);
		System.out.println(" error: incompatible types: SCross cannot be converted to Swift acceptSwiftObj(scross);");
		System.out.println("Passing XUV obj to Swift Reference");
		//XUV xuv = new XUV();
		//acceptSwiftObj(xuv);
		System.out.println("error: incompatible types: XUV cannot be converted to Swift acceptSwiftObj(xuv);");
	}
	
	public void executeMaintenanceScenarios(){
		System.out.println("SCross obj - SCross Reference");
		SCross scross = new SCross();
		scross.maintenance();
		System.out.println("SCross obj - Car Reference");
		Car car = new SCross();
		car.maintenance();
		System.out.println("Car obj - Car Reference");
		car = new Car();
		car.maintenance();
		System.out.println("Swift obj - Swift Reference");
		Swift swift = new Swift();
		swift.maintenance();
	}
	
	public void testOverloadedCarConstructor(){
		System.out.println("XUV default constructor");
		XUV xuv = new XUV();
		System.out.println("XUV overloaded constructor");
		//xuv = new XUV("Call from Runner");
		System.out.println(" error: constructor XUV in class XUV cannot be applied to given types;");
		System.out.println("xuv = new XUV(\"Call from Runner\");required: no arguments found: String");
		System.out.println("reason: actual and formal argument lists differ in length");
	}
	
	public void testAbstractClass(){
		System.out.println("Trying to create instance for abstract class: BirdAbstract");
		//BirdAbstract bird = new BirdAbstract();
		//bird.fly();
		//bird.speak();
		System.out.println(" error: BirdAbstract is abstract; cannot be instantiated BirdAbstract bird = new BirdAbstract();");
		System.out.println("Creating instance for ParrotMod which extends BirdAbstract");
		ParrotMod parrot = new ParrotMod();
		parrot.fly();
		parrot.speak();
	}
	
	public void testDuckClass(){
		System.out.println("Duck extends abstract class Bird");
		System.out.println("Duck providing implementation for fly method");
		Duck duck = new Duck();
		duck.speak();
		duck.fly();
	}
	
	public static void main(String args[]){
		Scanner sc = new Scanner (System.in);
		try{
			InheritanceRunner runner = new InheritanceRunner();
			runner.printChoiceList();
			int choice = sc.nextInt();
			sc.nextLine();
		
			//if choice = 10 => exit
			if(choice == 10){
				return;
			}
			
			do{
				try{
					switch(choice){
						case 1:
							runner.displayStructure();
							break;
						case 2:
							runner.testDerivedClassMethods(sc);
							break;
						case 3:
							runner.testInheritedMethods(sc);
							break;
						case 4:
							runner.testPolymorphism();
							break;
						case 5:
							runner.understandPolymorphism();
							break;
						case 6:
							runner.executeMaintenanceScenarios();
							break;
						case 7:
							runner.testOverloadedCarConstructor();
							break;
						case 8:
							runner.testAbstractClass();
							break;
						case 9:
							runner.testDuckClass();
							break;
					}
				}
				catch(Exception e){
					System.out.println(e.getMessage());
					System.out.println("Printing Stack Trace...");
					e.printStackTrace();
				}
				runner.printChoiceList();
				choice = sc.nextInt();
				sc.nextLine();
				
			}while(choice!=10);
		}
		finally{
			sc.close();
		}
	}
}