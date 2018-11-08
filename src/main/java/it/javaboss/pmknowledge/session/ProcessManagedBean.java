package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.Document;
import it.javaboss.pmknowledge.model.ProcessInput;
import it.javaboss.pmknowledge.model.ProcessOutput;
import it.javaboss.pmknowledge.service.ProcessService;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class ProcessManagedBean implements Serializable {
	@Autowired
	ProcessService processService;
	
	@Autowired
	ShowInfoManagedBean showInfoManagedBean;
	
	public List<ProcessOutput> getProcessProducingDocument() {
		List<ProcessOutput> processOutputs = processService.findProcessOutputByDocumentId( showInfoManagedBean.getInfoId() );
		
		if ( processOutputs.isEmpty() ) {
			Document doc = processService.findDocumentById( showInfoManagedBean.getInfoId() );
			if ( doc.getParent() != null ) {
				processOutputs = processService.findProcessOutputByDocumentId( doc.getParent().getId() );
			}
		}
		
		return processOutputs;
	}
	
	public List<ProcessInput> getProcessConsumingDocument() {
		return processService.findProcessInputByDocumentId( showInfoManagedBean.getInfoId() );
	}
}
