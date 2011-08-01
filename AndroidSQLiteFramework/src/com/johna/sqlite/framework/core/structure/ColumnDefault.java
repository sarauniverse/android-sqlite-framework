package com.johna.sqlite.framework.core.structure;


class ColumnDefault {
	private DefaultType defaultType;
	private String value;

	public ColumnDefault(DefaultType defaultType, String value) {
		this.defaultType = defaultType;
		this.value = value;
	}

	public DefaultType getDefaultType() {
		return defaultType;
	}

	public String getValue() {
		return value;
	}

	public void appendDefaultDef(StringBuilder statement) {
		statement.append(" DEFAULT ");

		switch (defaultType) {
		case LITERAL_VALUE:
		case SIGNED_NUMBER:
			statement.append(this.value + " ");
			break;
		default:
			statement.append("(" + this.value + ")");
			break;
		}
	}
}
