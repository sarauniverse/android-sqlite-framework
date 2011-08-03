package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;

import com.johna.sqlite.framework.core.SQLiteFrameworkException;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

class Table {

	private String name;

	private ArrayList<Column> columns;
	private ArrayList<TableConstraint> constraints;
	private ArrayList<Row> rows;

	public Table(String name) {
		this.name = name;
		this.columns = new ArrayList<Column>();
		this.constraints = new ArrayList<TableConstraint>();
		this.rows = new ArrayList<Row>();
	}

	public String getName() {
		return name;
	}

	public ArrayList<Column> getColumns() {
		return columns;
	}

	public ArrayList<TableConstraint> getConstraints() {
		return constraints;
	}

	public void addColumn(Column column) {
		this.columns.add(column);
	}

	public void addConstraints(TableConstraint constraints) {
		this.constraints.add(constraints);
	}

	public void addRow(Row row) {
		this.rows.add(row);
	}

	public String getCreateStatement() {
		StringBuilder statement = new StringBuilder();

		statement.append("CREATE TABLE ");
		statement.append(this.name + " (");

		this.appendColumnsDef(0, statement);

		if (this.constraints.size() > 0)
			statement.append(",");

		this.appendConstraintsDef(0, statement);

		statement.append("\n)");

		return statement.toString();
	}

	public void insertRows(SQLiteDatabase db) throws SQLiteFrameworkException {
		this.insertRow(0, db);
	}

	private void appendColumnsDef(int index, StringBuilder statement) {

		if (index < this.columns.size()) {
			statement.append("\n\t");
			this.columns.get(index).appendColumnDef(statement);

			if (index != this.columns.size() - 1)
				statement.append(", ");

			this.appendColumnsDef(++index, statement);
		}
	}

	private void appendConstraintsDef(int index, StringBuilder statement) {
		if (index < this.constraints.size()) {
			statement.append("\n\t");
			this.constraints.get(index).appendConstraintsDef(statement);

			if (index != this.constraints.size() - 1)
				statement.append(", ");

			this.appendConstraintsDef(++index, statement);
		}
	}

	private void insertRow(int index, SQLiteDatabase db)
			throws SQLiteFrameworkException {
		if (index < this.rows.size()) {
			ArrayList<String> values = this.rows.get(index).getValues();

			ContentValues cv = new ContentValues();

			for (int i = 0; i < values.size(); i++) {
				Column column = this.columns.get(i);

				cv.put(column.getName(), values.get(i));
			}

			try {
				db.insertOrThrow(this.name, null, cv);
			} catch (SQLException e) {
				throw new SQLiteFrameworkException(
						"An error occurred when inserting record " + index
								+ " of Table " + this.name + ".", e);
			}

			this.insertRow(++index, db);
		}
	}
}
