package com.lastminute.test.salestaxes;

public class TaxRoundRuleExample implements TaxRoundRule {

	float roundOff;

	public TaxRoundRuleExample(float roundOff) {
		this.roundOff = roundOff;
	}

	@Override
	public float round(float tax) {
		return (float) Math.ceil(tax / roundOff) * roundOff;
	}

}
