package it.javaboss.pmknowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

@Entity
@Table( name = "PROCESS_OUTPUTS" )
public class ProcessOutput implements ProcessLink {
	private Long id;
	private Process process;
	private Document document;
	private String operation;
	
	//-------------------------------------------------------------------------------------
	
	public class Operation {
		public static final String GENERATE = "G";
		public static final String UPDATE = "U";
		public static final String GENERATE_AND_UPDATE = "GU";
	}
	
	//-------------------------------------------------------------------------------------
	
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
	
	@Column(name="OPERATION")
	public String getOperation() {
		return operation;
	}
	public void setOperation(String operation) {
		this.operation = operation;
	}
	
	//-------------------------------------------------------------------------------------
	
	@Transient
	public boolean isGenerate() {
		return Operation.GENERATE.equals( this.operation );
	}
	
	@Transient
	public boolean isUpdate() {
		return Operation.UPDATE.equals( this.operation );
	}
	
	@Transient
	public boolean isGenerateAndUpdate() {
		return Operation.GENERATE_AND_UPDATE.equals( this.operation );
	}
	
	@SuppressWarnings("unchecked")
	@Transient
	@Override
	public <T extends Identifiable & Hierarchy> T getValue() {
		return (T) getDocument();
	}
	@Override
	public <T extends Identifiable & Hierarchy> void setValue(T value) {
		setDocument( (Document) value );
	}
}
