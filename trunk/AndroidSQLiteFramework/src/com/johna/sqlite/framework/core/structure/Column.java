package com.johna.sqlite.framework.core.structure;


class Column {

	private String name;
	private ColumnType columnType;

	private ColumnPrimaryKey columnPrimaryKey;
	private ColumnNotNull notNull;
	private ColumnUnique unique;
	private ColumnConstraint constraint;
	private ConstraintCheck check;
	private ColumnDefault defaultValue;
	private ColumnCollate collate;
	
	public Column(String name){
		this.name = name;
	}

	public Column(String name, ColumnType columnType) {
		this.name = name;
		this.columnType = columnType;
	}

	public String getName() {
		return name;
	}

	public ColumnType getColumnType() {
		return columnType;
	}

	public ColumnConstraint getConstraint() {
		return constraint;
	}

	public ColumnNotNull getNotNull() {
		return notNull;
	}

	public ColumnUnique getUnique() {
		return unique;
	}

	public ConstraintCheck getCheck() {
		return check;
	}

	public ColumnDefault getDefaultValue() {
		return defaultValue;
	}

	public ColumnCollate getCollate() {
		return collate;
	}

	public ColumnPrimaryKey getPrimaryKey() {
		return this.columnPrimaryKey;
	}

	public void createConstraint(String name) {
		this.constraint = new ColumnConstraint(name);
	}

	public void createPrimaryKey(OrderType order,
			ConflictClause conflictClause, boolean autoIncrement) {
		this.columnPrimaryKey = new ColumnPrimaryKey(order, conflictClause,
				autoIncrement);
	}

	public void createNotNull(ConflictClause conflictClause) {
		this.notNull = new ColumnNotNull(conflictClause);
	}

	public void createUnique(ConflictClause conflictClause) {
		this.unique = new ColumnUnique(conflictClause);
	}

	public void createCheck(String expr) {
		this.check = new ConstraintCheck(expr);
	}

	public void createDefaultValue(DefaultType defaultType, String value) {
		this.defaultValue = new ColumnDefault(defaultType, value);
	}

	public void createCollate(String name) {
		this.collate = new ColumnCollate(name);
	}

	public void appendColumnDef(StringBuilder statement) {
		statement.append(this.name + " ");
		statement.append(this.columnType.getColumnType().toUpperCase());

		if (this.constraint != null)
			this.constraint.appendColumnConstraintDef(statement);
		
		if (this.columnPrimaryKey != null)
			this.columnPrimaryKey.appendColumnPrimaryKeyDef(statement);

		if (this.notNull != null)
			this.notNull.appendColumnNotNullDef(statement);

		if (this.unique != null)
			this.unique.appendUniqueDef(statement);

		if (this.check != null)
			this.check.appendCheckDef(statement);

		if (this.defaultValue != null)
			this.defaultValue.appendDefaultDef(statement);

		if (this.collate != null)
			this.collate.appendCollateDef(statement);
	}
}
