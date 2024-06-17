package com.gn.model.vo;

public class Buy {
	private int buy_no;
	private int buy_userNo;
	private int buy_prodNo;
	private int buy_amount;
	public Buy() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Buy(int buy_no, int buy_userNo, int buy_prodNo, int buy_amount) {
		super();
		this.buy_no = buy_no;
		this.buy_userNo = buy_userNo;
		this.buy_prodNo = buy_prodNo;
		this.buy_amount = buy_amount;
	}
	public int getBuy_no() {
		return buy_no;
	}
	public void setBuy_no(int buy_no) {
		this.buy_no = buy_no;
	}
	public int getBuy_userNo() {
		return buy_userNo;
	}
	public void setBuy_userNo(int buy_userNo) {
		this.buy_userNo = buy_userNo;
	}
	public int getBuy_prodNo() {
		return buy_prodNo;
	}
	public void setBuy_prodNo(int buy_prodNo) {
		this.buy_prodNo = buy_prodNo;
	}
	public int getBuy_amount() {
		return buy_amount;
	}
	public void setBuy_amount(int buy_amount) {
		this.buy_amount = buy_amount;
	}
	@Override
	public String toString() {
		return buy_no + "||" + buy_userNo + "||" + buy_prodNo 
				+ "||" + buy_amount;
	}
	
	
	
}
