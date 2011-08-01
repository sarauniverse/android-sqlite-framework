package com.johna.sqlite.framework.core.structure;

enum ColumnType {
	TEXT("text"),
	NUMERIC("numeric"),
	INTEGER("integer"),
	REAL("real"),
	NONE("none");
	
	private String columnType;
	
	private ColumnType(String columnType){
		this.columnType = columnType;
	}
	
	public String getColumnType(){
		return this.columnType;
	}
	
	public static ColumnType getColumnTypeFrom(String columnType){
		if(columnType.equals(ColumnType.TEXT.getColumnType())){
			return ColumnType.TEXT;
		} else if(columnType.equals(ColumnType.NUMERIC.getColumnType())){
			return ColumnType.NUMERIC;
		}  else if(columnType.equals(ColumnType.INTEGER.getColumnType())){
			return ColumnType.INTEGER;
		} else if(columnType.equals(ColumnType.REAL.getColumnType())){
			return ColumnType.REAL;
		} else {
			return ColumnType.NONE;
		}
	}
}
