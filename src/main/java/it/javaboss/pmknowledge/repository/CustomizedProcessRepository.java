package it.javaboss.pmknowledge.repository;

import java.util.List;

import it.javaboss.pmknowledge.model.Process;

public interface CustomizedProcessRepository {
	
	public List<Process> search( 
			List<Long> processIds,
			List<Long> knowledgeAreaIds,
			List<Long> processGroupIds);
	
}
