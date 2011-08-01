package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;


class Trigger {
	private String onTable;
	private WhenTriggerType when;
	private EventType event;
	private String name;
	private boolean forEachRow;
	private ArrayList<String> updateColumns;
	private String whenRestriction;
	private String beginStatement;

	public Trigger(String onTable, WhenTriggerType when, EventType event,
			String name, boolean forEachRow) {
		this.onTable = onTable;
		this.when = when;
		this.event = event;
		this.name = name;
		this.forEachRow = forEachRow;
		this.updateColumns = new ArrayList<String>();
	}

	public void setWhenRestriction(String whenRestriction) {
		this.whenRestriction = whenRestriction;
	}

	public void setBeginStatement(String beginStatement) {
		this.beginStatement = beginStatement;
	}

	public void addUpdateColumn(String updateColumn) {
		this.updateColumns.add(updateColumn);
	}

	public String getCreateStatement() {
		StringBuilder statement = new StringBuilder();

		statement.append("CREATE TRIGGER " + this.name);

		this.appendWhenDef(statement);

		this.appendEventDef(statement);
		
		statement.append(" ON " + this.onTable);
		
		if (forEachRow)
			statement.append(" FOR EACH ROW");
		
		if (whenRestriction != "")
			statement.append(" WHEN " + this.whenRestriction);
		
		statement.append(" BEGIN " + this.beginStatement + " END");
		
		return statement.toString();
	}
	
	private void appendWhenDef(StringBuilder statement){
		switch (when) {
		case AFTER:
			statement.append(" AFTER");
			break;
		case BEFORE:
			statement.append(" AFTER");
			break;
		case INSTEAD_OF:
			statement.append(" INSTEAD OF");
			break;
		default:
			break;
		}
	}

	private void appendEventDef(StringBuilder statement) {
		switch (event) {
		case DELETE:
			statement.append(" DELETE");
			break;
		case INSERT:
			statement.append(" INSERT");
			break;
		default:
			statement.append(" UPDATE");

			if (this.updateColumns.size() > 0) {
				statement.append(" OF");

				for (int i = 0; i < this.updateColumns.size(); i++) {
					statement.append(" " + this.updateColumns.get(i));

					if (i < this.updateColumns.size() - 2)
						statement.append(",");
				}
			}
			break;
		}
	}
}
