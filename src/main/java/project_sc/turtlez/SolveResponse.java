package project_sc.turtlez;

import java.util.List;

public class SolveResponse {
	private double average_rate;
	private User[] result;
	private Double[] rates;
	private User me;
	
	public double getAverage_rate() {
		return average_rate;
	}
	public void setAverage_rate(double average_rate) {
		this.average_rate = average_rate;
	}
	public User[] getResult() {
		return result;
	}
	public void setResult(User[] result) {
		this.result = result;
	}
	public Double[] getRates() {
		return rates;
	}
	public void setRates(Double[] rates) {
		this.rates = rates;
	}
	public User getMe() {
		return me;
	}
	
	public void setMe(User me) {
		this.me = me;
	}
	
	
	
}
