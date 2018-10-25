package it.javaboss.pmknowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.javaboss.pmknowledge.model.ProcessGroup;

@Repository
public interface ProcessGroupRepository extends JpaRepository<ProcessGroup,Long>{
	public List<ProcessGroup> findByFullNameContaining(String fullName);
	public List<ProcessGroup> findAllByOrderByOrderAsc();
}
