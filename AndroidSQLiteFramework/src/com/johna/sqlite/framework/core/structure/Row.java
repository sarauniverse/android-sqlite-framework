package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;

class Row {
	private ArrayList<String> values;
	
	public Row()
	{
		this.values = new ArrayList<String>();
	}
	
	public void addValue(String value){
		this.values.add(value);
	}
	
	public ArrayList<String> getValues(){
		return this.values;
	}
}
