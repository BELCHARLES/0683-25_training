package com.Z_tasks.filehandling.task.singleton.test;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInput;
import java.io.ObjectInputStream;
import java.io.ObjectOutput;
import java.io.ObjectOutputStream;

import com.Z_tasks.filehandling.task.singleton.BillPughSingleton;
import com.Z_tasks.filehandling.task.singleton.EnumSingleton;

public class SerializedSingletonTest {
	
	public static void main(String args[]) throws IOException, ClassNotFoundException {
		EnumSingleton instance1 = EnumSingleton.INSTANCE;
		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
        out.writeObject(instance1);
        out.close();
        ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
        EnumSingleton instance2 = (EnumSingleton) in.readObject();
        in.close();

        System.out.println("instanceOne hashCode="+instance1.hashCode());
        System.out.println("instanceTwo hashCode="+instance2.hashCode());
		
//		BillPughSingleton instance1 = BillPughSingleton.getInstance();
//		ObjectOutput out = new ObjectOutputStream(new FileOutputStream("filename.ser"));
//        out.writeObject(instance1);
//        out.close();
//        ObjectInput in = new ObjectInputStream(new FileInputStream("filename.ser"));
//        BillPughSingleton instance2 = (BillPughSingleton) in.readObject();
//        in.close();
//
//        System.out.println("instanceOne hashCode="+instance1.hashCode());
//        System.out.println("instanceTwo hashCode="+instance2.hashCode());

	}
	
}
