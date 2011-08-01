package com.johna.sqlite.framework.core.structure;

class View {
	private String name;
	private String selectStatement;
	
	public View(String name){
		this.name = name;
	}
	
	public void setSelectStatement(String selectStatement){
		this.selectStatement = selectStatement;
	}
	
	public String getCreateStatement(){
		StringBuilder createStatement = new StringBuilder();
		
		createStatement.append("CREATE VIEW " + this.name + " AS ");
		createStatement.append(this.selectStatement);
		
		return createStatement.toString();
	}
}
