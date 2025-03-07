package com.ztasks.filehandling.task;

import com.generalutils.GeneralUtils;
import com.exception.InvalidArgumentException;

import java.io.File;
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class FileTask{
	
	public void writeFile(File file,List<String> contents)throws InvalidArgumentException,IOException{
		GeneralUtils.checkObjArgIsNull(file);
		GeneralUtils.checkObjArgIsNull(contents);
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
			for (String content : contents) {
				writer.write(content);
				writer.newLine();
			}
		}
	}
}