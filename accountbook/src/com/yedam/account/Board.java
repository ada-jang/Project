package com.yedam.account;

public class Board {
	private String  list;
	private int price;
	private String date;
	
	public Board (String list, int price, String date) {
		super();
		this.list = list;
		this.price = price;
		this.date = date;
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
		return date;
	}
	public void setDate(String date) {
		this.date = date;
	}
	
}
