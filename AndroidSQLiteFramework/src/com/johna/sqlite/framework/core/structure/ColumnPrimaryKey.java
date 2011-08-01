package com.johna.sqlite.framework.core.structure;


class ColumnPrimaryKey {
	private boolean autoIncrement;
	private ConflictClause conflictClause;
	private OrderType order;

	public ColumnPrimaryKey(OrderType order, ConflictClause conflictClause,
			boolean autoIncrement) {
		this.order = order;
		this.conflictClause = conflictClause;
		this.autoIncrement = autoIncrement;
	}

	public boolean isAutoIncrement() {
		return autoIncrement;
	}

	public ConflictClause getConflictClause() {
		return conflictClause;
	}

	public OrderType getOrder() {
		return order;
	}

	public void appendColumnPrimaryKeyDef(StringBuilder statement) {
		statement.append(" PRIMARY KEY ");

		if (this.order != null)
			statement.append(order.getOrderType());

		statement
				.append(conflictClause.getConflictClause().toUpperCase() + " ");

		if (this.autoIncrement == true)
			statement.append("AUTOINCREMENT");
	}
}
