package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;


class TableUnique {
	
	private ConflictClause conflictClause;
	private ArrayList<String> columnsUnique;
	
	public TableUnique(ConflictClause conflictClause){
		this.conflictClause = conflictClause;
		this.columnsUnique = new ArrayList<String>();
	}
	
	public ConflictClause getConflictClause(){
		return this.conflictClause;
	}
	
	public ArrayList<String> getColumnsKeys(){
		return this.columnsUnique;
	}
	
	public void addColumnKey(String name){
		this.columnsUnique.add(name);
	}

	public void appendUniqueDef(StringBuilder statement){
		statement.append("UNIQUE (");
		
		for (int i = 0; i < this.columnsUnique.size(); i++){
			statement.append(this.columnsUnique.get(i));
			
			if (i < this.columnsUnique.size() - 1)
				statement.append(", ");
		}
		
		statement.append(this.conflictClause.getConflictClause().toUpperCase());
		
		statement.append(")");
	}
}
