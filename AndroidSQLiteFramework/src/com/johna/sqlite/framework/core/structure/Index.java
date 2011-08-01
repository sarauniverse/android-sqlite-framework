package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;


class Index {
	private String name;
	private String onTable;
	private boolean unique;

	private ArrayList<IndexColumn> indexColumns;

	public Index(String name, String onTable, boolean unique) {
		this.name = name;
		this.onTable = onTable;
		this.unique = unique;
		this.indexColumns = new ArrayList<IndexColumn>();
	}

	public void setUnique(boolean unique) {
		this.unique = unique;
	}
	
	public void addIndexColumn(String name, String collateName, OrderType order){
		this.indexColumns.add(new IndexColumn(name, collateName, order));
	}

	public String getCreateStatement() {
		StringBuilder statement = new StringBuilder();

		statement.append("CREATE");

		if (unique)
			statement.append(" UNIQUE");

		statement.append(" INDEX " + this.name + " ON " + this.onTable + " (");

		this.appendColumnDef(0, statement);

		statement.append(")");

		return statement.toString();
	}

	private void appendColumnDef(int index, StringBuilder statement) {
		if (index < this.indexColumns.size()) {
			this.indexColumns.get(index).appendIndexColumnDef(statement);

			if (index < this.indexColumns.size() - 1)
				statement.append(", ");
			
			this.appendColumnDef(++index, statement);
		}
	}
}
