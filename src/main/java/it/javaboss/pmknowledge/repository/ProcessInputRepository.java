package it.javaboss.pmknowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.javaboss.pmknowledge.model.ProcessInput;

@Repository
public interface ProcessInputRepository extends JpaRepository<ProcessInput,Long> {
	List<ProcessInput> findByDocument_Id(Long id);
}
