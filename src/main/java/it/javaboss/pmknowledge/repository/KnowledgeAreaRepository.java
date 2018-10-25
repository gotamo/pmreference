package it.javaboss.pmknowledge.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.javaboss.pmknowledge.model.KnowledgeArea;

@Repository
public interface KnowledgeAreaRepository extends JpaRepository<KnowledgeArea,Long>{
	public List<KnowledgeArea> findByFullNameContaining(String fullName);
	public List<KnowledgeArea> findAllByOrderByOrderAsc();

}
