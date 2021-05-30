package com.atm.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

import lombok.Data;

@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@JsonInclude(Include.NON_NULL)
public class Customer {

	private String atmCardNumber;
	
	private int atmPin;
	
	private long balance;
	
	public Customer() {
		
	}
	
	public Customer(String atmCardNumber, int atmPin, int balance) {
		super();
		this.atmCardNumber = atmCardNumber;
		this.atmPin = atmPin;
		this.balance = balance;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((atmCardNumber == null) ? 0 : atmCardNumber.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Customer other = (Customer) obj;
		 if (!atmCardNumber.equals(other.atmCardNumber))
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "Customer [atmCardNumber=" + atmCardNumber + ", balance=" + balance +  "]";
	}
	
}
