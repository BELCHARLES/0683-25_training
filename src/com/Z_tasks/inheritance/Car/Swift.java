package com.Z_tasks.inheritance.Car;

import com.Z_tasks.inheritance.Car.Car;

public class Swift extends Car{
	private int seats;
	private int airBags;
	private String model;
	private String colour;
	
	public int getSeats (){
		return seats;
	}
	
	public void setSeats (int seats){
		this.seats = seats; 
	}
	
	public int getAirBags (){
		return airBags;
	}
	
	public void setAirBags (int airBags){
		this.airBags = airBags; 
	}
	
	public String getModel (){
		return model;
	}
	
	public void setModel (String model){
		this.model = model;
	}
	
	public String getColour (){
		return colour;
	}
	
	public void setColour (String colour){
		this.colour = colour; 
	}
}