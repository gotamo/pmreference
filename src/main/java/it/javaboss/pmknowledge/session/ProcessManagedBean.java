package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.service.ProcessService;
import it.javaboss.pmknowledge.model.Process;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class ProcessManagedBean implements Serializable {
	@Autowired
	ProcessService processService;
	
	@Autowired
	ShowInfoManagedBean showInfoManagedBean;
	
	public List<Process> getProcessProducingDocument() {
		return processService.findProcessWithOutput( showInfoManagedBean.getInfoId() );
	}
	
	public List<Process> getProcessConsumingDocument() {
		return processService.findProcessWithInput( showInfoManagedBean.getInfoId() );
	}
}
