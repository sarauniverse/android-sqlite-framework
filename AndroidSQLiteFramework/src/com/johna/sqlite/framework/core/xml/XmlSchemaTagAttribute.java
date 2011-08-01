package com.johna.sqlite.framework.core.xml;

enum XmlSchemaTagAttribute {
	ACTION("action"),
	COLLATE_NAME("collate-name"),
	COLLATION_NAME("collation-name"),
	COLUMN_AUTOINCREMENT("column-autoincrement"),
	COLUMN_CONFLICT_CLAUSE("column-conflict-clause"),
	COLUMN_NAME("column-name"),
	CONFLICT_CLAUSE("conflict-clause"),
	DATABASE_NAME("database-name"),
	EVENT("event"),
	EXPR("expr"),
	FOR_EACH_ROW("for-each-row"),
	INDEX_NAME("index-name"),
	INITIALLY("initially"),
	NAME("name"),
	NOT("not"),
	NOT_NULL("notNull"),
	ORDER("order"),
	ON_TABLE("on-table"),
	TABLE_NAME("table-name"),
	TRIGGER_NAME("trigger-name"),
	TYPE("type"),
	UNIQUE("unique"),
	VERSION("version"),
	VIEW_NAME("view-name"),
	WHEN("when");
	
	private String xmlAttrName;
	
	private XmlSchemaTagAttribute(String xmlAttrName){
		this.xmlAttrName = xmlAttrName;
	}
	
	public String getXmlAttrName(){
		return this.xmlAttrName;
	}
}
