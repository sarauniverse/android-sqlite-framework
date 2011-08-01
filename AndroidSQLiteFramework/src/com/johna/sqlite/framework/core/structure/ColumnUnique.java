package com.johna.sqlite.framework.core.structure;


class ColumnUnique {
	
	private ConflictClause conflictClause;
	
	public ColumnUnique(ConflictClause conflictClause){
		this.conflictClause = conflictClause;
	}
	
	public ConflictClause getConflictClause(){
		return this.conflictClause;
	}
	
	public void appendUniqueDef(StringBuilder statement){
		statement.append(" UNIQUE " + this.conflictClause.getConflictClause().toUpperCase());
	}
}
