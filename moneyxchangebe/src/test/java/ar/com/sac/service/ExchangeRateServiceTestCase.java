package ar.com.sac.service;

import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import ar.com.sac.repository.ExchangeRateRepository;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.MOCK)
public class ExchangeRateServiceTestCase {

	@MockBean
	private ExchangeRateRepository repository;
	
	public void testFirstTime(){
		
	}
	
	public void testMoreThan10Minutes(){
		
	}
	
	public void testLessThan10Minutes(){
		
	}
	
}
