package com.lastminute.test.salestaxes;


import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class TaxCalculatorService implements TaxCalculator {

	private List<ItemType> exceptTaxItemTypeList;
	private TaxRoundRule taxRoundRule;

	private float basicTaxPercentage;
	private float importedTaxPercentage;
	
	@SuppressWarnings("unused")
	private static final Logger log = LoggerFactory.getLogger(TaxCalculatorService.class);

	public TaxCalculatorService(List<ItemType> exceptTaxItemTypeList, TaxRoundRule taxRoundRule, float basicTaxPercentage, float importedTaxPercentage) {
		this.exceptTaxItemTypeList = exceptTaxItemTypeList;
		this.taxRoundRule = taxRoundRule;
		this.basicTaxPercentage = basicTaxPercentage;
		this.importedTaxPercentage = importedTaxPercentage;
	}

	@Override
	public float calculateTaxForPurchase(Purchase purchase) {
		float tax = 0f;

		Item item = purchase.getItem();
		int quantity = purchase.getQuantity();
		
		float taxPercentageToBeApplied = (exceptTaxItemTypeList.contains(item.getItemType()) == false ) ? basicTaxPercentage : 0f;
		
		taxPercentageToBeApplied += (item.isImported()== true)?  importedTaxPercentage : 0;
		
		tax =  ((item.getPrice()*quantity) * taxPercentageToBeApplied) / 100;
		
		return taxRoundRule.round(tax);
	}
}