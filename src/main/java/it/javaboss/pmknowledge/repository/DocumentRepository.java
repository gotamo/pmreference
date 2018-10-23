package it.javaboss.pmknowledge.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import it.javaboss.pmknowledge.model.Document;


@Repository
public interface DocumentRepository extends JpaRepository<Document,Long>{

}
