package com.atm.dao;


import com.atm.model.AtmRequest;


public interface CustomerAccountDao {
	
	public boolean validateCustomer(AtmRequest atmRequest);

	public long fetchCustomerBalance(AtmRequest atmRequest);
	
	public void updateCustomerBalance(AtmRequest atmRequest, long customerBalanceNew);
	

	
}
