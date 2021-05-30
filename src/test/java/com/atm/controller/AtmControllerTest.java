package com.atm.controller;


import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.ResponseEntity;

import com.atm.model.AtmRequest;
import com.atm.response.ATMMachineRequestResult;
import com.atm.response.CustomerResult;
import com.atm.service.PostATMMachineService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest(classes=AtmController.class)
public class AtmControllerTest {
	
	@InjectMocks
	private AtmController atmController;
	
	@MockBean
	PostATMMachineService postRequestService;
	
	@Test
	public void processAtmTxnRequestTest() {
		Mockito.when(postRequestService.validateUserDetails(Mockito.any())).thenReturn(getResult());
		ResponseEntity<ATMMachineRequestResult> actual =atmController.processAtmTxnRequest(input());
		Assert.assertEquals(actual.getBody().getCustomerResult().getWithdrawnAmount(),"100");
		
	}
	private ATMMachineRequestResult getResult() {
		ATMMachineRequestResult result = new ATMMachineRequestResult();
		CustomerResult customerResult = new CustomerResult();
		customerResult.setAtmCardNumber("1234567890123456");
		customerResult.setAtmPin(1234);
		customerResult.setBalance(200);
		customerResult.setWithdrawnAmount("100");
		result.setCustomerResult(customerResult);
		return result;
	}
	
	private AtmRequest input() {
		AtmRequest req = new AtmRequest();
		req.setAmount(100);
		req.setAtmCardNumber("1234567890123456");
		req.setAtmPin(1234);
		return req;
		
	}
	

}
