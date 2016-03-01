package com.lastminute.test.salestaxes;

public class ReceiptItem {

	private float amount;
	private Purchase purchase;
	

	public void setAmout(float amount) {
		this.amount = amount;
		
	}

	public float getAmount() {
		return amount;
	}

	public Purchase getPurchase() {
		// TODO Auto-generated method stub
		return this.purchase;
	}

	public void setPurchase(Purchase purchase) {
		this.purchase = purchase;
	}

}
