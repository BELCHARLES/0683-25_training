package com.generalutils;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.File;
import java.util.Properties;

public class PropertiesReader{
	
	public Properties loadProperties(File file)throws IOException{
		try(FileInputStream fileIn = new FileInputStream(file)){
			Properties properties = new Properties();
			properties.load(fileIn);
			return properties;
		}
	}
	
	public void printProperties(Properties properties){
		properties.list(System.out);
	}
}