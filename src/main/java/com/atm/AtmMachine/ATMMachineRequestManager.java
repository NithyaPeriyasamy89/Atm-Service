package com.atm.AtmMachine;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.atm.constants.AtmConstants.ATMMachineErrorKeys;
import com.atm.dao.ATMMachineDao;
import com.atm.dao.CustomerAccountDao;
import com.atm.exception.ATMMachineException;
import com.atm.exception.UserAccountException;
import com.atm.exception.ValidationException;
import com.atm.model.AtmRequest;
import com.atm.model.Denomination;
import com.atm.response.ATMMachineRequestResult;
import com.atm.response.CustomerResult;

@Component
public class ATMMachineRequestManager {
	@Autowired
	private CustomerAccountDao customerAccountDao;
	@Autowired
	private ATMMachineDao atmMachineDao;
	

	public ATMMachineRequestResult fetchCustomerBalance(AtmRequest atmRequest) {
		
		long balance = customerAccountDao.fetchCustomerBalance(atmRequest);
		
		ATMMachineRequestResult requestResult = new ATMMachineRequestResult();
		CustomerResult customerResult = new CustomerResult();
		customerResult.setBalance(balance);
		requestResult.setCustomerResult(customerResult);
		
		return requestResult;
	}

	public ATMMachineRequestResult withdrawMoney(AtmRequest atmRequest) {
		
		long customerBalance = 0;
		long customerBalanceNew = 0;
		ATMMachineRequestResult requestResult = null;
		long atmBalance = atmMachineDao.fethcATMBalance();

		if (atmBalance == 0) {
			throw new ATMMachineException(ATMMachineErrorKeys.ATM_OUT_OF_SERVICE.getErrorKey());
		}
		
		boolean isRequestedAmountValid =atmMachineDao.validateWithdrawalAmount(atmRequest.getAmount());
		if (!isRequestedAmountValid) {
			throw new ValidationException(ATMMachineErrorKeys.BAD_REQUEST_INVALID_AMOUNT.getErrorKey());
		}
		
		customerBalance = customerAccountDao.fetchCustomerBalance(atmRequest);
		
		if (atmRequest.getAmount() > customerBalance) {
			
				throw new UserAccountException(ATMMachineErrorKeys.CUSTOMER_INSUFFICIENT_BALANCE.getErrorKey(),"Insuficient Balance");
		
		} else {
			customerBalanceNew = customerBalance - atmRequest.getAmount();
		}
		
		if (atmRequest.getAmount() > atmBalance) {
			throw new ATMMachineException(ATMMachineErrorKeys.ATM_INSUFFICIENT_BALANCE.getErrorKey());
		}
		
		List<Denomination> denominationResult = atmMachineDao.dispenseMoney(atmRequest.getAmount());
		
		try {
			customerAccountDao.updateCustomerBalance(atmRequest, customerBalanceNew);
			
		} catch (Exception e) {
			e.printStackTrace();
			
			//  Revert the ATMBalance and Denomination repository.
			throw new ATMMachineException(ATMMachineErrorKeys.ATM_MANAGED_ERROR.getErrorKey());
		}
		
		requestResult = new ATMMachineRequestResult();
		CustomerResult customerResult = new CustomerResult();
		customerResult.setBalance(customerBalanceNew);
		customerResult.setWithdrawnAmount(atmRequest.getAmount()+"");
		customerResult.setDenominationResult(denominationResult);
		requestResult.setCustomerResult(customerResult);
		
		return requestResult;
	}

}