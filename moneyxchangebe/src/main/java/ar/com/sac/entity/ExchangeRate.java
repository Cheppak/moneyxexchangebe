package ar.com.sac.entity;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ExchangeRate {

	@Id
    private Long id;
	private LocalDateTime time;
	private BigDecimal rate;
	
	public ExchangeRate(){
	}

	public ExchangeRate(Long id, LocalDateTime time, BigDecimal rate){
		this.id = id;
		this.time = time;
		this.rate = rate;
	}
	
	public LocalDateTime getTime() {
		return time;
	}
	public void setTime(LocalDateTime time) {
		this.time = time;
	}
	public BigDecimal getRate() {
		return rate;
	}
	public void setRate(BigDecimal rate) {
		this.rate = rate;
	}
}
