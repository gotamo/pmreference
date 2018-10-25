package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.Document;
import it.javaboss.pmknowledge.model.KnowledgeArea;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.model.ProcessGroup;
import it.javaboss.pmknowledge.service.ProcessService;

@SuppressWarnings("serial")
@Component
@ManagedBean
@ViewScoped
public class MenuManagedBean implements Serializable {
	
	@Autowired
	ProcessService processService;
		
	private Long[] 	selectedKnowledgeAreaIds;
	private Long[]  selectedProcessGroupIds;
	private List<Process> 	selectedProcesses;
	
	//------------------------------------------------------------------------------------
	
	public Long[] getSelectedKnowledgeAreaIds() {
		return selectedKnowledgeAreaIds;
	}
	public void setSelectedKnowledgeAreaIds(Long[] selectedKnowledgeArea) {
		this.selectedKnowledgeAreaIds = selectedKnowledgeArea;
	}
	
	//------------------------------------------------------------------------------------
	
	public Long[] getSelectedProcessGroupIds() {
		return selectedProcessGroupIds;
	}
	public void setSelectedProcessGroupIds(Long[] selectedProcessGroup) {
		this.selectedProcessGroupIds = selectedProcessGroup;
	}
	
	//------------------------------------------------------------------------------------

	public List<Process> getSelectedProcesses() {
		return selectedProcesses;
	}
	public void setSelectedProcesses(List<Process> selectedProcessIds) {
		this.selectedProcesses = selectedProcessIds;
	}
	
	//------------------------------------------------------------------------------------
	
	public List<KnowledgeArea> getKnowledgeAreas() {
		return processService.findAllKnowledgeAreas();
	}
	
	public List<ProcessGroup> getProcessGroups() {
		return processService.findAllProcessGroups();
	}
	
	public List<Process> getProcesses() {
		return processService.findAllProcesses();
	}
	
	public List<Document> getDocuments() {
		return processService.findAllDocuments();
	}
	
	public List<Process> completeProcess(String filter) {
		return processService.findProcessByNameContains(filter);
	}
}
