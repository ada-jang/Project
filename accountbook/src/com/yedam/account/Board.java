package com.yedam.account;

public class Board {
	private String  list;
	private int price;
	private String exitDate;
	
	public Board (String list, int price, String exitDate) {
		super();
		this.list = list;
		this.price = price;
		this.exitDate = exitDate;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getDate() {
		return exitDate;
	}
	public void setDate(String date) {
		this.exitDate = date;
	}
	
}
