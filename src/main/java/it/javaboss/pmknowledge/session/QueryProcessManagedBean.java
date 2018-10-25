package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.KnowledgeArea;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.service.ProcessService;
import it.javaboss.pmknowledge.utils.ArrayUtils;
import it.javaboss.pmknowledge.utils.ListUtils;

@SuppressWarnings("serial")
@Component
@ManagedBean
@SessionScoped
public class QueryProcessManagedBean implements Serializable {

	@Autowired 
	MenuManagedBean menuManagedBean;
	
	@Autowired
	ProcessService processService;

	private Map<KnowledgeArea, List<Process>> processesByKa = null;
	private List<Process> processes;
	
	//-----------------------------------------------------------------------------------------

	public List<Process> getProcesses() {
		return processes;
	}
	
	public List<Entry<KnowledgeArea, List<Process>>> getProcessesByKa() {
		List<Map.Entry<KnowledgeArea, List<Process>>> list = new ArrayList<>();
	    
		list.addAll(processesByKa.entrySet());
		
	    return list;
	}
	
	public Set<KnowledgeArea> getFilteredKA() {
		return this.processesByKa.keySet();
	}
	
	//-----------------------------------------------------------------------------------------	
	
	public void clear() {
		this.menuManagedBean.setSelectedKnowledgeAreaIds( null );
		this.menuManagedBean.setSelectedProcesses( null );
		this.menuManagedBean.setSelectedProcessGroupIds( null );
		refresh();
	}
	
	//-----------------------------------------------------------------------------------------	

	public void refresh() {
		this.processes = null;
		getFilteredProcesses();

		this.processesByKa = null;
		getFilteredProcessesByKA();
	}
	
	//-----------------------------------------------------------------------------------------	

	public List<Process> getFilteredProcesses() {
		if ( this.processes == null ) {
			if ( ArrayUtils.isNotEmpty( menuManagedBean.getSelectedKnowledgeAreaIds() )
					|| ArrayUtils.isNotEmpty( menuManagedBean.getSelectedProcessGroupIds() ) 
					|| ListUtils.isNotEmpty( menuManagedBean.getSelectedProcesses() ) ) {
					
				try {
					this.processes = processService.search( 
								Arrays.asList( ArrayUtils.asListOfId( menuManagedBean.getSelectedProcesses() ) ), 
								Arrays.asList( menuManagedBean.getSelectedKnowledgeAreaIds() ), 
								Arrays.asList( menuManagedBean.getSelectedProcessGroupIds() ) );
				} catch (Exception e) {
					e.printStackTrace();
					this.processes = null;
				}
			} else {
				this.processes = processService.findAllProcesses();
			}
		}
		return this.processes;
	}
	
	//-----------------------------------------------------------------------------------------

	public Map<KnowledgeArea, List<Process>> getFilteredProcessesByKA() {
		if ( this.processesByKa == null) {
			
			this.processesByKa = new HashMap<>();

			List<Process> processes = getFilteredProcesses();
			
			if ( this.processes != null ) {
				for ( Process process : processes ) {
					List<Process> list = processesByKa.get(process.getKnowledgeArea());
					if (list == null) {
						list = new ArrayList<Process>();
						processesByKa.put( process.getKnowledgeArea(), list );
					}
					list.add(process);
				}
			}
		}
		return processesByKa;
	}
	
}
