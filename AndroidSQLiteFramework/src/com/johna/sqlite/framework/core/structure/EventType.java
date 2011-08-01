package com.johna.sqlite.framework.core.structure;

enum EventType {
	DELETE("DELETE"),
	INSERT("INSERT"),
	UPDATE_OF("UPDATE OF");
	
	private String event;
	
	private EventType(String event){
		this.event = event;
	}
	
	public String geteventTrigger(){
		return this.event;
	}
	
	public static EventType getEventTriggerTypeFrom(String event){
		if(event.equals(EventType.DELETE.geteventTrigger())){
			return EventType.DELETE;
		} else if(event.equals(EventType.INSERT.geteventTrigger())){
			return EventType.INSERT;
		}else {
			return EventType.UPDATE_OF;
		}
	}

}
