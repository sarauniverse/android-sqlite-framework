package com.johna.sqlite.framework.core.structure;


class TableConstraint {

	private String name;

	private TablePrimaryKey primaryKey;
	private TableUnique unique;
	private ConstraintCheck check;
	private TableForeignKey foreignKey;

	private TableConstraintType constraintType;

	public TableConstraint() {
		this.name = "";
	}

	public TableConstraint(String name) {
		this();
		this.name = name;
	}

	public String getName() {
		return this.name;
	}

	public TableForeignKey getForeignKey() {
		return this.foreignKey;
	}

	public TablePrimaryKey getPrimaryKey() {
		return this.primaryKey;
	}

	public TableUnique getUnique() {
		return this.unique;
	}

	public ConstraintCheck getCheck() {
		return this.check;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void createPrimaryKey(ConflictClause conflictClause) {
		this.primaryKey = new TablePrimaryKey(conflictClause);
		this.constraintType = TableConstraintType.PRIMARY_KEY;
	}

	public void createUnique(ConflictClause conflictClause) {
		this.unique = new TableUnique(conflictClause);
		this.constraintType = TableConstraintType.UNIQUE;
	}

	public void createCheck(String expr) {
		this.check = new ConstraintCheck(expr);
		this.constraintType = TableConstraintType.CHECK;
	}

	public void createForeignKey(TableForeignKey foreignKey) {
		this.foreignKey = foreignKey;
		this.constraintType = TableConstraintType.FOREIGN;
	}

	public void appendConstraintsDef(StringBuilder statement) {
		if (this.name != "")
			statement.append("CONSTRAINT " + this.name + " ");

		switch (this.constraintType) {
		case PRIMARY_KEY:
			this.primaryKey.appendPrimaryKeyDef(statement);
			break;
		case UNIQUE:
			this.unique.appendUniqueDef(statement);
			break;
		case CHECK:
			this.check.appendCheckDef(statement);
			break;
		default:
			this.foreignKey.appendForeignKey(statement);
			break;
		}
	}

}
