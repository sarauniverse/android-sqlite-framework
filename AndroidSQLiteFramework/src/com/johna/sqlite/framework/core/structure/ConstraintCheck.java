package com.johna.sqlite.framework.core.structure;

class ConstraintCheck {
	private String expr;
	
	public ConstraintCheck(String expr){
		this.expr = expr;
	}
	
	public String getExpr(){
		return this.expr;
	}
	
	public void appendCheckDef(StringBuilder statement){
		statement.append(" CHECK (" + this.expr + ")");
	}
}
