package com.johna.sqlite.framework.core.structure;

enum WhenTriggerType {
	BEFORE("BEFORE"),
	AFTER("AFTER"),
	INSTEAD_OF("INSTEAD OF"),
	DEFAULT("DEFAULT");
	
	private String when;
	
	private WhenTriggerType(String when){
		this.when = when;
	}
	
	public String getWhenTrigger(){
		return this.when;
	}
	
	public static WhenTriggerType getWhenTriggerTypeFrom(String when){
		if(when.equals(WhenTriggerType.BEFORE.getWhenTrigger())){
			return WhenTriggerType.BEFORE;
		} else if(when.equals(WhenTriggerType.AFTER.getWhenTrigger())){
			return WhenTriggerType.AFTER;
		} else if(when.equals(WhenTriggerType.INSTEAD_OF.getWhenTrigger())){
			return WhenTriggerType.INSTEAD_OF;
		} else {
			return WhenTriggerType.DEFAULT;
		}
	}
}
