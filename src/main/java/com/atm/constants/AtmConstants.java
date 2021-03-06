package com.atm.constants;

public class AtmConstants {
	

	public static final int CUSTOMER_ACCOUNT_NUMBER_LENGTH = 9;
	
	 public static final int CUSTOMER_ACCOUNT_PIN_LENGTH = 4;
	 
	 public enum ATMMachineErrorKeys {

			ATM_INSUFFICIENT_BALANCE("101"),
			ATM_OUT_OF_SERVICE("102"),
			ATM_MANAGED_ERROR("103"),
			BAD_REQUEST("201"),
			AUTHENTICATION_ERROR("202"),
			INVALID_CARD_ERROR("204"),
			BAD_REQUEST_INVALID_AMOUNT("203"),
			CUSTOMER_INSUFFICIENT_BALANCE("301"),
			CUSTOMER_DAILY_LIMIT_WITHDRAWAL_REACHED("302");
			
			private String errorKey;

			private ATMMachineErrorKeys(String errorKey) {
				this.errorKey = errorKey;
			}

			public String getErrorKey() {
				return errorKey;
			}
	 }

}
