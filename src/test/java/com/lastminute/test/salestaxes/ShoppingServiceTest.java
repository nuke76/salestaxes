package com.lastminute.test.salestaxes;

import java.util.ArrayList;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import com.lastminute.test.salestaxes.Item;
import com.lastminute.test.salestaxes.ItemType;
import com.lastminute.test.salestaxes.Purchase;
import com.lastminute.test.salestaxes.PurchaseBean;
import com.lastminute.test.salestaxes.ReceiptPrinter;
import com.lastminute.test.salestaxes.ReceiptStringPrinter;
import com.lastminute.test.salestaxes.ShoppingCart;
import com.lastminute.test.salestaxes.ShoppingService;
import com.lastminute.test.salestaxes.TaxCalculator;
import com.lastminute.test.salestaxes.TaxCalculatorService;
import com.lastminute.test.salestaxes.TaxRoundRule;
import com.lastminute.test.salestaxes.TaxRoundRuleExample;

import static org.junit.Assert.*;

public class ShoppingServiceTest {

	private ShoppingService shoppingService;

	@Before
	public void setUp() {

		float basicTaxPercentage = 10.00f;
		TaxRoundRule taxRoundRule = new TaxRoundRuleExample(0.05f);
		float importedTaxPercentage = 5.00f;

		List<ItemType> exceptTaxItemTypeList = new ArrayList<ItemType>();
		exceptTaxItemTypeList.add(ItemType.BOOKS);
		exceptTaxItemTypeList.add(ItemType.FOOD);
		exceptTaxItemTypeList.add(ItemType.MEDICAL);

		TaxCalculator taxCalculator = new TaxCalculatorService(exceptTaxItemTypeList, taxRoundRule, basicTaxPercentage, importedTaxPercentage);

		ReceiptPrinter receiptPrinter = new ReceiptStringPrinter();

		shoppingService = new ShoppingService(taxCalculator, receiptPrinter);
	}

	@Test
	public void getReceiptFormattedForOnePurchase() throws Exception {

		int quantity = 1;
		Item item = new Item();
		item.setDescription("description");
		item.setImported(false);
		item.setItemType(ItemType.BOOKS);
		item.setPrice(12.49f);
		Purchase purchase = new PurchaseBean(item, quantity);

		List<Purchase> purchaseList = new ArrayList<Purchase>();
		purchaseList.add(purchase);

		ShoppingCart cart = new ShoppingCart(purchaseList);

		String actual = shoppingService.getReceipt(cart);

		String expected = "1 description: 12.49\nSales Taxes: 0.00\nTotal: 12.49";

		assertEquals(expected, actual);

		item.setImported(true);

		expected = "1 imported description: 13.14\nSales Taxes: 0.65\nTotal: 13.14";

		actual = shoppingService.getReceipt(cart);

		assertEquals(expected, actual);
	}

	@Test
	public void testInput1() throws Exception {

		List<Purchase> purchaseList = new ArrayList<Purchase>();

		int quantity = 1;

		Item item = new Item();
		item.setDescription("book");
		item.setImported(false);
		item.setItemType(ItemType.BOOKS);
		item.setPrice(12.49f);

		Purchase purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		item = new Item();
		item.setDescription("music CD");
		item.setImported(false);
		item.setItemType(ItemType.MUSIC_CD);
		item.setPrice(14.99f);

		purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		item = new Item();
		item.setDescription("chocolate bar");
		item.setImported(false);
		item.setItemType(ItemType.FOOD);
		item.setPrice(0.85f);

		purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		ShoppingCart cart = new ShoppingCart(purchaseList);

		String actual = shoppingService.getReceipt(cart);

		String expected = "1 book: 12.49\n" + "1 music CD: 16.49\n" + "1 chocolate bar: 0.85\n" + "Sales Taxes: 1.50\n" + "Total: 29.83";

		assertEquals(expected, actual);

	}

	@Test
	public void testInput2() throws Exception {
		List<Purchase> purchaseList = new ArrayList<Purchase>();

		int quantity = 1;

		Item item = new Item();
		item.setDescription("box of chocolates");
		item.setImported(true);
		item.setItemType(ItemType.FOOD);
		item.setPrice(10.00f);

		Purchase purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		item = new Item();
		item.setDescription("bottle of perfume");
		item.setImported(true);
		item.setItemType(ItemType.PERFUME);
		item.setPrice(47.50f);

		purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		ShoppingCart cart = new ShoppingCart(purchaseList);

		String actual = shoppingService.getReceipt(cart);

		String expected = "1 imported box of chocolates: 10.50\n" + "1 imported bottle of perfume: 54.65\n" + "Sales Taxes: 7.65\n" + "Total: 65.15";

		assertEquals(expected, actual);
	}

	@Test
	public void testInput3() throws Exception {
		List<Purchase> purchaseList = new ArrayList<Purchase>();

		int quantity = 1;

		Item item = new Item();
		item.setDescription("bottle of perfume");
		item.setImported(true);
		item.setItemType(ItemType.PERFUME);
		item.setPrice(27.99f);

		Purchase purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		item = new Item();
		item.setDescription("bottle of perfume");
		item.setImported(false);
		item.setItemType(ItemType.PERFUME);
		item.setPrice(18.99f);

		purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		item = new Item();
		item.setDescription("packet of headache pills");
		item.setImported(false);
		item.setItemType(ItemType.MEDICAL);
		item.setPrice(9.75f);

		purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		item = new Item();
		item.setDescription("box of chocolates");
		item.setImported(true);
		item.setItemType(ItemType.FOOD);
		item.setPrice(11.25f);

		purchase = new PurchaseBean(item, quantity);
		purchaseList.add(purchase);

		ShoppingCart cart = new ShoppingCart(purchaseList);

		String actual = shoppingService.getReceipt(cart);

		String expected = "1 imported bottle of perfume: 32.19\n"+
											"1 bottle of perfume: 20.89\n"+
											"1 packet of headache pills: 9.75\n"+
											"1 imported box of chocolates: 11.85\n"+
											"Sales Taxes: 6.70\n"+
											"Total: 74.68";

		assertEquals(expected, actual);
	}

}
