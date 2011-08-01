package com.johna.sqlite.framework.core.xml;

enum XmlSchemaTag {
	BEGIN_STATEMENT("begin-statement"),
	COLLATE("collate"),
	COLUMN("column"),
	COLUMN_CHECK("column-check"),
	COLUMN_CONSTRAINT("column-constraint"),
	COLUMN_PRIMARY_KEY("column-primary-key"),
	COLUMNS("columns"),
	CONSTRAINT_CHECK("constraint-check"),
	DATABASE("database"),
	DEFAULT("default"),
	DEFERRABLE("deferrable"),
	EXPR("expr"),
	FOREIGN_COLUMN("foreign-column"),
	FOREIGN_KEY("foreign-key"),
	INDEX("index"),
	INDEX_COLUMN("index-column"),
	INDEXES("indexes"),
	KEY_COLUMN("key-column"),
	LITERAL_VALUE("literal-value"),
	ON_DELETE("on-delete"),
	ON_UPDATE("on-update"),
	PRIMARY_KEY("primary-key"),
	REFERENCE_COLUMN("reference-column"),
	REFERENCES("references"),
	ROW("row"),
	ROWS("rows"),
	SELECT_STATEMENT("select-statement"),
	SIGNED_NUMBER("signed-number"),
	SQLITE("sqlite"),
	TABLE("table"),
	TABLE_CONSTRAINT("table-constraint"),
	TABLE_CONSTRAINTS("table-constraints"),
	TABLES("tables"),
	TRIGGER("trigger"),
	TRIGGERS("triggers"),
	UNIQUE("unique"),
	UNIQUE_COLUMN("unique-column"),
	UPDATE_COLUMN("update-column"),
	VALUE("value"),
	VIEW("view"),
	VIEWS("views"),
	WHEN_RESTRICTION("when-restriction");
	
	private String xmlTag;
	
	private XmlSchemaTag(String xmlTag){
		this.xmlTag = xmlTag;
	}
	
	public String getXmlTag(){
		return this.xmlTag;
	}

	public static XmlSchemaTag getXmlSchemaTagFrom(String tagName){
		if(tagName.equals(XmlSchemaTag.BEGIN_STATEMENT.getXmlTag())){
			return XmlSchemaTag.BEGIN_STATEMENT;
		} else if(tagName.equals(XmlSchemaTag.COLLATE.getXmlTag())){
			return XmlSchemaTag.COLLATE;
		} else if(tagName.equals(XmlSchemaTag.COLUMN.getXmlTag())){
			return XmlSchemaTag.COLUMN;
		} else if(tagName.equals(XmlSchemaTag.COLUMNS.getXmlTag())){
			return XmlSchemaTag.COLUMNS;
		}  else if(tagName.equals(XmlSchemaTag.COLUMN_CHECK.getXmlTag())){
			return XmlSchemaTag.COLUMN_CHECK;
		} else if(tagName.equals(XmlSchemaTag.COLUMN_CONSTRAINT.getXmlTag())){
			return XmlSchemaTag.COLUMN_CONSTRAINT;
		}  else if(tagName.equals(XmlSchemaTag.COLUMN_PRIMARY_KEY.getXmlTag())){
			return XmlSchemaTag.COLUMN_PRIMARY_KEY;
		}  else if(tagName.equals(XmlSchemaTag.CONSTRAINT_CHECK.getXmlTag())){
			return XmlSchemaTag.CONSTRAINT_CHECK;
		} else if(tagName.equals(XmlSchemaTag.DATABASE.getXmlTag())){
			return XmlSchemaTag.DATABASE;
		}  else if(tagName.equals(XmlSchemaTag.DEFAULT.getXmlTag())){
			return XmlSchemaTag.DEFAULT;
		} else if(tagName.equals(XmlSchemaTag.DEFERRABLE.getXmlTag())){
			return XmlSchemaTag.DEFERRABLE;
		}  else if(tagName.equals(XmlSchemaTag.EXPR.getXmlTag())){
			return XmlSchemaTag.EXPR;
		} else if(tagName.equals(XmlSchemaTag.FOREIGN_COLUMN.getXmlTag())){
			return XmlSchemaTag.FOREIGN_COLUMN;
		}  else if(tagName.equals(XmlSchemaTag.FOREIGN_KEY.getXmlTag())){
			return XmlSchemaTag.FOREIGN_KEY;
		}  else if(tagName.equals(XmlSchemaTag.INDEX.getXmlTag())){
			return XmlSchemaTag.INDEX;
		} else if(tagName.equals(XmlSchemaTag.INDEX_COLUMN.getXmlTag())){
			return XmlSchemaTag.INDEX_COLUMN;
		}  else if(tagName.equals(XmlSchemaTag.INDEXES.getXmlTag())){
			return XmlSchemaTag.INDEXES;
		} else if(tagName.equals(XmlSchemaTag.KEY_COLUMN.getXmlTag())){
			return XmlSchemaTag.KEY_COLUMN;
		}  else if(tagName.equals(XmlSchemaTag.LITERAL_VALUE.getXmlTag())){
			return XmlSchemaTag.LITERAL_VALUE;
		} else if(tagName.equals(XmlSchemaTag.ON_DELETE.getXmlTag())){
			return XmlSchemaTag.ON_DELETE;
		}  else if(tagName.equals(XmlSchemaTag.ON_UPDATE.getXmlTag())){
			return XmlSchemaTag.ON_UPDATE;
		} else if(tagName.equals(XmlSchemaTag.PRIMARY_KEY.getXmlTag())){
			return XmlSchemaTag.PRIMARY_KEY;
		}  else if(tagName.equals(XmlSchemaTag.REFERENCE_COLUMN.getXmlTag())){
			return XmlSchemaTag.REFERENCE_COLUMN;
		}  else if(tagName.equals(XmlSchemaTag.REFERENCES.getXmlTag())){
			return XmlSchemaTag.REFERENCES;
		}  else if(tagName.equals(XmlSchemaTag.ROW.getXmlTag())){
			return XmlSchemaTag.ROW;
		}  else if(tagName.equals(XmlSchemaTag.ROWS.getXmlTag())){
			return XmlSchemaTag.ROWS;
		}  else if(tagName.equals(XmlSchemaTag.SELECT_STATEMENT.getXmlTag())){
			return XmlSchemaTag.SELECT_STATEMENT;
		} else if(tagName.equals(XmlSchemaTag.SIGNED_NUMBER.getXmlTag())){
			return XmlSchemaTag.SIGNED_NUMBER;
		} else if(tagName.equals(XmlSchemaTag.SQLITE.getXmlTag())){
			return XmlSchemaTag.SQLITE;
		}  else if(tagName.equals(XmlSchemaTag.TABLE.getXmlTag())){
			return XmlSchemaTag.TABLE;
		} else if(tagName.equals(XmlSchemaTag.TABLES.getXmlTag())){
			return XmlSchemaTag.TABLES;
		} else if(tagName.equals(XmlSchemaTag.TABLE_CONSTRAINT.getXmlTag())){
			return XmlSchemaTag.TABLE_CONSTRAINT;
		}  else if(tagName.equals(XmlSchemaTag.TABLE_CONSTRAINTS.getXmlTag())){
			return XmlSchemaTag.TABLE_CONSTRAINTS;
		}  else if(tagName.equals(XmlSchemaTag.TRIGGER.getXmlTag())){
			return XmlSchemaTag.TRIGGER;
		}  else if(tagName.equals(XmlSchemaTag.TRIGGERS.getXmlTag())){
			return XmlSchemaTag.TRIGGERS;
		} else if(tagName.equals(XmlSchemaTag.UNIQUE.getXmlTag())){
			return XmlSchemaTag.UNIQUE;
		}  else if(tagName.equals(XmlSchemaTag.UNIQUE_COLUMN.getXmlTag())){
			return XmlSchemaTag.UNIQUE_COLUMN;
		}  else if(tagName.equals(XmlSchemaTag.UPDATE_COLUMN.getXmlTag())){
			return XmlSchemaTag.UPDATE_COLUMN;
		}  else if(tagName.equals(XmlSchemaTag.VALUE.getXmlTag())){
			return XmlSchemaTag.VALUE;
		}  else if(tagName.equals(XmlSchemaTag.VIEW.getXmlTag())){
			return XmlSchemaTag.VIEW;
		}  else if(tagName.equals(XmlSchemaTag.VIEWS.getXmlTag())){
			return XmlSchemaTag.VIEWS;
		} else {
			return XmlSchemaTag.WHEN_RESTRICTION;
		}
	}
}
