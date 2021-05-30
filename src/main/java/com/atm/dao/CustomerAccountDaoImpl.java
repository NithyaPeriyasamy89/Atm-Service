package com.atm.dao;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.atm.AtmMachine.ApplicationData;
import com.atm.constants.AtmConstants.ATMMachineErrorKeys;
import com.atm.exception.ValidationException;
import com.atm.model.AtmRequest;
import com.atm.model.Customer;
import com.atm.service.CustomerClient;

@Repository
public class CustomerAccountDaoImpl implements CustomerAccountDao {
	
	@Autowired
	private ApplicationData applicationData;
	
	@Autowired
	private CustomerClient customerClient;
	

	@Override
	public boolean validateCustomer(AtmRequest atmRequest) {
		
		Optional<Customer> customer = findCustomer(atmRequest);
		
		int pinActual = customer.orElseThrow(() -> new ValidationException(ATMMachineErrorKeys.INVALID_CARD_ERROR.getErrorKey())).getAtmPin();
		
		return atmRequest.getAtmPin() == pinActual;
	}

	@Override
	public long fetchCustomerBalance(AtmRequest atmRequest) {
		
		Optional<Customer> customer = findCustomer(atmRequest);
		
		// TODO - change to custom exception
		return customer.orElseThrow(NullPointerException::new).getBalance();
	}


	@Override
	public void updateCustomerBalance(AtmRequest atmRequest, long customerBalanceNew) {
		
		customerClient.updateBalance(customerBalanceNew,atmRequest.getAtmCardNumber());
		
	
	}
	

	private Optional<Customer> findCustomer(AtmRequest atmRequest) {
		
		Customer customerToBeUpdated = new Customer();
		customerToBeUpdated.setAtmCardNumber(atmRequest.getAtmCardNumber());
		customerToBeUpdated.setAtmPin(atmRequest.getAtmPin());
		
		return customerClient.getCustomers().stream().
				filter(c -> c.equals(customerToBeUpdated)).findFirst();
	}

}
