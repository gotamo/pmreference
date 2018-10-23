package it.javaboss.pmknowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name = "PROCESS_INPUTS" )
public class ProcessInput implements ProcessLink {
	private Long id;
	private Process process;
	private Document document;
	private Integer order;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@ManyToOne
    @JoinColumn( name="ID_PROCESS" )
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	
	@ManyToOne
    @JoinColumn( name="ID_DOCUMENT" )
	public Document getDocument() {
		return document;
	}
	public void setDocument(Document document) {
		this.document = document;
	}
	
	@Column(name="ORDER")
	public Integer getOrder() {
		return order;
	}
	public void setOrder(Integer order) {
		this.order = order;
	}
	
	@Transient
	@SuppressWarnings("unchecked")
	@Override
	public <T extends Identifiable & Hierarchy> T getValue() {
		return (T) getDocument();
	}
	@Override
	public <T extends Identifiable & Hierarchy> void setValue(T value) {
		setDocument( (Document) value );
	}
}
