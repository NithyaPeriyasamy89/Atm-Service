package com.atm.validator;

import java.lang.annotation.Documented;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.annotation.ElementType;
import javax.validation.Constraint;
import javax.validation.Payload;


@Retention(value= RetentionPolicy.RUNTIME)
@Inherited
@Documented
@Constraint(validatedBy=AtmPinValidator.class)
@Target({ ElementType.FIELD })
public @interface AtmPin {
	
	String message() default "{Atm pin should be 4 digits}";
	Class<?>[] groups() default {};
	  Class<? extends Payload>[] payload() default {};

}
