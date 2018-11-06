package it.javaboss.pmknowledge.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.model.Document;
import it.javaboss.pmknowledge.model.KnowledgeArea;
import it.javaboss.pmknowledge.model.Process;
import it.javaboss.pmknowledge.model.ProcessGroup;
import it.javaboss.pmknowledge.repository.DocumentRepository;
import it.javaboss.pmknowledge.repository.KnowledgeAreaRepository;
import it.javaboss.pmknowledge.repository.ProcessGroupRepository;
import it.javaboss.pmknowledge.repository.ProcessRepository;

@Component
public class CachedProcessRepository implements ProcessService {
	
	private List<Process> processes;
	
	private List<Document> documents;
	
	private List<KnowledgeArea> knowledgeAreas;
	
	private List<ProcessGroup> processGroups;
	
	@Autowired
	KnowledgeAreaRepository knowledgeAreaRepository;
	
	@Autowired
	ProcessGroupRepository processGroupRepository;
	
	@Autowired
	DocumentRepository documentRepository;
	
	@Autowired
	ProcessRepository processRepository;

	
	
	@Override
	public List<Process> findAllProcesses() {
		if ( processes == null ) {
			processes = processRepository.findAll();
		}
		return processes;
	}

	@Override
	public List<KnowledgeArea> findAllKnowledgeAreas() {
		if ( knowledgeAreas == null ) {
			knowledgeAreas = knowledgeAreaRepository.findAll();
		}
		return knowledgeAreas;
	}

	@Override
	public List<Document> findAllDocuments() {
		if ( documents == null ) {
			documents = documentRepository.findAll();
		}
		return documents;
	}

	@Override
	public List<ProcessGroup> findAllProcessGroups() {
		if ( processGroups == null ) {
			processGroups = processGroupRepository.findAll();
		}
		return processGroups;
	}

	@Override
	public List<Process> findProcessByNameContains(String filter) {
		return processRepository.findByNameContains(filter);
	}

	@Override
	public List<Process> search(List<Long> processIds, List<Long> knowledgeAreaIds, List<Long> processGroupIds) {
		return processRepository.search(processIds, knowledgeAreaIds, processGroupIds);
	}

	@Override
	public List<ProcessGroup> findAllProcessGroupsByOrderByOrderAsc() {
		return processGroupRepository.findAllByOrderByOrderAsc();
	}

	@Override
	public List<KnowledgeArea> findKnowledgeAreasAllByOrderByOrderAsc() {
		return knowledgeAreaRepository.findAllByOrderByOrderAsc();
	}

	@Override
	public Optional<Process> findProcessById(Long id) {
		return processes.stream().filter( p -> p.getId().equals( id ) ).findFirst();
	}

	@Override
	public List<Process> findProcessWithInput(Long docId) {
		return processRepository.findByProcessInputs_Document_Id( docId );
	}

	@Override
	public List<Process> findProcessWithOutput(Long docId) {
		return processRepository.findByProcessOutputs_Document_Id( docId );
	}

	
	
}
