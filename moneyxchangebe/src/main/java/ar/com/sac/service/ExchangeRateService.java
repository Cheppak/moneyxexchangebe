package ar.com.sac.service;

import java.math.BigDecimal;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import ar.com.sac.entity.ExchangeRate;
import ar.com.sac.repository.ExchangeRateRepository;

@Service("exchangeService")
@Transactional
public class ExchangeRateService {
	
	private static final long ID = 1l;
	private ExchangeRateRepository exchangeRateRepository;
	
	@Autowired
	private RateStrategy rateStrategy;
	
	@Autowired
	public ExchangeRateService(ExchangeRateRepository exchangeRateRepository, RateStrategy rateStrategy){
		this.exchangeRateRepository = exchangeRateRepository;
		this.rateStrategy = rateStrategy;
	}
	
	public BigDecimal getExchangeRate(BigDecimal value){
		
		BigDecimal rate;
		// La primera vez que levanto la app...
    	if(!exchangeRateRepository.existsById(ID)){
    		rate = rateStrategy.getRate();
    		ExchangeRate entity = new ExchangeRate(ID, LocalDateTime.now(), rate );
    		exchangeRateRepository.save(entity);
    		return rate.multiply(value);
    	}

    	Optional<ExchangeRate> entity =  exchangeRateRepository.findById(ID);
    	ExchangeRate exchangeRate = entity.get();
   		LocalDateTime lastTime = exchangeRate.getTime();
   		
    	Duration duration = Duration.between(LocalDateTime.now(), lastTime);
    	long diff = Math.abs(duration.toMinutes());
    	if(diff > 10){
    		rate = rateStrategy.getRate();
    		exchangeRateRepository.save(new ExchangeRate(ID, LocalDateTime.now(), rate ));
    	}else{
    		rate = exchangeRate.getRate();
    	}
    	
    	return rate.multiply(value);
	}
	
}
