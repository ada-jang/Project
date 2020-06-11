package com.yedam.account;

import javafx.beans.property.SimpleIntegerProperty;
import javafx.beans.property.SimpleStringProperty;

public class Board {
	private SimpleStringProperty  list;
	private SimpleIntegerProperty price;
	private SimpleStringProperty exitDate;
	
	public Board (String list, int price, String exitDate) {
		super();
		this.list = new SimpleStringProperty(list);
		this.price = new SimpleIntegerProperty(price);
		this.exitDate = new SimpleStringProperty(exitDate);
	}
	
	public void setList(String list) {
		this.list.set(list);
	}

	public String getList() {
		return this.list.get();
	}

	public SimpleStringProperty listProperty() {
		return this.list;
	}

	public void setPrice(int price) {
		this.price.set(price);
	}

	public int getPrice() {
		return this.price.get();
	}

	public void setexitDate(String exitDate) {
		this.list.set(exitDate);
	}

	public String getexitDate() {
		return this.exitDate.get();
	}

	public SimpleStringProperty exitDateProperty() {
		return this.exitDate;
	}
	
}
