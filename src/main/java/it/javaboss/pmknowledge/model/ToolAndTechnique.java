package it.javaboss.pmknowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "TOOLS_AND_TECHNIQUES" )
public class ToolAndTechnique implements Identifiable, Hierarchy {
	private Long id;
	private String code;
	private String name;
	private ToolAndTechnique parent;
	private String fileName;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="CODE")
	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@SuppressWarnings("unchecked")
	@ManyToOne
    @JoinColumn( name="ID_PARENT_TT" )
	public ToolAndTechnique getParent() {
		return parent;
	}
	public void setParent(ToolAndTechnique parent) {
		this.parent = parent;
	}
	
	@Column(name="FILE_NAME")
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
}
