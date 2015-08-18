package com.intern.model;

public class TranData {
	public int getTranId() {
		return tranId;
	}

	public void setTranId(int tranId) {
		this.tranId = tranId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getDate() {
		return date;
	}

	public void setDate(String date) {
		this.date = date;
	}

	public String getTranType() {
		return tranType;
	}

	public void setTranType(String tranType) {
		this.tranType = tranType;
	}

	public double getAmt() {
		return amt;
	}

	public void setAmt(double amt) {
		this.amt = amt;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	private int tranId;
	private int userId;
	private String date;
	private String tranType;
	private double amt;
	private double balance;
}
