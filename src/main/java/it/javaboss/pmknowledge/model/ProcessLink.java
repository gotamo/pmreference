package it.javaboss.pmknowledge.model;

public interface ProcessLink {
	public Process getProcess();
	public void setProcess(Process process);
	public <T extends Identifiable & Hierarchy> T getValue();
	public <T extends Identifiable & Hierarchy> void setValue(T value);
}
