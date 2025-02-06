package com.generalutils;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.File;
import java.util.Properties;
import java.util.Map;

import com.generalutils.GeneralUtils;
import com.exception.InvalidArgumentException;

public class PropertiesWriter{
	
	public Properties loadProperties(Map<String,String> props)throws InvalidArgumentException{
		Properties properties = new Properties();
		for(String key : props.keySet()){
			properties.setProperty(key,GeneralUtils.get(props,key));
		}
		return properties;
	}
	
	public void storeProperties(File file,Properties properties)throws IOException{
		try(FileOutputStream out = new FileOutputStream(file)){
			properties.store(out,"Stored properties");
		}
	}
}