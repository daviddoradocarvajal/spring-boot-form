package com.daviddorado.springboot.form.app.controller;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.daviddorado.springboot.form.app.editors.NombreEditor;
import com.daviddorado.springboot.form.app.editors.PaisPropertyEditor;
import com.daviddorado.springboot.form.app.models.domain.Pais;
import com.daviddorado.springboot.form.app.models.domain.Usuario;
import com.daviddorado.springboot.form.app.services.PaisService;
import com.daviddorado.springboot.form.app.validation.UsuarioValidator;

import jakarta.validation.Valid;

//SessionAttributes -> Mantiene los datos del model durante la sesion HTTP y no los elimina (para persistir id sin mostrar en un formulario)
@Controller
@SessionAttributes("usuario")
public class FormController {
	@Autowired
	private UsuarioValidator validator;
	@Autowired
	private PaisService paisService;
	@Autowired
	private PaisPropertyEditor paisPropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		// Si el segundo parámetro de new CustomDateEditor es true manda null si es
		// false se valida por fecha los vacios
		binder.registerCustomEditor(Date.class, "fechaNac", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, "nombre", new NombreEditor());
		binder.registerCustomEditor(String.class, "userName", new NombreEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("España", "Portugal", "Francia", "Andorra");
	}
	
	@ModelAttribute("rolesString")
	public List<String> rolesString() {
		return Arrays.asList("Usuario", "Admin", "Moderador");
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("Es", "España");
		paises.put("Pt", "Portugal");
		paises.put("Fr", "Francia");
		paises.put("An", "Andorra");
		return paises;
	}

	@ModelAttribute("listaPaises")
	public List<Pais> listaPaises() {
		return paisService.listar();
	}

	@GetMapping("/form")
	public String form(Model model) {
		Usuario usuario = new Usuario();
		usuario.setNombre("john");
		usuario.setApellido("doe");
		usuario.setIdentificador("123.456.789-L");
		model.addAttribute("titulo", "Formulario prueba");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	/*
	 * Cuando se envian parámetros desde una vista de formulario el RequestParam lo
	 * envia desde el atributo name por lo que debe coincidir el nombre del
	 * parámetro con el nombre del atributo name del formulario
	 * 
	 * @RequestParam(name="nombre") donde nombre es el valor del atributo name ->
	 * Permite no usar el mismo nombre para el parámetro Cuando se valida el Pojo
	 * del formulario va como primer parámetro y el BindingResult como segundo
	 * 
	 * @ModelAttribute permite cambiar el nombre del objeto para el formulario
	 */
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult validationResult, Model model, SessionStatus status) // ,
																														// @RequestParam
																														// String
																														// username,
																														// @RequestParam
																														// String
	// password, @RequestParam String email)
	{
		// validator.validate(usuario, validationResult);
		model.addAttribute("titulo", "Resultado formulario");
		if (validationResult.hasErrors()) {
			/*
			 * Map<String, String> errores = new HashMap<>();
			 * validationResult.getFieldErrors().forEach(error -> {
			 * errores.put(error.getField(),
			 * "El campo ".concat(error.getField()).concat(" ").concat(error.
			 * getDefaultMessage())); }); model.addAttribute("error",errores);
			 */
			return "form";
		}
		/*
		 * Usuario usuario = new Usuario(); usuario.setUsername(username);
		 * usuario.setPassword(password); usuario.setEmail(email);
		 */

		model.addAttribute("usuario", usuario);
		status.setComplete();
		/*
		 * model.addAttribute("username", username); model.addAttribute("password",
		 * password); model.addAttribute("email", email);
		 */
		return "resultado";
	}
}
