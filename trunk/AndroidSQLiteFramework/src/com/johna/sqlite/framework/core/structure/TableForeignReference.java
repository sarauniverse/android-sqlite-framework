package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;


class TableForeignReference {

	private String tableReference;
	private ArrayList<String> columns;
	private ActionType onDelete;
	private ActionType onUpdate;
	private TableForeignDeferrable deferrable;
	
	public TableForeignReference(String tableReference){
		this.tableReference = tableReference;
		this.columns = new ArrayList<String>();
		onDelete = null;
		onUpdate = null;
		deferrable = null;
	}
	
	public String getTableReference(){
		return this.tableReference;
	}
	
	public ArrayList<String> getColumnsReferences(){
		return this.columns;
	}

	public ActionType getOnDelete() {
		return onDelete;
	}

	public ActionType getOnUpdate() {
		return onUpdate;
	}

	public TableForeignDeferrable getDeferrable() {
		return deferrable;
	}
	
	public void addColumnReference(String name){
		this.columns.add(name);
	}
	
	public void setOnDelete(ActionType actionType){
		this.onDelete = actionType;
	}
	
	public void setOnUpdate(ActionType actionType){
		this.onUpdate = actionType;
	}
	
	public void createDeferrable(boolean deferrable, boolean initiallyDeferred, boolean initiallyImmediate){
		this.deferrable = new TableForeignDeferrable(deferrable, initiallyDeferred, initiallyImmediate);
	}
	
	public void appendReferenceDef(StringBuilder statement){
		statement.append("REFERENCES " + this.tableReference + " (");
		
		for (int i = 0; i < this.columns.size(); i++){
			statement.append(this.columns.get(i));
			
			if (i < this.columns.size() - 1)
				statement.append(", ");
		}
		
		statement.append(") ");
		
		if (this.onDelete != null)
			statement.append("ON DELETE " + this.onDelete.getActionType().toUpperCase() + " ");
		
		if (this.onUpdate != null)
			statement.append("ON UPDATE " + this.onUpdate.getActionType().toUpperCase() + " ");
		
		if (this.deferrable != null)
			this.deferrable.appendDeferrableDef(statement);
	}
}
