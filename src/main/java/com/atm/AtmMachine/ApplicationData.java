package com.atm.AtmMachine;

import java.util.TreeSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atm.model.ATMMachine;
import com.atm.model.Denomination;

@Component
public class ApplicationData {

	private ATMMachine atmMachine;

	private static TreeSet<com.atm.model.Denomination> denominationAll = null;
	
	public ATMMachine getAtmMachine() {
		return atmMachine;
	}

	// ATM initialization
	@Autowired
	public void setAtmMachine(ATMMachine atmMachine) {
		
		atmMachine.setDenominationAll(getDenominationAll());
		
		this.atmMachine = atmMachine;
	}

	
	
	private static TreeSet<Denomination> getDenominationAll() {
		
		if (denominationAll == null) {
			denominationAll = new TreeSet<Denomination>();
			denominationAll.add(new Denomination(50, 20));
			denominationAll.add(new Denomination(20, 30));
			denominationAll.add(new Denomination(10, 30));
			denominationAll.add(new Denomination(5, 20));
		}
		return denominationAll;
	}

}
