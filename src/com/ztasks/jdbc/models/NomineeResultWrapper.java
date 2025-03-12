package com.ztasks.jdbc.models;

import java.util.Map;

public class NomineeResultWrapper {
	private boolean isAllSuccess;
	private Map<Nominee,Map<String,String>> failedEntries;
	private int successCount;
	
	public NomineeResultWrapper(boolean isAllSuccess,Map<Nominee,Map<String,String>> failedEntries,int successCount) {
		this.isAllSuccess = isAllSuccess;
		this.failedEntries = failedEntries;
		this.successCount = successCount;
	}
	
	public boolean isAllSuccess() {
		return isAllSuccess;
	}

	public Map<Nominee, Map<String, String>> getFailedEntries() {
		return failedEntries;
	}

	public int getSuccessCount() {
		return successCount;
	}
	
}

