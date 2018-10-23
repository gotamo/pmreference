package it.javaboss.pmknowledge.session;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.ApplicationScope;

@SuppressWarnings("serial")
@Component
@ManagedBean
@ApplicationScope
public class ApplicationManagedBean implements Serializable {

	@Value( "${edit.enabled}" )
	private String editEnabled;

	public String getEditEnabled() {
		return editEnabled;
	}
	
}
