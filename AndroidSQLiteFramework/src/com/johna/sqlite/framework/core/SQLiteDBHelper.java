package com.johna.sqlite.framework.core;

import com.johna.sqlite.framework.core.structure.Database;
import com.johna.sqlite.framework.core.xml.SQLiteXmlParser;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase.CursorFactory;

/**
 * @author N. Johnatan Flores Carmona
 *
 */
public abstract class SQLiteDBHelper extends SQLiteOpenHelper {
	private Context context;

	/**
	 * @param context
	 * @throws SQLiteFrameworkException
	 */
	public SQLiteDBHelper(Context context) throws SQLiteFrameworkException {

		super(context, SQLiteXmlParser.getDatabaseName(context), null,
				SQLiteXmlParser.getDatabaseVersion(context));

		this.context = context;
	}

	/**
	 * @param context
	 * @param factory
	 * @throws SQLiteFrameworkException
	 */
	public SQLiteDBHelper(Context context, CursorFactory factory)
			throws SQLiteFrameworkException {

		super(context, SQLiteXmlParser.getDatabaseName(context), factory,
				SQLiteXmlParser.getDatabaseVersion(context));

		this.context = context;
	}

	/* (non-Javadoc)
	 * @see android.database.sqlite.SQLiteOpenHelper#onCreate(android.database.sqlite.SQLiteDatabase)
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
	 * @param db
	 * @throws SQLiteFrameworkException
	 */
	public void initialLoad(SQLiteDatabase db) throws SQLiteFrameworkException {
		SQLiteXmlParser parser = new SQLiteXmlParser(this.context);

		Database initialLoadStructure = parser.getInitialLoadStructure();

		for (int i = 0; i < initialLoadStructure.getTables().size(); i++) {
			initialLoadStructure.insertRowOnTable(i, db);
		}
	}
}
