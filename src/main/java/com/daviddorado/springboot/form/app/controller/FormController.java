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
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import com.daviddorado.springboot.form.app.editors.NombreEditor;
import com.daviddorado.springboot.form.app.editors.PaisPropertyEditor;
import com.daviddorado.springboot.form.app.editors.RolePropertyEditor;
import com.daviddorado.springboot.form.app.models.domain.Pais;
import com.daviddorado.springboot.form.app.models.domain.Role;
import com.daviddorado.springboot.form.app.models.domain.Usuario;
import com.daviddorado.springboot.form.app.services.PaisService;
import com.daviddorado.springboot.form.app.services.RoleService;
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
	@Autowired
	private RoleService roleService;
	@Autowired
	private RolePropertyEditor rolePropertyEditor;

	@InitBinder
	public void initBinder(WebDataBinder binder) {
		binder.addValidators(validator);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		dateFormat.setLenient(false);
		// Si el segundo par??metro de new CustomDateEditor es true manda null si es
		// false se valida por fecha los vacios
		binder.registerCustomEditor(Date.class, "fechaNac", new CustomDateEditor(dateFormat, false));
		binder.registerCustomEditor(String.class, "nombre", new NombreEditor());
		binder.registerCustomEditor(String.class, "userName", new NombreEditor());
		binder.registerCustomEditor(Pais.class, "pais", paisPropertyEditor);
		binder.registerCustomEditor(Role.class, "roles", rolePropertyEditor);
	}

	@ModelAttribute("generos")
	public List<String> getGeneros() {
		return Arrays.asList("Mujer", "Hombre", "No binario");
	}

	@ModelAttribute("paises")
	public List<String> paises() {
		return Arrays.asList("Espa??a", "Portugal", "Francia", "Andorra");
	}

	@ModelAttribute("listaRoles")
	public List<Role> listarRoles() {
		return this.roleService.listRoles();
	}

	@ModelAttribute("rolesString")
	public List<String> rolesString() {
		return Arrays.asList("Usuario", "Admin", "Moderador");
	}

	@ModelAttribute("rolesMap")
	public Map<String, String> rolesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("US", "Usuario");
		paises.put("AD", "Admin");
		paises.put("MD", "Moderador");
		return paises;
	}

	@ModelAttribute("paisesMap")
	public Map<String, String> paisesMap() {
		Map<String, String> paises = new HashMap<>();
		paises.put("Es", "Espa??a");
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
		usuario.setHabilitar(true);
		usuario.setValorSecreto("Soy un valor secreto ****");
		usuario.setPais(new Pais(1, "ES", "Espa??a"));
		usuario.setRoles(Arrays.asList(new Role(1, "Administrador", "ROLE_ADMIN")));
		model.addAttribute("titulo", "Formulario prueba");
		model.addAttribute("usuario", usuario);
		return "form";
	}

	@GetMapping("/resultado")
	public String resultado(@SessionAttribute(name="usuario", required=false) Usuario usuario, Model model, SessionStatus status) {
		if(usuario==null) {
			return "redirect:/form";
		}
		model.addAttribute("titulo", "Resultado formulario");		
		status.setComplete();
		return "resultado";
	}

	/*
	 * Cuando se envian par??metros desde una vista de formulario el RequestParam lo
	 * envia desde el atributo name por lo que debe coincidir el nombre del
	 * par??metro con el nombre del atributo name del formulario
	 * 
	 * @RequestParam(name="nombre") donde nombre es el valor del atributo name ->
	 * Permite no usar el mismo nombre para el par??metro Cuando se valida el Pojo
	 * del formulario va como primer par??metro y el BindingResult como segundo
	 * 
	 * @ModelAttribute permite cambiar el nombre del objeto para el formulario
	 */
	// Params eliminados @RequestParam String username, @RequestParam String
	// password, @RequestParam String email
	@PostMapping("/form")
	public String procesar(@Valid Usuario usuario, BindingResult validationResult, Model model) {
		// validator.validate(usuario, validationResult);		
		if (validationResult.hasErrors()) {
			/*
			 * Map<String, String> errores = new HashMap<>();
			 * validationResult.getFieldErrors().forEach(error -> {
			 * errores.put(error.getField(),
			 * "El campo ".concat(error.getField()).concat(" ").concat(error.
			 * getDefaultMessage())); }); model.addAttribute("error",errores);
			 */
			model.addAttribute("titulo", "Resultado formulario");
			return "form";
		}
		/*
		 * Usuario usuario = new Usuario(); usuario.setUsername(username);
		 * usuario.setPassword(password); usuario.setEmail(email);
		 */

		// model.addAttribute("usuario", usuario);

		/*
		 * model.addAttribute("username", username); model.addAttribute("password",
		 * password); model.addAttribute("email", email);
		 */
		return "redirect:/resultado";
	}
}
