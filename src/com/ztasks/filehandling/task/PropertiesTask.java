package com.ztasks.filehandling.task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;

import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;

public class PropertiesTask {
	
	public void storeProperties(File file,Properties props)throws InvalidArgumentException,IOException{
		GeneralUtils.checkObjArgIsNull(file);
		GeneralUtils.checkObjArgIsNull(props);
		try(FileOutputStream out = new FileOutputStream(file)){
			props.store(out,"Stored properties");
		}
	}
	
	public Properties readProperties(File file)throws IOException,InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(file);
		Properties properties = new Properties();;
		try(FileInputStream fileIn = new FileInputStream(file)){
			properties.load(fileIn);
		}
		return properties;
	}
	
	public String getProperty(Properties property,String key)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(property);
		GeneralUtils.checkObjArgIsNull(key);
		return property.getProperty(key);
	}
	
}
