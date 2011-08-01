package com.johna.sqlite.framework.core.structure;

class TableForeignDeferrable {

	private boolean notDeferrable;
	private boolean initiallyDeferred;
	private boolean initiallyImmediate;
	
	public TableForeignDeferrable(boolean notDeferrable, boolean initiallyDeferred, boolean initiallyImmediate){
		this.notDeferrable = notDeferrable;
		this.initiallyDeferred = initiallyDeferred;
		this.initiallyImmediate = initiallyImmediate;
	}

	public boolean isNotDeferrable() {
		return notDeferrable;
	}

	public boolean isInitiallyDeferred() {
		return initiallyDeferred;
	}

	public boolean isInitiallyImmediate() {
		return initiallyImmediate;
	}
	
	public void appendDeferrableDef(StringBuilder statement){
		if (notDeferrable)
			statement.append("NOT ");
		
		statement.append("DEFERRABLE");
		
		if (initiallyDeferred)
			statement.append(" INITIALLY DEFERRED");
		
		if (initiallyImmediate)
			statement.append(" INITIALLY IMMEDIATE");
	}
}
