package com.daviddorado.springboot.form.app.editors;

import java.beans.PropertyEditorSupport;

public class NombreEditor extends PropertyEditorSupport {

	@Override
	public void setAsText(String text) throws IllegalArgumentException {		
		
		setValue(text.replace(text.charAt(0), Character.toUpperCase(text.charAt(0))).trim());
	}
	
	

}
