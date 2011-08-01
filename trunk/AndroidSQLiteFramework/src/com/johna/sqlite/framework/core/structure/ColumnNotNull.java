package com.johna.sqlite.framework.core.structure;


class ColumnNotNull {
	
	private ConflictClause conflictClause;
	
	public ColumnNotNull(ConflictClause conflictClause){
		this.conflictClause = conflictClause;
	}
	
	public ConflictClause getConflictClause(){
		return this.conflictClause;
	}
	
	public void appendColumnNotNullDef(StringBuilder statement){
		statement.append(" NOT NULL " + this.conflictClause.getConflictClause().toUpperCase());
	}
}
