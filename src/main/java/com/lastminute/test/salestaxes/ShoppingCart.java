package com.lastminute.test.salestaxes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart implements Iterable<Purchase>{
	
	private List<Purchase> purchaseList;
	
	public ShoppingCart() {
		purchaseList = new ArrayList<Purchase>();
	}

	public ShoppingCart(List<Purchase> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public void addPurchase(Purchase purchase) {
		purchaseList.add(purchase);
	}

	@Override
	public Iterator<Purchase> iterator() {
		
		return this.purchaseList.iterator();
	}
	
	
}
