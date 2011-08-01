package com.johna.sqlite.framework.core.structure;

class ColumnConstraint {

	private String name;
	
	public ColumnConstraint(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void appendColumnConstraintDef(StringBuilder statement){
		statement.append(" CONSTRAINT " + this.name);
	}
}
