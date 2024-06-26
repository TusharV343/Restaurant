package com.vaibhav.data.controler;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;


@Entity

public class Menu {
	@GeneratedValue
	@Id
	int id;
	String itemName;
	String itemPrice;
	
	

	public int getId() {
		return id;
	}



	public void setId(int id) {
		this.id = id;
	}



	public String getItemName() {
		return itemName;
	}



	public void setItemName(String itemName) {
		this.itemName = itemName;
	}



	public String getItemPrice() {
		return itemPrice;
	}



	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}



	public Menu(int id, String itemName, String itemPrice) {
		super();
		this.id = id;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
	}



	public Menu() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
}
	