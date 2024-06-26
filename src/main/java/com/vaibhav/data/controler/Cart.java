package com.vaibhav.data.controler;

import java.util.List;

public class Cart {
	public List<Menu> menu;
	public double totalPrice;
	public List<Menu> getMenu() {
		return menu;
	}
	public void setMenu(List<Menu> menu) {
		this.menu = menu;
	}
	public double getTotalPrice() {
		return totalPrice;
	}
	public void setTotalPrice(double totalPrice) {
		this.totalPrice = totalPrice;
	}
	public Cart(List<Menu> menu, double totalPrice) {
		super();
		this.menu = menu;
		this.totalPrice = totalPrice;
	}
	public Cart() {
		// TODO Auto-generated constructor stub
	}
	
	

}
