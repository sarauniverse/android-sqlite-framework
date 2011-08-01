package com.johna.sqlite.framework.core.structure;

enum OrderType {
	ASC("ASC"),
	DESC("DESC");
	
	private String order;
	
	private OrderType(String order){
		this.order = order;
	}
	
	public String getOrderType(){
		return this.order;
	}
}
