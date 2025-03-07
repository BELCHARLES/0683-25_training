package com.ztasks.filehandling.task.singleton.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

class CAR {
	String model;
	String mode;
	
//	public CAR(String model) {}
}

class Car extends CAR implements Serializable {
//    private static final long serialVersionUID = 1L; 

    String name;
    int yearOfMake;
    String colour;
    float amt;

    public Car(String name, int yearOfMake,String model,String mode) {
//    	super(model);
    	this.mode = mode;
    	this.model = model;
        this.name = name;
        this.yearOfMake = yearOfMake;
        this.colour = colour;
    }

    @Override
    public String toString() {
        return "Car{name='" + name + "', yearOfMake=" + yearOfMake + ", colour='" +colour+ " model = "+model+" mode: "+mode+" amt: "+amt+" }";
//        return "Car{name='" + name + "', yearOfMake=" + yearOfMake + " model = "+model+" mode: "+mode+" }";

    }
}

public class CarTest {
    public static void main(String[] args) throws IOException, ClassNotFoundException {
    	Car deserializedCar;
    
//        Car car = new Car("Tesla", 2023, "Red","hai");
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("car.ser"))) {
//            oos.writeObject(car);
//            oos.writeUTF(car.model);
//            oos.writeUTF(car.mode);
//            System.out.println("Car object serialized successfully!");
//        }

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("car.ser"))) {
            deserializedCar = (Car) ois.readObject();
            deserializedCar.model = ois.readUTF();
            deserializedCar.mode = ois.readUTF();
        } 


        if (deserializedCar != null) {
            System.out.println("Deserialized Car: " + deserializedCar);
        }
    }
}
