package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.Locale;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

@SuppressWarnings("serial")
@ManagedBean(name="language")
@SessionScoped
public class LanguageManagedBean implements Serializable {

	public Locale browserLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale();
	
	public String getLocaleExtetion() {
		return browserLocale.getLanguage();
	}
}
