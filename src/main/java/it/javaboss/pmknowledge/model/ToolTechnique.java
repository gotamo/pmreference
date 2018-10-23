package it.javaboss.pmknowledge.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table( name = "TOOLS_AND_TECHNIQUES" )
public class ToolTechnique {
	private Long id;
	private String name;
	private String description;
	private ToolTechnique parent;
	
	@Id
	@Column(name="ID")
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	
	@Column(name="NAME")
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	@Column(name="DESCRIPTION")
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	@ManyToOne
    @JoinColumn( name="ID_PARENT_TT" )
	public ToolTechnique getParent() {
		return parent;
	}
	public void setParent(ToolTechnique parent) {
		this.parent = parent;
	}
	
	
}
