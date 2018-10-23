package it.javaboss.pmknowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "DOCUMENTS" )
public class Document implements Identifiable, Hierarchy {
	private Long id;
	private String shortName;
	private String fullName;
	private String description;
	private Document parent;
	private String fileName;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="SHORT_NAME")
	public String getShortName() {
		return shortName;
	}
	public void setShortName(String shortName) {
		this.shortName = shortName;
	}
	
	@Column(name="FULL_NAME")
	public String getFullName() {
		return fullName;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@SuppressWarnings("unchecked")
	@ManyToOne
    @JoinColumn( name="ID_PARENT_DOCUMENT" )
	public Document getParent() {
		return parent;
	}
	public void setParent(Document document) {
		this.parent = document;
	}
	
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
}
