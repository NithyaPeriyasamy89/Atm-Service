package com.atm.model;

import javax.validation.constraints.Size;

import com.atm.validator.AtmPin;

import lombok.Data;

@Data
public class AtmRequest {
	
	private int amount;
	
	
	@AtmPin
	//@Size(min = 4, message = "Atm pin should be of 4 digits")
	private int atmPin;
	
//	@ATMCardNumber
	@Size(max = 16, min=16, message = "Atm card number should be of 16 digits")
	private String atmCardNumber;
	

}
