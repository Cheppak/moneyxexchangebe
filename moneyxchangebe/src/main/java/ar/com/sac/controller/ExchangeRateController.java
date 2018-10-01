package ar.com.sac.controller;

import java.math.BigDecimal;
import java.text.DecimalFormat;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import ar.com.sac.service.ExchangeRateService;

import com.google.gson.Gson;
import com.google.gson.JsonObject;

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
    	return buildJsonResponse(rate);
    }
    
    private String buildJsonResponse(BigDecimal rate){
    	Gson gson = new Gson();
    	DecimalFormat df = new DecimalFormat();
    	df.setMaximumFractionDigits(2);
    	df.setMinimumFractionDigits(0);
    	df.setGroupingUsed(true);
    	df.setGroupingSize(3);
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("rate", df.format(rate));
		return gson.toJson(jsonObject);
    }
    
}
