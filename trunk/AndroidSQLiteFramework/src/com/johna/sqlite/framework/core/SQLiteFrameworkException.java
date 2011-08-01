package com.johna.sqlite.framework.core;

/**
 * @author N. Johnatan Flores Carmona
 *
 */
@SuppressWarnings("serial")
public class SQLiteFrameworkException extends Exception {

	/**
	 * 
	 */
	public SQLiteFrameworkException() {
		super();
	}

	/**
	 * @param message
	 */
	public SQLiteFrameworkException(String message) {
		super(message);
	}

	/**
	 * @param e
	 */
	public SQLiteFrameworkException(Throwable e) {
		super(e);
	}
	
	/**
	 * @param message
	 * @param e
	 */
	public SQLiteFrameworkException(String message, Throwable e){
		super(message, e);
	}
}
