package it.javaboss.pmknowledge.service;

import java.util.List;
import java.util.Optional;

import it.javaboss.pmknowledge.model.Document;
import it.javaboss.pmknowledge.model.KnowledgeArea;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.model.ProcessGroup;
import it.javaboss.pmknowledge.model.ProcessInput;
import it.javaboss.pmknowledge.model.ProcessOutput;

public interface ProcessService {
	public List<Process> findAllProcesses();
	public List<KnowledgeArea> findAllKnowledgeAreas();
	public List<Document> findAllDocuments();
	public List<ProcessGroup> findAllProcessGroups();
	public List<Process> findProcessByNameContains(String filter);
	public List<Process> search( List<Long> processIds, List<Long> knowledgeAreaIds, List<Long> processGroupIds );
	public List<ProcessGroup> findAllProcessGroupsByOrderByOrderAsc();
	public List<KnowledgeArea> findKnowledgeAreasAllByOrderByOrderAsc();
	public Optional<Process> findProcessById(Long id);
	public List<ProcessInput> findProcessInputByDocumentId(Long id);
	public List<ProcessOutput> findProcessOutputByDocumentId(Long docId);
}
