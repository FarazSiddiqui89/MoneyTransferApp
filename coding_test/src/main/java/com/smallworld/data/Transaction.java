package com.smallworld.data;

import java.util.List;

public class Transaction {
    // Represent your transaction data here.
	
	private int mtn;
	private int senderAge;
	private int beneficiaryAge;
	private double amount;
	private String senderFullName;
	private String beneficiaryFullName;
	private List<Issue> issues;
	
	public int getMtn() {
		return mtn;
	}
	public void setMtn(int mtn) {
		this.mtn = mtn;
	}
	public int getSenderAge() {
		return senderAge;
	}
	public void setSenderAge(int senderAge) {
		this.senderAge = senderAge;
	}
	public int getBeneficiaryAge() {
		return beneficiaryAge;
	}
	public void setBeneficiaryAge(int beneficiaryAge) {
		this.beneficiaryAge = beneficiaryAge;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSenderFullName() {
		return senderFullName;
	}
	public void setSenderFullName(String senderFullName) {
		this.senderFullName = senderFullName;
	}
	public String getBeneficiaryFullName() {
		return beneficiaryFullName;
	}
	public void setBeneficiaryFullName(String beneficiaryFullName) {
		this.beneficiaryFullName = beneficiaryFullName;
	}
	public List<Issue> getIssues() {
		return issues;
	}
	public void setIssues(List<Issue> issues) {
		this.issues = issues;
	}
	
	
}
