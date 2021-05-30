package com.atm.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.atm.helper.ErrorMessageProvider;
import com.atm.response.ATMMachineRequestResult;
import com.atm.response.ServiceError;

@Service
public abstract class AbstractService {
	
	@Autowired
	private ErrorMessageProvider errorMessagesProvider;
	

	public ATMMachineRequestResult prepareErrorResponse(String errorCode) {
		
		ATMMachineRequestResult result = new ATMMachineRequestResult();
		String errorMessageForKey = errorMessagesProvider.getErrorMessageForKey(errorCode);
		ServiceError serviceError = new ServiceError(errorCode, errorMessageForKey);
		result.setServiceError(serviceError);
		
		return result;
	}
}