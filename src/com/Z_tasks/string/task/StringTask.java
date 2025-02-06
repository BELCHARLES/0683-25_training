package com.Z_tasks.string.task;

import com.exception.StringTaskException;
import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;

import java.util.ArrayList;

public class StringTask {
	public int findLength(String str) throws InvalidArgumentException {
		return GeneralUtils.findLength(str);
	}

	public char[] convertToCharArray(String str)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		return str.toCharArray();
	}

	public char charAtPos(String str, int pos) throws StringTaskException,InvalidArgumentException {
		try{
			GeneralUtils.boundaryCheck(pos, 1, findLength(str));
			return str.charAt(pos - 1);
		}
		catch(BoundaryCheckException e){
			throw new StringTaskException("Index out of range in method charAtPos",e);
		}
	}

	public int countOfChar(String str, char letter)throws InvalidArgumentException {
		int count = 0;
		int length = findLength(str);
		for (int i = 0; i < length; i++) {
			if (str.charAt(i) == letter) {
				count++;
			}
		}
		return count;
	}

	public int greatestPosOfChar(String str, char letter)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		return str.lastIndexOf(letter);
	}

	public String getLastNChar(String str, int n) throws StringTaskException,InvalidArgumentException {
		try{
			int length = findLength(str);
			GeneralUtils.boundaryCheck(n, 1, length);
			return str.substring(length - n);
		}
		catch(BoundaryCheckException e){
			throw new StringTaskException("Index out of range in method getLastNChar",e);
		}	
	}

	public String getFirstNChar(String str, int n) throws StringTaskException,InvalidArgumentException {
		try{
			GeneralUtils.boundaryCheck(n, 1, findLength(str));
			return str.substring(0, n);
		}
		catch(BoundaryCheckException e){
			throw new StringTaskException("Index out of range in method getFirstNChar",e);
		}
		
	}

	public String replaceFirstNChar(String str, int count, String repStr) throws StringTaskException,InvalidArgumentException {
		try{
			GeneralUtils.checkObjArgIsNull(repStr);
			GeneralUtils.boundaryCheck(count, 1, findLength(str));
			return repStr.concat(str.substring(count));
		}
		catch(BoundaryCheckException e){
			throw new StringTaskException("Index out of range in method replaceFirstNChar",e);
		}
	}

	public boolean checkStartsWith(String str, String starter)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		GeneralUtils.checkObjArgIsNull(starter);
		return str.startsWith(starter);
	}

	public boolean checkEndsWith(String str, String ender) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(str);
		GeneralUtils.checkObjArgIsNull(ender);
		return str.endsWith(ender);
	}

	public String changeCase(String str,boolean toUpper)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		if(toUpper){
			return str.toUpperCase();
		}
		return str.toLowerCase();
	}

	public String reverseString(String str) throws InvalidArgumentException{
		char[] charArr = convertToCharArray(str);
		int left = 0;
		int right = findLength(str) - 1;
		while (left < right) {
			char temp = charArr[left];
			charArr[left] = charArr[right];
			charArr[right] = temp;
			left++;
			right--;
		}
		return new String(charArr);
	}

	public String concWithoutGivenChar(String str, String remover,String replacer)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		GeneralUtils.checkObjArgIsNull(remover);
		GeneralUtils.checkObjArgIsNull(replacer);
		return str.replace(remover, replacer);
	}

	public String[] split(String str, String delimeter)throws InvalidArgumentException {
		return GeneralUtils.split(str,delimeter);
	}

	public String mergeStrWithChar(ArrayList<String> strArr, String merger)throws InvalidArgumentException {
		return GeneralUtils.join(merger,strArr);
	}

	public boolean equalsWithCaseOption(String str1, String str2,boolean isCaseSensitive)throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str1);
		GeneralUtils.checkObjArgIsNull(str2);
		if (isCaseSensitive){
			return str1.equals(str2);
		}
		return str1.equalsIgnoreCase(str2);
	}

	public String trimExtraSpace(String str) throws InvalidArgumentException{
		GeneralUtils.checkObjArgIsNull(str);
		return str.trim();
	}

}