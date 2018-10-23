package it.javaboss.pmknowledge.session;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.repository.ProcessRepository;
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
	ProcessRepository processRepository;
	
	public List<Process> getFilteredProcesses() {
		if ( ArrayUtils.isNotEmpty( menuManagedBean.getSelectedKnowledgeAreaIds() )
				|| ArrayUtils.isNotEmpty( menuManagedBean.getSelectedProcessGroupIds() ) 
				|| ListUtils.isNotEmpty( menuManagedBean.getSelectedProcessIds() ) ) {
				
			try {
				return processRepository.search( 
						Arrays.asList( ArrayUtils.asListOfLong( menuManagedBean.getSelectedProcessIds() ) ), 
						Arrays.asList( menuManagedBean.getSelectedKnowledgeAreaIds() ), 
						Arrays.asList( menuManagedBean.getSelectedProcessGroupIds() ) );
			} catch (Exception e) {
				e.printStackTrace();
			}
			return null;
		} else {
			return processRepository.findAll();
		}
	}
	
}
