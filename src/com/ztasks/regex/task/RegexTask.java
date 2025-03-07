package com.ztasks.regex.task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import com.exception.InvalidArgumentException;
import com.generalutils.GeneralUtils;

public class RegexTask {
	private static final String MOBILEREGEX = "^[789]\\d{9}$";
	private static final String ALPHANUMERICREGEX = "^[a-zA-Z0-9]+$";
	private static final String MAILREGEX = "^[a-zA-Z0-9][\\w.+-]*@[a-zA-Z0-9-]+\\.[a-zA-Z]{2,}$";
	private static final String STRINGLENGTHREGEX = "^[a-zA-Z]{1,6}";
	private static final String HTMLTAGREGEX = "</?\\w+>";

	public boolean validateMobile(String mobile) throws InvalidArgumentException {
		return GeneralUtils.validatePattern(MOBILEREGEX, mobile);
	}

	public boolean validateAlphaNumeric(String str) throws InvalidArgumentException {
		return GeneralUtils.validatePattern(ALPHANUMERICREGEX, str);
	}

	public boolean checkStartsWith(String str, String matchingStr) throws InvalidArgumentException {
		String quotedString = GeneralUtils.quote(matchingStr);
		String regex = "^" + quotedString + ".*";
		return GeneralUtils.matchPattern(regex, str);
	}

	public boolean checkContains(String str, String matchingStr) throws InvalidArgumentException {
		String quotedString = GeneralUtils.quote(matchingStr);
		String regex = ".*" + quotedString + ".*";
		return GeneralUtils.matchPattern(regex, str);
	}

	public boolean checkExactMatch(String str, String matchingStr) throws InvalidArgumentException {
		String quotedString = GeneralUtils.quote(matchingStr);
		String regex = "^" + quotedString + "$";
		return GeneralUtils.matchPattern(regex, str);
	}

	public boolean checkEndsWith(String str, String matchingStr) throws InvalidArgumentException {
		String quotedString = GeneralUtils.quote(matchingStr);
		String regex = ".*" + quotedString + "$";
		return GeneralUtils.matchPattern(regex, str);
	}

	public List<Boolean> validateCaseInsensitive(List<String> list, String matchingStr)
			throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(list);

		String quotedString = GeneralUtils.quote(matchingStr);
		String regex = "^" + quotedString + "$";
		Pattern pattern = Pattern.compile(regex, Pattern.CASE_INSENSITIVE);

		List<Boolean> resultList = new ArrayList<>();

		for (String str : list) {
			Matcher matcher = pattern.matcher(str);
			resultList.add(matcher.matches());
		}
		return resultList;
	}

	public boolean validateMail(String mail) throws InvalidArgumentException {
		return GeneralUtils.validatePattern(MAILREGEX, mail);
	}

	public boolean validateStringLength(String str) throws InvalidArgumentException {
		return GeneralUtils.validatePattern(STRINGLENGTHREGEX, str);
	}

	public Map<String, List<Integer>> getMatchingIndices(List<String> list, List<String> matchingList)
			throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(matchingList);
		int length = GeneralUtils.findLength(list);

		RegexTask task = new RegexTask();
		Map<String, List<Integer>> indexMap = new HashMap<>();
		for (String match : matchingList) {
			List<Integer> indices = new ArrayList<>();

			for (int i = 0; i < length; i++) {
				boolean result = task.checkExactMatch(list.get(i), match);
				if (result) {
					indices.add(i);
				}
			}

			indexMap.put(match, indices);
		}

		return indexMap;
	}

	public List<String> extractHtmlTags(String input) throws InvalidArgumentException {
		GeneralUtils.checkObjArgIsNull(input);
		List<String> tags = new ArrayList<>();

		Pattern pattern = Pattern.compile(HTMLTAGREGEX);
		Matcher matcher = pattern.matcher(input);

		while (matcher.find()) {
			tags.add(matcher.group());
		}

		return tags;
	}

}
