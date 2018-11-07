package it.javaboss.pmknowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.javaboss.pmknowledge.model.ProcessOutput;

@Repository
public interface ProcessOutputRepository extends JpaRepository<ProcessOutput,Long> {
	List<ProcessOutput> findByDocument_Id(Long id);
}
