package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.HashBasedTable;
import com.google.common.collect.Table;

import it.javaboss.pmknowledge.model.KnowledgeArea;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.model.ProcessGroup;
import it.javaboss.pmknowledge.service.ProcessService;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class ProcessTableManagedBean implements Serializable {
	
	private Table<Long, Long, List<Process>> processTable;
	
	private List<ProcessGroup> processGroups;
	
	private List<KnowledgeArea> knowledgeAreas;
	
	@Autowired
	ProcessService processService;
	
	//------------------------------------------------------------------------------
	
	@PostConstruct
	private void init() {
		processGroups = processService.findAllProcessGroupsByOrderByOrderAsc();
		knowledgeAreas = processService.findKnowledgeAreasAllByOrderByOrderAsc();
		
		processTable = HashBasedTable.create();
		List<Process> processes = processService.findAllProcesses();
		
		List<Process> list = null;
		for ( Process process : processes ) {
			list = processTable.get( process.getKnowledgeArea().getId(), process.getProcessGroup().getId() );
			
			if ( list == null ) {
				list = new ArrayList<Process>();
				processTable.put( process.getKnowledgeArea().getId(), process.getProcessGroup().getId(), list );
			}
			
			list.add( process );
		}
	}
	
	//------------------------------------------------------------------------------
	
	public List<KnowledgeArea> getKnowledgeAreas() {
		return knowledgeAreas;
	}
	
	public List<ProcessGroup> getProcessGroups() {
		return processGroups;
	}
	
	public List<Process> getProcesses( KnowledgeArea ka, ProcessGroup pg ) {
		return processTable.get( ka.getId(), pg.getId() );
	}
}
