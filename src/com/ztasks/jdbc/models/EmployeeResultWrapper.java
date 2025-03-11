package com.ztasks.jdbc.models;

import java.util.Map;

public class EmployeeResultWrapper {
	private boolean isAllSuccess;
	private Map<Employee,Map<String,String>> failedEntries;
	private int successCount;
	
	public EmployeeResultWrapper(boolean isAllSuccess,Map<Employee,Map<String,String>> failedEntries,int successCount) {
		this.isAllSuccess = isAllSuccess;
		this.failedEntries = failedEntries;
		this.successCount = successCount;
	}
	
	public boolean isAllSuccess() {
		return isAllSuccess;
	}

	public Map<Employee, Map<String, String>> getFailedEntries() {
		return failedEntries;
	}

	public int getSuccessCount() {
		return successCount;
	}
	
}
