package com.ztasks.inheritance.Car;

public class Car{
	private int yearOfMake;
	private String engineNumber;
	private String type;
	
	public Car(){
	}
	
	public Car (String msg){
		System.out.println("Message : "+msg);
	}
	
	public int getYearOfMake(){
		return yearOfMake;
	}
	
	public void setYearOfMake(int yearOfMake){
		this.yearOfMake = yearOfMake;
	}
	
	public String getEngineNumber(){
		return engineNumber;
	}
	
	public void setEngineNumber(String engineNumber){
		this.engineNumber = engineNumber;
	}
	
	public String getType(){
		return type;
	}
	
	public void setType(String type){
		this.type = type;
	}
	
	public void maintenance(){
		System.out.println("Car under maintenance");
	}
}