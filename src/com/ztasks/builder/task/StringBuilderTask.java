package com.ztasks.builder.task;

import java.util.ArrayList;
import com.generalutils.GeneralUtils;
import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.exception.StringBuilderException;

public class StringBuilderTask{
	
	public StringBuilder getStringBuilder(){
		return new StringBuilder();
	}
	
	public StringBuilder appendWithDelimiter(StringBuilder strBuilder ,String delimiter, String str)
	throws InvalidArgumentException{	
		GeneralUtils.checkObjArgIsNull(strBuilder);
		GeneralUtils.checkObjArgIsNull(delimiter);
		GeneralUtils.checkObjArgIsNull(str);
		return strBuilder.append(str).append(delimiter);
	}
	
	
	public StringBuilder insertString (int insertAfter ,String delimiter, String insertText,StringBuilder strBuilder)
	throws InvalidArgumentException,StringBuilderException {
		try{
			GeneralUtils.checkObjArgIsNull(insertText);
//pls check		
		int insertAt = GeneralUtils.findStartIndex(insertAfter , delimiter , strBuilder,true);
			return strBuilder.insert(insertAt, insertText + delimiter);
		}
		catch(BoundaryCheckException e){
			throw new StringBuilderException("Index out of range in method insertString",e);
		}
	}
	
	public StringBuilder delete (int startIndex ,int endIndex,StringBuilder strBuilder)
	throws InvalidArgumentException,StringBuilderException{
		try{
			return GeneralUtils.deleteInStrBuilder(startIndex,endIndex,strBuilder);
		}
		catch(BoundaryCheckException e){
			throw new StringBuilderException("StartIndex range(inclusive): 0 to strBuilderLength-1\nEndIndex range(inclusive):startIndex to strBuilderLength",e);
		}
	}
	
	public StringBuilder delete(int deleteStrAt,String delimiter,StringBuilder strBuilder)
	throws InvalidArgumentException,StringBuilderException{
		try{
			int startIndex = GeneralUtils.findStartIndex(deleteStrAt,delimiter,strBuilder,false);
			int endIndex = GeneralUtils.findEndIndex(startIndex,delimiter,strBuilder);
			return delete(startIndex,endIndex,strBuilder);
		}
		catch(BoundaryCheckException e){
			throw new StringBuilderException("String to operate is out of range",e);
		}	
	}
	
	public StringBuilder replace (int startIndex ,int endIndex, String replaceWith,StringBuilder strBuilder)
	throws InvalidArgumentException,StringBuilderException{
		try{
			return GeneralUtils.replaceInStrBuilder(startIndex,endIndex,replaceWith,strBuilder);
		}
		catch(BoundaryCheckException e){
			throw new StringBuilderException("StartIndex range(inclusive): 0 to strBuilderLength-1\nEndIndex range(inclusive):startIndex to strBuilderLength",e);
		}
	}
	
	public StringBuilder replace(String toReplace , String replaceWith , StringBuilder strBuilder)
	throws InvalidArgumentException,StringBuilderException{
		int toReplaceLength = GeneralUtils.findLength(toReplace);
		int startIndex = GeneralUtils.findIndexInStrBuilder(toReplace,strBuilder,true);
		while ( startIndex != -1){
			int endIndex = startIndex + toReplaceLength;
			strBuilder = replace(startIndex,endIndex,replaceWith,strBuilder);
			startIndex = findIndex(toReplace,strBuilder,true);
		}
		return strBuilder;
	}
	
	public StringBuilder reverse (StringBuilder strBuilder)
	throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(strBuilder);
		return strBuilder.reverse();
	}
	
	public int findIndex ( String str,StringBuilder strBuilder, Boolean isFirst)
	throws InvalidArgumentException{
		return GeneralUtils.findIndexInStrBuilder(str,strBuilder,isFirst);
	}
}