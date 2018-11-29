package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class ShowInfoManagedBean implements Serializable {
	
	private static Map<String,Object> options = new HashMap<String, Object>();
	static {
		 options.put("modal", true);
		 options.put("width", "60%");
	     options.put("height", "400");
	     options.put("contentWidth", "100%");
	     options.put("contentHeight", "100%");
	     options.put("headerElement", "customheader");
	}
	
	@Value("${dialog.document.path}")
	private String documentPath; 
	
	@Value("${dialog.tool.path}")
	private String toolPath; 
	
	private String infoToShow;
	
	private Long infoId;

	//---------------------------------------------------------------------------------------------
	
	public void setInfoToShow(String infoToShow) {
		this.infoToShow = infoToShow;
	}
	public String getInfoToShow() {
		return infoToShow;
	}
	
	public Long getInfoId() {
		return infoId;
	}
	public void setInfoId(Long infoId) {
		this.infoId = infoId;
	}

	//---------------------------------------------------------------------------------------------
	
	private String buildFileName(String path) throws MalformedURLException {
		String browserLocale = FacesContext.getCurrentInstance().getViewRoot().getLocale().getLanguage();

		String fullPath = path + infoToShow + "_" + browserLocale + ".xhtml";
		
		URL resource = FacesContext.getCurrentInstance().getExternalContext().getResource(fullPath);
		
		if ( resource == null ) {
			fullPath = path + infoToShow + "_it" + ".xhtml";
		}
		
		return fullPath;
	}
	
	public void showDocument() throws MalformedURLException {
        String fileName = buildFileName(documentPath);
        
        System.out.println("ShowInfoManagedBean.show(): " + fileName );
        
		PrimeFaces.current().dialog().openDynamic( fileName, options, null);
	}
	
	public void showTool() throws MalformedURLException {
		String fileName = buildFileName(toolPath);
        
        System.out.println("ShowInfoManagedBean.show(): " + fileName );
        
		PrimeFaces.current().dialog().openDynamic( fileName, options, null);
	}
}
