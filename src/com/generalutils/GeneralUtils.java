package com.generalutils;

import java.util.Collection;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.HashMap;
import java.util.LinkedHashMap;

import com.exception.BoundaryCheckException;
import com.exception.InvalidArgumentException;
import com.exception.ValidationException;

public class GeneralUtils {

	private static final Map<String, Pattern> PATTERN_CACHE = new ConcurrentHashMap<>();

	public static final String NAMEPATTERN = "^[a-zA-Z\\s]{1,20}$";
	public static final String MAILPATTERN = "^\\S+@\\S+\\.\\S+$";
	public static final String PHONEPATTERN = "^\\d{10}$";

	public static void checkObjArgIsNull(Object obj) throws InvalidArgumentException {
		if (obj == null) {
			throw new InvalidArgumentException("Argument cannot be null,pass a valid argument");
		}

	}

	public static void boundaryCheck(int n, int min, int max) throws BoundaryCheckException {
		if (n < min || n > max) {
			throw new BoundaryCheckException(
					"Integer value should be between " + min + " and " + max + " (inclusive) for this operation.");
		}
	}

	public static int findLength(Object obj) throws InvalidArgumentException {
		checkObjArgIsNull(obj);
		if (obj instanceof CharSequence) {
			return ((CharSequence) obj).length();
		} else if (obj instanceof Collection) {
			return ((Collection<?>) obj).size();
		} else if (obj instanceof Map) {
			return ((Map<?, ?>) obj).size();
		} else {
			throw new InvalidArgumentException("Unsupported object type: " + obj.getClass());
		}
	}

	public static <K, V> void checkSameLength(K[] arr1, V[] arr2) throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(arr1);
		GeneralUtils.checkObjArgIsNull(arr2);
		if (arr1.length != arr2.length) {
			throw new InvalidArgumentException("Given arrays of not same length");
		}
	}

	public static void checkPositive(int num) throws InvalidArgumentException {
		if (num < 0) {
			throw new InvalidArgumentException("Given value " + num + " should be positive ");
		}
	}

	public static <K, V> Map<K, V> createMap() {
		return new HashMap<>();
	}

	public static <K, V> Map<K, V> createLinkedMap() {
		return new LinkedHashMap<>();
	}

	public static <K, V> V get(Map<K, V> map, K key) throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(map);
		return map.get(key);
	}

	public static <K, V> void put(Map<K, V> map, K key, V value) throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(map);
		map.put(key, value);
	}

	public static String[] split(String str, String delimeter) throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		GeneralUtils.checkObjArgIsNull(delimeter);
		return str.split(delimeter);
	}

	public static String join(String merger, ArrayList<String> strArr) throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(strArr);
		GeneralUtils.checkObjArgIsNull(merger);
		return String.join(merger, strArr);
	}

	public static int findStartIndex(int operationAt, String delimiter, StringBuilder strBuilder, Boolean isInsert)
			throws InvalidArgumentException, BoundaryCheckException {
		GeneralUtils.checkObjArgIsNull(delimiter);
		if (operationAt < 1) {
			throw new BoundaryCheckException("The string to operate cannot be negative");
		}
		int count = 0;
		int delIndex = 0;
		char delimiterChar = delimiter.charAt(0);
		int length = GeneralUtils.findLength(strBuilder);
		for (int i = 0; i < length; i++) {
			if (strBuilder.charAt(i) == delimiterChar) {
				count++;
				if (count == operationAt) {
					if (isInsert) {
						return i + 1;
					}
					return delIndex;
				}
				delIndex = i + 1;
			}
		}
		if (!isInsert && count < operationAt) {
			throw new BoundaryCheckException("The position to operate exceeds the number of strings	");
		}
		return length;
	}

	public static int findEndIndex(int startIndex, String delimiter, StringBuilder strBuilder)
			throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(strBuilder);
		GeneralUtils.checkObjArgIsNull(delimiter);
		int eofDelimiter = strBuilder.indexOf(delimiter, startIndex) + GeneralUtils.findLength(delimiter);
		int endIndex = startIndex + (eofDelimiter - startIndex);
		return endIndex;
	}

	public static int findIndexInStrBuilder(String str, StringBuilder strBuilder, Boolean isFirst)
			throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(str);
		GeneralUtils.checkObjArgIsNull(strBuilder);
		if (isFirst) {
			return strBuilder.indexOf(str);
		}
		return strBuilder.lastIndexOf(str);
	}

	public static StringBuilder deleteInStrBuilder(int startIndex, int endIndex, StringBuilder strBuilder)
			throws InvalidArgumentException, BoundaryCheckException {
		int strBuilderLength = GeneralUtils.findLength(strBuilder);
		GeneralUtils.boundaryCheck(startIndex, 0, strBuilderLength - 1);
		GeneralUtils.boundaryCheck(endIndex, startIndex + 1, strBuilderLength);
		return strBuilder.delete(startIndex, endIndex);
	}

	public static StringBuilder replaceInStrBuilder(int startIndex, int endIndex, String replaceWith,
			StringBuilder strBuilder) throws InvalidArgumentException, BoundaryCheckException {
		GeneralUtils.checkObjArgIsNull(replaceWith);
		int strBuilderLength = GeneralUtils.findLength(strBuilder);
		GeneralUtils.boundaryCheck(startIndex, 0, strBuilderLength - 1);
		GeneralUtils.boundaryCheck(endIndex, startIndex + 1, strBuilderLength);
		return strBuilder.replace(startIndex, endIndex, replaceWith);
	}

	public static File createDirFile(String dirPath, String fileName) throws InvalidArgumentException, IOException {
		GeneralUtils.checkObjArgIsNull(dirPath);
		File directory = new File(dirPath);
		directory.mkdirs();

		File file = new File(directory, fileName);
		file.createNewFile();
		return file;
	}

	public static Pattern getPattern(String regex) throws InvalidArgumentException {
		checkObjArgIsNull(regex);
		return PATTERN_CACHE.computeIfAbsent(regex, Pattern::compile);
	}

	// for storing compiled regex
	public static boolean validatePattern(String regex, String input) throws InvalidArgumentException {
		checkObjArgIsNull(input);
		Pattern pattern = getPattern(regex);
		Matcher matcher = pattern.matcher(input);
		return matcher.matches();
	}

	// regex compilation storage not required
	public static boolean matchPattern(String regex, String input) throws InvalidArgumentException {
		checkObjArgIsNull(input);
		checkObjArgIsNull(regex);
		return Pattern.matches(regex, input);
	}

	public static String quote(String input) throws InvalidArgumentException {
		checkObjArgIsNull(input);
		return Pattern.quote(input);
	}

	
	//jdbc stuff
	public static void isNull(Object obj) throws ValidationException {
		if (obj == null) {
			throw new ValidationException("E001");
		}

	}

	public static void isEmpty(String str) throws ValidationException {
		isNull(str);
		if (str.trim().isEmpty()) {
			throw new ValidationException("E001");
		}
	}

	public static void checkPattern(String pattern, String str) throws ValidationException {
		isEmpty(pattern);
		isEmpty(str);
		if (!Pattern.matches(pattern, str)) {
			throw new ValidationException("E002");
		}
	}

	public static void validateTextField(String input,String fieldName) throws ValidationException {
		checkPattern(NAMEPATTERN, input);
	}
	
	public static void validateMobile(String mobile) throws ValidationException {
		checkPattern(PHONEPATTERN, mobile);
	}
	
	public static void validateEmail(String email) throws ValidationException {
		checkPattern(MAILPATTERN, email);
	}
	
	

	/*
	 * private static ValidationException customizedException(ValidationException e,
	 * String field, String additionalInfo) { switch (e.getErrorCode()) { case
	 * "E001": return new ValidationException(e.getErrorCode(), field); case "E002":
	 * return new ValidationException(e.getErrorCode(), field, additionalInfo);
	 * default: return e; }
	 * 
	 * }
	 */
}

//startindex logic for string delimiter
//int count = 0;
//		int startIndex = 0;
//		int delimiterLength = GeneralUtils.findLength(delimiter);
//		int index = strBuilder.indexOf(delimiter , startIndex);
//		int flag = 0;//to check operationAt is greater than no.of strings
//		while(index!= -1){
//			count++;
//			if(count == operationAt){
//				flag =1;
//				break;
//			}
//			startIndex = index+delimiterLength;
//			index = strBuilder.indexOf(delimiter , startIndex);
//		}
//		if(!isInsert && flag == 0){
//			throw new BoundaryCheckException("String number is greater than the number of strings ");
//		}
//		return startIndex;