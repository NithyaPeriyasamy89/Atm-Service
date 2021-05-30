package com.atm.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.atm.model.AtmRequest;
import com.atm.response.ATMMachineRequestResult;
import com.atm.service.PostATMMachineService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@Api(value="ATM Service", description="Withdrawing money from ATM")
public class AtmController {
	
	@Autowired
	PostATMMachineService postRequestService;
	
	@ApiOperation(value = "Withdraw money from ATM", response = ATMMachineRequestResult.class)
	@PostMapping("/atm/transaction")
	public ResponseEntity<ATMMachineRequestResult> processAtmTxnRequest(@RequestBody @Valid AtmRequest request) {
		System.out.println("Enter postWithdrawMoney()");
		ATMMachineRequestResult requestResult = postRequestService.validateUserDetails(request);
		System.out.println("Leaving postWithdrawMoney()");
		return ResponseEntity.ok(requestResult);
		
	}

}
