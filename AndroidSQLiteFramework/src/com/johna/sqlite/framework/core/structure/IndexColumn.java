package com.johna.sqlite.framework.core.structure;


class IndexColumn {
	private String name;
	private String collateName;
	private OrderType order;

	public IndexColumn(String name, String collateName, OrderType order) {
		this.name = name;
		this.collateName = collateName;
		this.order = order;
	}

	public void setCollateName(String collateName) {
		this.collateName = collateName;
	}

	public void setOrder(OrderType order) {
		this.order = order;
	}

	public void appendIndexColumnDef(StringBuilder statement) {
		statement.append(this.name);

		if (collateName != "")
			statement.append(" COLLATE " + this.collateName);

		if (this.order != null)
			statement.append(" " + this.order.getOrderType());
	}
}
