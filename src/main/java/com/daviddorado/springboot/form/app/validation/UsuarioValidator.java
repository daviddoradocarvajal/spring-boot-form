package com.daviddorado.springboot.form.app.validation;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.daviddorado.springboot.form.app.models.domain.Usuario;
@Component
public class UsuarioValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// Comprobar que el tipo que se está intentando validar corresponde con la clase soportada por este validador
		return Usuario.class.isAssignableFrom(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {
		if(target instanceof Usuario) {
			Usuario usuario = (Usuario) target;
			
			ValidationUtils.rejectIfEmptyOrWhitespace(errors, "nombre", "NotEmpty.usuario.nombre");
			
			if(!usuario.getIdentificador().matches("[0-9]{3}[.,][\\d]{3}[.,][\\d]{3}[-][A-Z]{1}")) {
				errors.rejectValue("identificador", "Pattern.usuario.identificador");
			}
		}else {
			System.out.println("Not an user");
			errors.reject("NotUser", "Not an User");
		}
			
		

	}

}
