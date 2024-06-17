package com.gn.model.vo;

public class Product {
	private int prodNo;
	private String prodName;
	private int prodPrice;
	private int haveAmount;
	
	public Product() {
		super();
		// TODO Auto-generated constructor stub
	}

	public Product(int prodNo, String prodName, int prodPrice, int haveAmount) {
		super();
		this.prodNo = prodNo;
		this.prodName = prodName;
		this.prodPrice = prodPrice;
		this.haveAmount = haveAmount;
	}

	public int getProdNo() {
		return prodNo;
	}

	public void setProdNo(int prodNo) {
		this.prodNo = prodNo;
	}

	public String getProdName() {
		return prodName;
	}

	public void setProdName(String prodName) {
		this.prodName = prodName;
	}

	public int getProdPrice() {
		return prodPrice;
	}

	public void setProdPrice(int prodPrice) {
		this.prodPrice = prodPrice;
	}

	public int getHaveAmount() {
		return haveAmount;
	}

	public void setHaveAmount(int haveAmount) {
		this.haveAmount = haveAmount;
	}

	@Override
	public String toString() {
		return prodNo + "||" + prodName + "||" + prodPrice + "||"
				+ haveAmount;
	}
	
}
