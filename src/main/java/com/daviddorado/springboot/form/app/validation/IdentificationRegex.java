package com.daviddorado.springboot.form.app.validation;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

@Constraint(validatedBy = IdentificationRegexValidator.class)
@Retention(RUNTIME)
@Target({ FIELD, METHOD })
public @interface IdentificationRegex {
	
	String message() default "No cumple el patrón";

	Class<?>[] groups() default { };

	Class<? extends Payload>[] payload() default { };
}
