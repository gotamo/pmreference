package it.javaboss.pmknowledge.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OrderBy;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import org.primefaces.model.DefaultTreeNode;
import org.primefaces.model.TreeNode;

@Entity
@Table( name = "PROCESSES" )
public class Process {
	private Long 	id;
	private String 	name;
	
	private ProcessGroup  processGroup;
	private KnowledgeArea knowledgeArea;
	
	private List<ProcessInput>  			processInputs;
	private List<ProcessOutput> 			processOutputs;
	private List<ProcessToolAndTechnique> 	processToolsAndTechniques;
	
	//---------------------------------------------------------------------------------------------
	
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
	
	@ManyToOne
    @JoinColumn( name="ID_PROCESS_GROUP" )
	public ProcessGroup getProcessGroup() {
		return processGroup;
	}
	public void setProcessGroup(ProcessGroup processGroup) {
		this.processGroup = processGroup;
	}
	
	@ManyToOne
    @JoinColumn( name="ID_KNOWLEDGE_AREA" )
	public KnowledgeArea getKnowledgeArea() {
		return knowledgeArea;
	}
	public void setKnowledgeArea(KnowledgeArea knowledgeArea) {
		this.knowledgeArea = knowledgeArea;
	}
	
	@OneToMany( mappedBy = "process" )
	@OrderBy("order ASC")
	@LazyCollection(LazyCollectionOption.FALSE)
	public List<ProcessInput> getProcessInputs() {
		return processInputs;
	}
	public void setProcessInputs(List<ProcessInput> processInputs) {
		this.processInputs = processInputs;
	}
	
	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( mappedBy = "process" )
	public List<ProcessOutput> getProcessOutputs() {
		return processOutputs;
	}
	public void setProcessOutputs(List<ProcessOutput> processOutputs) {
		this.processOutputs = processOutputs;
	}

	@LazyCollection(LazyCollectionOption.FALSE)
	@OneToMany( mappedBy = "process" )
	public List<ProcessToolAndTechnique> getProcessToolsAndTechniques() {
		return processToolsAndTechniques;
	}
	public void setProcessToolsAndTechniques(List<ProcessToolAndTechnique> processToolsAndTechniques) {
		this.processToolsAndTechniques = processToolsAndTechniques;
	}	
	
	//---------------------------------------------------------------------------------------------
	
	@Transient
	public TreeNode getProcessInputTree() {
		return buildTree( this.processInputs );
	}
	
	@Transient
	public TreeNode getProcessOutputTree() {
		return buildTree( this.processOutputs );
	}
	
	@Transient
	public TreeNode getProcessTT() {
		return buildTree( this.processToolsAndTechniques );
	}
	
	@Transient
	public <T extends ProcessLink> TreeNode buildTree(  List<T> list) {
		TreeNode root = new DefaultTreeNode("root", null);
		root.setExpanded( true );
		for ( ProcessLink processDocument : list ) {
			TreeNode node = new DefaultTreeNode( processDocument );
			node.setExpanded( true );
			
			if ( processDocument.getValue().getParent() == null ) {
				// Document added to the root
				root.getChildren().add( node );
				node.setParent( root );
				
			} else {
				// Search the parent
				TreeNode parent = null;
				for ( TreeNode child : root.getChildren() ) {
					if ( ((ProcessLink) child.getData()).getValue().getId().equals( processDocument.getValue().getParent().getId() ) ) {
						parent = child;
						break;
					}
				}
				
				if ( parent == null ) {
					// Create a dummy parent
					ProcessLink dummyProcessDocument = new DummyProcessLink<>();
					dummyProcessDocument.setValue( processDocument.getValue().getParent() );
					dummyProcessDocument.setProcess( processDocument.getProcess() );
					
					parent = new DefaultTreeNode( dummyProcessDocument, root );
					parent.setExpanded( true );
				}
				
				// Add parent to the root
				parent.getChildren().add( node );
				node.setParent( parent );
			}
		}
		return root;
		
	}
}
