package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.Document;
import it.javaboss.pmknowledge.model.KnowledgeArea;
import it.javaboss.pmknowledge.model.ProcessGroup;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.repository.DocumentRepository;
import it.javaboss.pmknowledge.repository.KnowledgeAreaRepository;
import it.javaboss.pmknowledge.repository.ProcessGroupRepository;
import it.javaboss.pmknowledge.repository.ProcessRepository;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class MenuManagedBean implements Serializable {
	
	@Autowired
	KnowledgeAreaRepository knowledgeAreaRepository;
	
	@Autowired
	ProcessGroupRepository processGroupRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	ProcessRepository processRepository;
	
	private Long[] 	selectedKnowledgeAreaIds;
	private Long[]  selectedProcessGroupIds;
	private List<Long> 	selectedProcessIds;
	
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

	public List<Long> getSelectedProcessIds() {
		return selectedProcessIds;
	}
	public void setSelectedProcessIds(List<Long> selectedProcessIds) {
		this.selectedProcessIds = selectedProcessIds;
	}
	
	//------------------------------------------------------------------------------------
	
	public List<KnowledgeArea> getKnowledgeAreas() {
		return knowledgeAreaRepository.findAll();
	}
	
	public List<ProcessGroup> getProcessGroups() {
		return processGroupRepository.findAll();
	}
	
	public List<Document> getDocuments() {
		return documentRepository.findAll();
	}
	
	public List<Process> completeProcess(String filter) {
		return processRepository.findByNameContains(filter);
	}
	
	//------------------------------------------------------------------------------------

}
