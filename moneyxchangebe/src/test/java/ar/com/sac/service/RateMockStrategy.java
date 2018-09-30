package ar.com.sac.service;

import java.math.BigDecimal;

public class RateMockStrategy implements RateStrategy{

	private static final BigDecimal MOCK_RATE = new BigDecimal(1.1);

	@Override
	public BigDecimal getRate() {
		return MOCK_RATE;
	}
	
}
