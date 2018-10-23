package it.javaboss.pmknowledge.model;

public class DummyProcessLink<T extends Identifiable & Hierarchy> implements ProcessLink {

	private Process process;
	private T value;
	
	public Process getProcess() {
		return process;
	}
	public void setProcess(Process process) {
		this.process = process;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public T getValue() {
		return value;
	}
	@SuppressWarnings("unchecked")
	@Override
	public <E extends Identifiable & Hierarchy> void setValue(E value) {
		this.value = (T) value;
	}

	public boolean isUpdate() {
		return false;
	}
	
	public Document getDocument() {
		return (Document) value;
	}
	
	public ToolAndTechnique getToolAndTechnique() {
		return (ToolAndTechnique) value;
	}
}
