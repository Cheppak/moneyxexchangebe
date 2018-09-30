package ar.com.sac.controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.sac.entity.ExchangeRate;
import ar.com.sac.repository.ExchangeRateRepository;
import ar.com.sac.service.ExchangeRateService;

@RestController
public class ExchangeRateController {
	
	ExchangeRateService exchangeRateService; 
	
	@Autowired
	public ExchangeRateController(ExchangeRateService humanService) {
		this.exchangeRateService = humanService;
	}
	
    @RequestMapping(value="/cotizacion", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public @ResponseBody String cotizacion(@RequestParam("value") BigDecimal value){

    	BigDecimal rate = this.exchangeRateService.getExchangeRate(value);
    	    	
    	return rate.toEngineeringString();
    }
}
