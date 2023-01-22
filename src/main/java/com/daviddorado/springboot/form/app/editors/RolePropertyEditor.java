package com.daviddorado.springboot.form.app.editors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.PropertiesEditor;
import org.springframework.stereotype.Component;

import com.daviddorado.springboot.form.app.services.RoleService;
@Component
public class RolePropertyEditor extends PropertiesEditor {
	
	@Autowired
	private RoleService roleService;
	@Override
	public void setAsText(String text) throws IllegalArgumentException {
		if(text.length() <=0 || text == null) {
			this.setValue(null);
			return;
		}
		try {
		this.setValue(roleService.getById(Integer.parseInt(text)));
		} catch (Exception e) {			
			this.setValue(null);
		}
	}

	

}
