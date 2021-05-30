package com.atm.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import com.atm.constants.AtmConstants;
import com.atm.exception.ValidationException;

public class AtmPinValidator implements ConstraintValidator<AtmPin, Integer> {

	@Override
	public boolean isValid(Integer value, ConstraintValidatorContext context) {
		String pinStr = value + "";
		if (pinStr.length() != AtmConstants.CUSTOMER_ACCOUNT_PIN_LENGTH) {
			return false;
		} else {
			return true;
		}
	}
	@Override
    public void initialize(AtmPin constraintAnnotation) {

    }

}
