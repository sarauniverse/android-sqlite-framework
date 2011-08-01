package com.johna.sqlite.framework.core.structure;

enum ConflictClause {
	DEFAULT(""),
	ROLLBACK("ROLLBACK"),
	ABORT("ABORT"),
	FAIL("FAIL"),
	IGNORE("IGNORE"),
	REPLACE("REPLACE");
	
	private String value;
	
	private ConflictClause(String value) {
		this.value = value;
	}
	
	public String getConflictClause(){
		return this.value;
	}
	
	public static ConflictClause getConflictClauseFrom(String conflictClause){
		if(conflictClause.equals(ConflictClause.ROLLBACK.getConflictClause())){
			return ConflictClause.ROLLBACK;
		} else if(conflictClause.equals(ConflictClause.ABORT.getConflictClause())){
			return ConflictClause.ABORT;
		}  else if(conflictClause.equals(ConflictClause.FAIL.getConflictClause())){
			return ConflictClause.FAIL;
		} else if(conflictClause.equals(ConflictClause.IGNORE.getConflictClause())){
			return ConflictClause.IGNORE;
		}  else if(conflictClause.equals(ConflictClause.REPLACE.getConflictClause())){
			return ConflictClause.REPLACE;
		}else {
			return ConflictClause.DEFAULT;
		}
	}
}
