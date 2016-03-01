package com.lastminute.test.salestaxes;

public class ReceiptItem {

	private float amount;
	private PurchaseBean purchase;
	

	public void setAmout(float amount) {
		this.amount = amount;
		
	}

	public float getAmount() {
		return amount;
	}

	public PurchaseBean getPurchase() {
		// TODO Auto-generated method stub
		return this.purchase;
	}

	public void setPurchase(PurchaseBean purchase) {
		this.purchase = purchase;
	}

}
