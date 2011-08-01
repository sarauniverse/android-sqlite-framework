package com.johna.sqlite.framework.core.structure;

class ColumnCollate {
	private String name;
	
	public ColumnCollate(String name){
		this.name = name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void appendCollateDef(StringBuilder statement){
		statement.append(" COLLATE " + this.name);
	}
}
