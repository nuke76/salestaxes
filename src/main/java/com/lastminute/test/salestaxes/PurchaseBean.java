package com.lastminute.test.salestaxes;


public class PurchaseBean {
	
	private Item item;
	private int quantity;
	
	public PurchaseBean(Item item, int quantity) {
		this.item = item;
		this.quantity = quantity;
	}
	
	public Item getItem() {
		return item;
	}

	public int getQuantity() {
		return quantity;
	}

	public void addQuantity(int quantity) {
		quantity++;
		
	}



}
