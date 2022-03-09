package com.chase.bean;

import java.util.List;

import javax.validation.constraints.NotNull;

public class AccountInfoBean {

	@NotNull(message = "account number can not be null")
	private String accountNumber;
	private List<String> sources;

	public AccountInfoBean(String accountNumber, List<String> sources) {
		super();
		this.accountNumber = accountNumber;
		this.sources = sources;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public List<String> getSources() {
		return sources;
	}

	public void setAccountNumber(String accountNumber) {
		this.accountNumber = accountNumber;
	}

	public void setSources(List<String> sources) {
		this.sources = sources;
	}

	@Override
	public String toString() {
		return "AccountInfoBean [accountNumber=" + accountNumber + ", source=" + sources + "]";
	}

}
