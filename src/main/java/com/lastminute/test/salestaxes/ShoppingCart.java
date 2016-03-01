package com.lastminute.test.salestaxes;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class ShoppingCart implements Iterable<PurchaseBean>{
	
	private List<PurchaseBean> purchaseList;
	
	public ShoppingCart() {
		purchaseList = new ArrayList<PurchaseBean>();
	}

	public ShoppingCart(List<PurchaseBean> purchaseList) {
		this.purchaseList = purchaseList;
	}
	public void addPurchase(PurchaseBean purchase) {
		purchaseList.add(purchase);
	}

	@Override
	public Iterator<PurchaseBean> iterator() {
		
		return this.purchaseList.iterator();
	}
	
	
}
