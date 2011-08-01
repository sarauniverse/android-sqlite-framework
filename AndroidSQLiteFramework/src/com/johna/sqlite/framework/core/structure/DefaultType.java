package com.johna.sqlite.framework.core.structure;

enum DefaultType {
	SIGNED_NUMBER("signed-number"),
	LITERAL_VALUE("literal-value"),
	EXPR("expr");
	
	private String defaultType;
	
	private DefaultType(String defaultType){
		this.defaultType = defaultType;
	}
	
	public String getDefaultType(){
		return this.defaultType;
	}
	
	public static DefaultType getDefaultTypeFrom(String defaultType){
		if(defaultType.equals(DefaultType.SIGNED_NUMBER.getDefaultType())){
			return DefaultType.SIGNED_NUMBER;
		} else if(defaultType.equals(DefaultType.LITERAL_VALUE.getDefaultType())){
			return DefaultType.LITERAL_VALUE;
		}else {
			return DefaultType.EXPR;
		}
	}
}
