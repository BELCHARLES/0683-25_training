package com.Z_tasks.arraylist.task; 

import java.util.List;
import java.util.Properties;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
//import java.util.ArrayList;	
import java.util.Iterator;

import com.generalutils.GeneralUtils;
import com.exception.InvalidArgumentException;
import com.exception.BoundaryCheckException;
import com.Z_tasks.filehandling.task.PropertiesTask;

public class ArrayListTask{
	
	private String loadClass() throws InvalidArgumentException, IOException {
		PropertiesTask propsTask = new PropertiesTask();
		File file = GeneralUtils.createDirFile(System.getProperty("user.dir"), "myprops.txt");
		Properties properties = propsTask.readProperties(file);
		return propsTask.getProperty(properties, "loadclass");
	}
	
	public <T> List<T> createList() throws  InvalidArgumentException{
		try {
			String loaderClass = loadClass();
			Class<?> reflectionClass = Class.forName(loaderClass);
			Constructor<?> defaultConstructor = reflectionClass.getConstructor();
			return (List<T>) defaultConstructor.newInstance();
		}
		catch(Exception e) {
			throw new InvalidArgumentException("Many exceptions here!!");
		}
	}
	
	public <T> List <T> addElements(List<T> list , T[] elements)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list);
		GeneralUtils.checkObjArgIsNull(elements);
		for(int i = 0;i<elements.length;i++){
			list.add(elements[i]);
		}
		return list;
	}
	
	public <T> int indexOf(List<T> list,T element)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list);
		GeneralUtils.checkObjArgIsNull(element);
		return list.indexOf(element);
	}
	
	public <T> int lastIndexOf(List<T> list,T element)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list);
		GeneralUtils.checkObjArgIsNull(element);
		return list.lastIndexOf(element);
	}
	
	public <T> Iterator<T> createIterator(List<T> list)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list);
		return list.iterator();
	}
	
	public <T> T get (List<T> list,int index)throws BoundaryCheckException,InvalidArgumentException{
		GeneralUtils.boundaryCheck(index, 0, GeneralUtils.findLength(list)-1);
		return list.get(index);
	}
	
	public <T> List<T> add (List<T> list,T element,int index)
	throws BoundaryCheckException,InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(element);
		GeneralUtils.boundaryCheck(index, 0, GeneralUtils.findLength(list)-1);
		list.add(index,element);
		return list;
	}
	
	public <T> List<T> newListFromExistingList(List<T> list,int from,int to)
	throws BoundaryCheckException,InvalidArgumentException{
		int length = GeneralUtils.findLength(list);
		GeneralUtils.boundaryCheck(from, 0, length-1);
		GeneralUtils.boundaryCheck(to, from, length);
		return list.subList(from,to);
	}
	
	public <T> List<T> combineLists(List <T> list1,List<T> list2)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list1);
		GeneralUtils.checkObjArgIsNull(list2);
		list1.addAll(list2);
		return list1;
	}
	
	public <T> List<T> remove (List<T> list,int index)throws InvalidArgumentException,BoundaryCheckException{
		GeneralUtils.boundaryCheck(index, 0, GeneralUtils.findLength(list)-1);
		list.remove(index);
		return list;
	}
	
	public <T> List<T> removeAll (List<T> list1,List<T> list2)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list1);
		GeneralUtils.checkObjArgIsNull(list2);
		list1.removeAll(list2);
		return list1;
	}
	
	public <T> List<T> retainAll (List<T> list1,List<T> list2)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list1);
		GeneralUtils.checkObjArgIsNull(list2);
		list1.retainAll(list2);
		return list1;
	}
	
	public <T> List<T> clear(List<T> list)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list);
		list.clear();
		return list;
	}
	
	public <T> boolean contains(List<T> list,T element)throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(list);
		GeneralUtils.checkObjArgIsNull(element);
		return list.contains(element);
	}
}
	