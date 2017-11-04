package com.easyrestaurant.validation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.OverridesAttribute;
import javax.validation.Payload;
import javax.validation.constraints.Pattern;

import com.easyrestaurant.validation.validator.ConfirmacaoSenhaValidator;

@Target({ ElementType.TYPE })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = { ConfirmacaoSenhaValidator.class })
public @interface ConfirmacaoSenha {

	@OverridesAttribute(constraint = Pattern.class, name = "message")
	String message() default "Confirmação senha não confere.";
	
	Class<?>[] groups() default {};
	
	Class<? extends Payload>[] payload() default {};
	
	String atributo();
	
	String atributoConfirmacao();
	
}