package com.johna.sqlite.framework.core.structure;

enum ActionType {
	SET_NULL("set null"),
	SET_DEFAULT("set default"),
	CASCADE("cascade"),
	RESTRINCT("restrinct"),
	NO_ACTION("no action");
	
	private String action;
	
	private ActionType(String action){
		this.action = action;
	}
	
	public String getActionType(){
		return this.action;
	}
	
	public static ActionType getActionTypeFrom(String actionType){
		if(actionType.equals(ActionType.SET_NULL.getActionType())){
			return ActionType.SET_NULL;
		} else if(actionType.equals(ActionType.SET_DEFAULT.getActionType())){
			return ActionType.SET_DEFAULT;
		}  else if(actionType.equals(ActionType.CASCADE.getActionType())){
			return ActionType.CASCADE;
		} else if(actionType.equals(ActionType.RESTRINCT.getActionType())){
			return ActionType.RESTRINCT;
		} else {
			return ActionType.NO_ACTION;
		}
	}
}
