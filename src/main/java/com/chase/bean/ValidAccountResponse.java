package com.chase.bean;

public class ValidAccountResponse {
	
	boolean isValid;
	String source;
	public boolean isValid() {
		return isValid;
	}
	public String getSource() {
		return source;
	}
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
	public void setSource(String source) {
		this.source = source;
	}
	

}
