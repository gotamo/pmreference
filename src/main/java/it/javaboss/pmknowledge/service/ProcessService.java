package it.javaboss.pmknowledge.service;

import java.util.Optional;

import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import it.javaboss.pmknowledge.repository.ProcessRepository;
import it.javaboss.pmknowledge.model.Process;

@Component
@ManagedBean
@ApplicationScoped
public class ProcessService {
	
	@Autowired
	ProcessRepository processRepository;
	
	public Optional<Process> findById(Long id) {
		return processRepository.findById(id);
	}

}
