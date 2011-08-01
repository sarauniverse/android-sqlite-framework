package com.johna.sqlite.framework.core.structure;

enum InitiallyType {
	IMMEDIATE("initially immediate"),
	DEFERRED("initially deferred");
	
	private String initiallyValue;
	
	private InitiallyType(String initiallyValue){
		this.initiallyValue = initiallyValue;
	}
	
	public String getInitiallyValue(){
		return this.initiallyValue;
	}
}
