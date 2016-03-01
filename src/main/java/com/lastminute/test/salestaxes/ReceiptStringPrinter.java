package com.lastminute.test.salestaxes;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ReceiptStringPrinter  implements ReceiptPrinter {
	
	private static final Logger log = LoggerFactory.getLogger(ReceiptStringPrinter.class);
	
	@Override
	public String print(List<ReceiptItem> receiptItems, float totalTax) {
		StringBuilder receiptBuilder = new StringBuilder();
		float totalAmount = 0.00f;
		
		for (ReceiptItem receiptItem : receiptItems) {
			StringBuilder builder = buildReceiptLine(receiptItem);
			totalAmount += receiptItem.getAmount();
			receiptBuilder.append(builder.toString());
		}
		receiptBuilder.append("Sales Taxes: "+String.format("%.2f", totalTax)+"\n");
		receiptBuilder.append("Total: "+String.format("%.2f", totalAmount));
		
		log.debug(receiptBuilder.toString());
		
		return receiptBuilder.toString();
	}

	private StringBuilder buildReceiptLine(ReceiptItem receiptItem) {
		StringBuilder builder = new StringBuilder();
		builder.append(receiptItem.getPurchase().getQuantity());
		builder.append(" ");
		builder.append((receiptItem.getPurchase().getItem().isImported() == true) ? "imported " : "");
		builder.append(receiptItem.getPurchase().getItem().getDescription());
		builder.append(": ");
		builder.append(String.format("%.2f", receiptItem.getAmount()));
		builder.append("\n");
		return builder;
	}

}
