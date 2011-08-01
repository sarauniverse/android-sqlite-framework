package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;


class TablePrimaryKey {
	
	private ConflictClause conflictClause;
	private ArrayList<String> columnsKeys;
	
	public TablePrimaryKey(ConflictClause conflictClause){
		this.conflictClause = conflictClause;
		this.columnsKeys = new ArrayList<String>();
	}
	
	public ConflictClause getConflictClause(){
		return this.conflictClause;
	}
	
	public ArrayList<String> getColumnsKeys(){
		return this.columnsKeys;
	}
	
	public void addColumnKey(String name){
		this.columnsKeys.add(name);
	}

	public void appendPrimaryKeyDef(StringBuilder statement){
		statement.append("PRIMARY KEY (");
		
		for (int i = 0; i < this.columnsKeys.size(); i++){
			statement.append(this.columnsKeys.get(i));
			
			if (i < this.columnsKeys.size() - 1)
				statement.append(", ");
		}
		
		statement.append(this.conflictClause.getConflictClause().toUpperCase());
		
		statement.append(")");
	}
}
