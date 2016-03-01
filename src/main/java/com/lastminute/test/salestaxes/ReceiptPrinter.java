package com.lastminute.test.salestaxes;

import java.util.List;

public interface ReceiptPrinter {
	
	public String print(List<ReceiptItem> receiptItems, float totalTax);

}
