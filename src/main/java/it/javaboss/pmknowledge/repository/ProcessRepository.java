package it.javaboss.pmknowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.javaboss.pmknowledge.model.Process;

@Repository
public interface ProcessRepository extends JpaRepository<Process,Long>, CustomizedProcessRepository{
	List<Process> findByNameContains(String name);
}
