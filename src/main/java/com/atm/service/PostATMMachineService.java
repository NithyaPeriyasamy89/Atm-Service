package com.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.AtmMachine.ATMMachineRequestManager;
import com.atm.constants.AtmConstants.ATMMachineErrorKeys;
import com.atm.dao.CustomerAccountDao;
import com.atm.exception.ValidationException;
import com.atm.model.AtmRequest;
import com.atm.response.ATMMachineRequestResult;
import com.atm.response.ServiceError;

@Service
public class PostATMMachineService extends AbstractService {
	
	@Autowired
	CustomerAccountDao customerAccountDao;
	
	@Autowired
	ATMMachineRequestManager atmMachineRequestManager;

	public ATMMachineRequestResult validateUserDetails(AtmRequest atmRequest) {

		System.out.println("In service execution");
		ATMMachineRequestResult requestResult = null;

		Boolean validCustomer = customerAccountDao.validateCustomer(atmRequest);
		if (validCustomer)
			requestResult = atmMachineRequestManager.withdrawMoney(atmRequest);
		else {
			throw new ValidationException(ATMMachineErrorKeys.AUTHENTICATION_ERROR.getErrorKey());

		}

		System.out.println("Leaving service execution");

		return requestResult;
	}

}
