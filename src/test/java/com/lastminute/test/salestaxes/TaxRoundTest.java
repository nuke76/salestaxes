package com.lastminute.test.salestaxes;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TaxRoundTest {

	@Test
	public void roundAtNearestUpTo005() throws Exception {

		TaxRoundRule taxRoundRule = new TaxRoundRuleExample(0.05f);

		assertEquals(10.05f, taxRoundRule.round(10.05f), 0.009f);

		assertEquals(10.05f, taxRoundRule.round(10.044f), 0.009f);

		assertEquals(10.05f, taxRoundRule.round(10.01f), 0.009f);

		assertEquals(10.1f, taxRoundRule.round(10.054f), 0.009f);

		assertEquals(10.00f, taxRoundRule.round(10.00f), 0.009f);
	}

}
