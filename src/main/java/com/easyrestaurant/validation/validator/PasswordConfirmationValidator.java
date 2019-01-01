package com.easyrestaurant.validation.validator;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import javax.validation.ConstraintValidatorContext.ConstraintViolationBuilder;

import org.apache.commons.beanutils.BeanUtils;

import com.easyrestaurant.validation.PasswordConfirmation;

public class PasswordConfirmationValidator implements ConstraintValidator<PasswordConfirmation, Object> {

	private String password;
	private String passwordConfirmation;
	
	@Override
	public void initialize(PasswordConfirmation constraintAnnotation) {
		this.password = constraintAnnotation.password();
		this.passwordConfirmation = constraintAnnotation.passwordConfirmation();
	}

	@Override
	public boolean isValid(Object object, ConstraintValidatorContext context) {
		
		if (isInvalid(object, context)) {
			context.disableDefaultConstraintViolation();
			String message = context.getDefaultConstraintMessageTemplate();
			ConstraintViolationBuilder violationBuilder = context.buildConstraintViolationWithTemplate(message);
			violationBuilder.addPropertyNode(this.passwordConfirmation).addConstraintViolation();
			return false;
		}
		
		return true;
	}
	
	private boolean isInvalid(Object object, ConstraintValidatorContext context) {
		try {
			Object passwordValue = BeanUtils.getProperty(object, this.password);
			Object passwordConfirmationValue = BeanUtils.getProperty(object, this.passwordConfirmation);
			
			if(passwordValue == null && passwordConfirmationValue == null) {
				return false;
			}
			
			return !isEquals(passwordValue, passwordConfirmationValue);
		
		} catch (Exception e) {
			throw new RuntimeException("Error retrieving password values", e);
		}
	}

	private boolean isEquals(Object password, Object passwordConfirmation) {
		return password != null && password.equals(passwordConfirmation);
	}

}
