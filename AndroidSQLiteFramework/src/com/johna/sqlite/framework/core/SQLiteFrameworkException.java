package com.johna.sqlite.framework.core;

/**
 * An exception that indicates there was an error with execution of
 * SQLiteFramework.
 * 
 * @author N. Johnatan Flores Carmona
 * @version 1.0.1
 * @since August 03, 2011
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
	public SQLiteFrameworkException(String message, Throwable e) {
		super(message, e);
	}
}
