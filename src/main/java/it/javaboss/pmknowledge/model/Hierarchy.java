package it.javaboss.pmknowledge.model;

public interface Hierarchy {
	public <T extends Hierarchy & Identifiable> T getParent();
}
