package com.daviddorado.springboot.form.app.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.daviddorado.springboot.form.app.models.domain.Usuario;

import jakarta.validation.Valid;

@Controller
public class FormController {

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("John");
		usuario.setApellido("Doe");
		usuario.setIdentificador("123456789-L");
		model.addAttribute("titulo", "Formulario prueba");
		model.addAttribute("usuario",usuario);
		return "form";
	}

	/*
	 * Cuando se envian parámetros desde una vista de formulario el RequestParam lo
	 * envia desde el atributo name por lo que debe coincidir el nombre del
	 * parámetro con el nombre del atributo name del formulario
	 * 
	 * @RequestParam(name="nombre") donde nombre es el valor del atributo name -> Permite no usar el mismo nombre para el parámetro
	 * Cuando se valida el Pojo del formulario va como primer parámetro y el BindingResult como segundo
	 * @ModelAttribute permite cambiar el nombre del objeto para el formulario 
	 */
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult validationResult ,Model model) // , @RequestParam String username, @RequestParam String
															// password, @RequestParam String email)
	{
		model.addAttribute("titulo", "Resultado formulario");
		if (validationResult.hasErrors()) {
			/*Map<String, String> errores = new HashMap<>();
			validationResult.getFieldErrors().forEach(error -> {
				errores.put(error.getField(), "El campo ".concat(error.getField()).concat(" ").concat(error.getDefaultMessage()));
			});
			model.addAttribute("error",errores);*/
			return "form";
		}
		/*
		 * Usuario usuario = new Usuario(); 
		 * usuario.setUsername(username);
		 * usuario.setPassword(password); 
		 * usuario.setEmail(email);
		 */
		
		
		model.addAttribute("usuario", usuario);
		/*
		 * model.addAttribute("username", username); 
		 * model.addAttribute("password", password); 
		 * model.addAttribute("email", email);
		 */
		return "resultado";
	}
}
