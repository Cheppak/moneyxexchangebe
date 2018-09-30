package ar.com.sac.service;

import java.math.BigDecimal;

import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

@Component
public class RateWsStrategy implements RateStrategy{

	@Override
	public BigDecimal getRate() {
		HttpHeaders headers = new HttpHeaders();
		headers.set("Accept", MediaType.APPLICATION_JSON_VALUE);
		RestTemplate restTemplate = new RestTemplate();
		
		// REVIEW esta api no nos permite obtener la cotizacion de forma correcta, dado que debemos pagar para que ello ocurra. 
		// Es por eso que por el momento lo configuramos asi. 
		UriComponentsBuilder builder = UriComponentsBuilder.fromHttpUrl("http://data.fixer.io/api/latest")
		        .queryParam("base", "EUR")
		        .queryParam("symbols", "USD")
		        .queryParam("access_key", "8acb6005c44002c62e863bd419463603");
		HttpEntity<?> entity = new HttpEntity<>(headers);
		
		// WARNING. La api nos deja solo 1000 peticiones
		HttpEntity<String> response = restTemplate.exchange(
		        builder.toUriString(), 
		        HttpMethod.GET, 
		        entity, 
		        String.class);
		try {
			JSONObject myObject = new JSONObject(response.getBody());
			JSONObject rates = (JSONObject) myObject.get("rates");
			return new BigDecimal((Double)rates.get("USD"));
		} catch (JSONException e) {
			throw new RuntimeException("Problemas en la respuesta JSON: "+e.getMessage());
		}
	}
	
}
