package it.javaboss.pmknowledge.repository;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import it.javaboss.pmknowledge.model.Process;

public class CustomizedProcessRepositoryImpl implements CustomizedProcessRepository {
	@PersistenceContext
    private EntityManager em;
	
	@SuppressWarnings("unchecked")
	public List<Process> search( 
			List<Long> processIds,
			List<Long> knowledgeAreaIds,
			List<Long> processGroupIds) {
		
		String conn = "";
		String where = "";

		if ( processIds != null && !processIds.isEmpty() ) {
			where += "p.id IN (:processIds)";
			conn = " AND ";
		}
		
		if ( knowledgeAreaIds != null && !knowledgeAreaIds.isEmpty() ) {
			where += conn + "p.knowledgeArea.id IN (:knowledgeAreaIds)";
			conn = " AND ";
		}
		
		if ( processGroupIds != null && !processGroupIds.isEmpty() ) {
			where += conn + "p.processGroup.id IN (:processGroupIds)";
			conn = " AND ";
		}
		
		Query query =  em.createQuery( "SELECT p FROM Process p " + 
										( where != "" ? "WHERE " + where : " " ) );
		
		if ( processIds != null && !processIds.isEmpty() ) {
			query.setParameter( "processIds", processIds );
		}
		
		if ( knowledgeAreaIds != null && !knowledgeAreaIds.isEmpty() ) {
			query.setParameter( "knowledgeAreaIds", knowledgeAreaIds );
		}
		
		if ( processGroupIds != null && !processGroupIds.isEmpty() ) {
			query.setParameter( "processGroupIds", processGroupIds );
		}

		return query.getResultList();
	}
}
