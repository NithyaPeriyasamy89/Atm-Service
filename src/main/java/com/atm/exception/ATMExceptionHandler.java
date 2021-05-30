package com.atm.exception;

import java.time.LocalDate;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.atm.response.ATMMachineRequestResult;
import com.atm.service.PostATMMachineService;
import com.fasterxml.jackson.databind.JsonMappingException;

@ControllerAdvice
public class ATMExceptionHandler extends ResponseEntityExceptionHandler{
	@Autowired
	private PostATMMachineService abstractService;
	
	 @Override
	    protected ResponseEntity<Object> handleMethodArgumentNotValid(
	        MethodArgumentNotValidException ex, HttpHeaders headers, 
	        HttpStatus status, WebRequest request) {

	        Map<String, Object> body = new LinkedHashMap<>();
	        body.put("timestamp", LocalDate.now());
	        body.put("status", status.value());

	        List<String> errors = ex.getBindingResult()
	                .getFieldErrors()
	                .stream()
	                .map(x -> x.getDefaultMessage())
	                .collect(Collectors.toList());

	        body.put("errors", errors);

	        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
	    }
	 
	 @ExceptionHandler
     @ResponseStatus(HttpStatus.BAD_REQUEST)
     public ResponseEntity<Object> handleJsonMappingException(JsonMappingException ex) {
         return new ResponseEntity<>("invalid request", HttpStatus.BAD_REQUEST);
     } 
	 @ExceptionHandler
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public ResponseEntity<Object> handleUserAccountException(UserAccountException ex) {
		 ATMMachineRequestResult requestResult = abstractService.prepareErrorResponse(ex.getMessage());
		 return new ResponseEntity<>(requestResult, HttpStatus.BAD_REQUEST);
	 } 
	 
	 @ExceptionHandler
	 @ResponseStatus(HttpStatus.BAD_REQUEST)
	 public ResponseEntity<Object> handleValidationException(ValidationException ex) {
		 ATMMachineRequestResult requestResult = abstractService.prepareErrorResponse(ex.getMessage());
		 return new ResponseEntity<>(requestResult, HttpStatus.BAD_REQUEST);
	 } 

}
