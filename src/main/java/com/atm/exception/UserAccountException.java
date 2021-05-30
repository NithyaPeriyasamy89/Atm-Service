package com.atm.exception;
public class UserAccountException extends ServiceException {

	private static final long serialVersionUID = 1L;
	
	public UserAccountException(String messageKey, Throwable t) {
		super(messageKey, t);
	}
	
	public UserAccountException(String messageKey,String message) {
		super(messageKey, null);
	}

}