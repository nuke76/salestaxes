package com.lastminute.test.salestaxes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import com.lastminute.test.salestaxes.Item;
import com.lastminute.test.salestaxes.ItemType;
import com.lastminute.test.salestaxes.Purchase;
import com.lastminute.test.salestaxes.PurchaseBean;
import com.lastminute.test.salestaxes.TaxCalculator;
import com.lastminute.test.salestaxes.TaxCalculatorService;
import com.lastminute.test.salestaxes.TaxRoundRule;
import com.lastminute.test.salestaxes.TaxRoundRuleExample;

import static org.junit.Assert.*;

public class TaxCalculatorTest {

	@Test
	public void shouldCalculateTaxForOnePuchaseNotImportedTaxFree() throws Exception {

		List<ItemType> exceptTaxItemTypeList = new ArrayList<ItemType>();
		exceptTaxItemTypeList.add(ItemType.BOOKS);
		exceptTaxItemTypeList.add(ItemType.FOOD);
		exceptTaxItemTypeList.add(ItemType.MEDICAL);

		TaxRoundRule taxRoundRule = new TaxRoundRuleExample(0.05f);

		float importedTaxPercentage = 5.0f;
		float basicTaxPercentage = 10.0f;

		Item item = new Item();
		item.setDescription("book");
		item.setItemType(ItemType.BOOKS);
		item.setImported(false);
		item.setPrice(12.49f);
		Purchase purchase = new PurchaseBean(item, 1);

		TaxCalculator taxCalculator = new TaxCalculatorService(exceptTaxItemTypeList, taxRoundRule, basicTaxPercentage, importedTaxPercentage);

		float tax = taxCalculator.calculateTaxForPurchase(purchase);

		assertEquals(0.00f, tax, 0);

	}

	@Test
	public void shouldCalculateTaxForOnePuchaseImportedTaxFree() throws Exception {

		List<ItemType> exceptTaxItemTypeList = new ArrayList<ItemType>();
		exceptTaxItemTypeList.add(ItemType.BOOKS);
		exceptTaxItemTypeList.add(ItemType.FOOD);
		exceptTaxItemTypeList.add(ItemType.MEDICAL);

		TaxRoundRule taxRoundRule = new TaxRoundRuleExample(0.05f);

		float importedTaxPercentage = 5.0f;
		float basicTaxPercentage = 10.0f;

		Item item = new Item();
		item.setDescription("BOX OF CHOCOLATE");
		item.setItemType(ItemType.FOOD);
		item.setImported(true);
		item.setPrice(10.00f);
		Purchase purchase = new PurchaseBean(item, 1);

		TaxCalculator taxCalculator = new TaxCalculatorService(exceptTaxItemTypeList, taxRoundRule, basicTaxPercentage, importedTaxPercentage);

		float tax = taxCalculator.calculateTaxForPurchase(purchase);

		assertEquals(0.5f, tax, 0.009f);

	}

	@Test
	public void shouldCalculateTaxForOnePuchaseNotImportedNotTaxFree() throws Exception {

		List<ItemType> exceptTaxItemTypeList = new ArrayList<ItemType>();
		exceptTaxItemTypeList.add(ItemType.BOOKS);
		exceptTaxItemTypeList.add(ItemType.FOOD);
		exceptTaxItemTypeList.add(ItemType.MEDICAL);

		TaxRoundRule taxRoundRule = new TaxRoundRuleExample(0.05f);

		//posso metterle in una setup
		float importedTaxPercentage = 5.0f;
		float basicTaxPercentage = 10.0f;

		Item item = new Item();
		item.setDescription("BOTTLE OF PERFUME");
		item.setItemType(ItemType.PERFUME);
		item.setImported(false);
		item.setPrice(18.99f);
		Purchase purchase = new PurchaseBean(item, 1);

		TaxCalculator taxCalculator = new TaxCalculatorService(exceptTaxItemTypeList, taxRoundRule, basicTaxPercentage, importedTaxPercentage);

		float tax = taxCalculator.calculateTaxForPurchase(purchase);

		assertEquals(1.90f, tax, 0.001f);

	}

}
