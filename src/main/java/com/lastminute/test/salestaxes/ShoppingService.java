package com.lastminute.test.salestaxes;

import java.util.ArrayList;
import java.util.List;

public class ShoppingService {
	
	private TaxCalculator taxCalculator;
	private ReceiptPrinter receiptPrinter;
	
	public ShoppingService(TaxCalculator taxCalculator, ReceiptPrinter receiptPrinter) {
		this.taxCalculator = taxCalculator;
		this.receiptPrinter = receiptPrinter;
	}
	
	public String getReceipt(ShoppingCart cart) {
		
		List<ReceiptItem> receiptItemList = new ArrayList<ReceiptItem>();
		float totalTax = 0.0f;
		
		for (PurchaseBean purchase : cart) {
			ReceiptItem receiptItem = new ReceiptItem();
			receiptItem.setPurchase(purchase);
			float amountNoTaxed = purchase.getItem().getPrice()*purchase.getQuantity();
			float tax = taxCalculator.calculateTaxForPurchase(purchase);
			receiptItem.setAmout(amountNoTaxed+tax);
			receiptItemList.add(receiptItem);
			totalTax += tax;
		}
		return receiptPrinter.print(receiptItemList, totalTax);
	}
}
