package com.session.executorservice.test;

public class MainClass {
	 public static void main(String[] args) {
		Thread ex = new Thread(()->{
			System.out.println("run method");
		});
		ex.start();
	}
}
