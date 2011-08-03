package com.johna.sqlite.framework.core;

import com.johna.sqlite.framework.core.structure.Database;
import com.johna.sqlite.framework.core.xml.SQLiteXmlParser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * SQLiteDBHelper is the abstract class that allows you to manage the creation
 * of a database and its version based on a structure defined in the file
 * assets/xml/dbschema.xml.
 * <p>
 * In addition, this class contains the implementation of the methods needed to
 * perform the bulk insert data in the application database. This method may be
 * used in a method override {@link #onCreate(SQLiteDatabase)} to execute an
 * initial load of data.
 * 
 * @author N. Johnatan Flores Carmona
 * @version 1.0.1
 * @since August 03, 2011
 * 
 */
public abstract class SQLiteDBHelper extends SQLiteOpenHelper {
	private Context context;

	/**
	 * Create a helper object to create, open, and/or manage a database. The
	 * database is not actually created or opened until one of
	 * {@link #getWritableDatabase()} or {@link #getReadableDatabase()} is
	 * called.
	 * 
	 * @param context
	 *            to use to open or create the database
	 */
	public SQLiteDBHelper(Context context) {

		super(context, SQLiteXmlParser.getDatabaseName(context), null,
				SQLiteXmlParser.getDatabaseVersion(context));

		this.context = context;
	}

	/**
	 * Create a helper object to create, open, and/or manage a database. The
	 * database is not actually created or opened until one of
	 * {@link #getWritableDatabase()} or {@link #getReadableDatabase()} is
	 * called.
	 * 
	 * @param context
	 *            to use to open or create the database
	 * @param factory
	 *            to use for creating cursor objects, or null for the default
	 */
	public SQLiteDBHelper(Context context, CursorFactory factory) {

		super(context, SQLiteXmlParser.getDatabaseName(context), factory,
				SQLiteXmlParser.getDatabaseVersion(context));

		this.context = context;
	}

	/*
	 * Called when the database is created for the first time. This is where the
	 * creation of tables and the initial population of the tables should
	 * happen. <p> This is where is the creation of tables in the database based
	 * on dbschema file.
	 * 
	 * @param db The database.
	 */
	@Override
	public void onCreate(SQLiteDatabase db) {
		this.createDataBaseStructure(db);
	}

	private void createDataBaseStructure(SQLiteDatabase db) {

		SQLiteXmlParser parser = new SQLiteXmlParser(this.context);
		try {
			Database dbStructure = parser.getDatabaseStructure();

			for (int i = 0; i < dbStructure.getTables().size(); i++) {
				db.execSQL(dbStructure.getTableCreateStatement(i));
			}

			for (int i = 0; i < dbStructure.getViews().size(); i++) {
				db.execSQL(dbStructure.getViewCreateStatement(i));
			}

			for (int i = 0; i < dbStructure.getTriggers().size(); i++) {
				db.execSQL(dbStructure.getTriggersCreateStatement(i));
			}

		} catch (SQLiteFrameworkException e) {
			return;
		}
	}

	/**
	 * This method allows you to run the bulk insert records in the database
	 * based on the file assets/xml/initial_load_{version}.xml. If you want to
	 * load the initial data must override the onCreate method and to add the
	 * following lines of code:
	 * <p>
	 * 
	 * <pre>
	 * <code>@Override</code>
	 * public void onCreate(SQLiteDatabase db) {
	 * 		super.onCreate(db);
	 * 		try {
	 * 			this.bulkInsert(db);
	 * 		} catch (SQLiteFrameworkException e) {
	 * 			e.printStackTrace();
	 * 		}
	 * }
	 * </pre>
	 * 
	 * @param db
	 *            The database.
	 * @throws SQLiteFrameworkException
	 *             If was an error with SQL parsing or execution. The message
	 *             contains information from the table and the index of the
	 *             record that caused the error.
	 */
	public void bulkInsert(SQLiteDatabase db) throws SQLiteFrameworkException {
		SQLiteXmlParser parser = new SQLiteXmlParser(this.context);

		Database initialLoadStructure = parser.getBulkInsertStructure();

		for (int i = 0; i < initialLoadStructure.getTables().size(); i++) {
			initialLoadStructure.insertRowOnTable(i, db);
		}
	}
}
