package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.primefaces.PrimeFaces;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.Process;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class ShowIttoManagedBean implements Serializable {
	
	private static Map<String,Object> options = new HashMap<String, Object>();
	static {
		 options.put("modal", false);
		 options.put("width", "70%");
	     options.put("height", "450");
	     options.put("contentWidth", "100%");
	     options.put("contentHeight", "100%");
	     options.put("headerElement", "customheader");
	}
	
	@Value("${dialog.itto.path}")
	private String ittoPath; 
	
	private Process selectedProcess;
	
	//------------------------------------------------------------------------------

	public Process getSelectedProcess() {
		return selectedProcess;
	}
	public void setSelectedProcess(Process selectedProcess) {
		this.selectedProcess = selectedProcess;
	}	
	
	//------------------------------------------------------------------------------
	
	public void show() {
        
        System.out.println("ShowIttoManagedBean.show(): " + selectedProcess.getName() );
        
		PrimeFaces.current().dialog().openDynamic( ittoPath + "dialog-itto.xhtml", options, null);
	}
}
