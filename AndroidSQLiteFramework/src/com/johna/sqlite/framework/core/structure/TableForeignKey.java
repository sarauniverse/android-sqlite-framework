package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;

class TableForeignKey {

	private ArrayList<String> columns;
	private TableForeignReference reference;
	
	public TableForeignKey(){
		this.columns = new ArrayList<String>();
	}

	public ArrayList<String> getColumns() {
		return columns;
	}

	public TableForeignReference getReference() {
		return reference;
	}
	
	public void addColumn(String name){
		this.columns.add(name);
	}
	
	public void createReference(String tableReference){
		this.reference = new TableForeignReference(tableReference);
	}
	
	public void appendForeignKey(StringBuilder statement){
		statement.append("FOREIGN KEY (");
		
		for (int i = 0; i < this.columns.size(); i++){
			statement.append(this.columns.get(i));
			
			if (i < this.columns.size() - 1)
				statement.append(", ");
		}
		
		statement.append(") ");
		
		this.reference.appendReferenceDef(statement);
	}
}
