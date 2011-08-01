package com.johna.sqlite.framework.core.xml;

import java.io.IOException;
import java.io.InputStream;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import android.content.Context;
import android.content.res.AssetManager;
import android.content.res.XmlResourceParser;

import com.johna.sqlite.framework.core.SQLiteFrameworkException;
import com.johna.sqlite.framework.core.structure.Database;

class SQLiteSAXParser {

	private String packageName;

	private Context context;
	private Database database;

	public SQLiteSAXParser(Context context) {
		this.context = context;
		this.database = null;

		this.context = context;
	}

	public Database databaseParse() throws SQLiteFrameworkException {

		XmlPullParser xpp = null;

		try {

			AssetManager assetManager = this.context.getAssets();
			InputStream in = assetManager.open("xml/dbschema.xml");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			xpp = factory.newPullParser();
			xpp.setInput(in, "UTF-8");

			while (xpp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlResourceParser.START_TAG) {

					String s = xpp.getName();
					System.out.println(s);

					XmlSchemaTag tagReaded = XmlSchemaTag
							.getXmlSchemaTagFrom(s);

					switch (tagReaded) {
					case DATABASE:
						extractInfoDBTag(xpp);
						break;
					case TABLE:
						extractInfoTable(xpp);
						break;
					case COLUMN:
						extractInfoColumn(xpp);
						break;
					case COLUMN_CONSTRAINT:
						extractInfoColumnConstraint(xpp);
						break;
					case COLUMN_PRIMARY_KEY:
						extractInfoColumnPrimaryKey(xpp);
						break;
					case COLUMN_CHECK:
						extractInfoColumnCheck(xpp);
						break;
					case DEFAULT:
						xpp.next();
						xpp.next();
						String tagName = xpp.getName();
						xpp.next();
						extractInfoColumnDefault(xpp, tagName);
						break;
					case COLLATE:
						extractInfoColumnCollate(xpp);
						break;
					case TABLE_CONSTRAINT:
						extractInfoTableConstraint(xpp);
						break;
					case PRIMARY_KEY:
						extractInfoPrimaryKey(xpp);
						break;
					case KEY_COLUMN:
						xpp.next();
						extractInfoKeyColumn(xpp);
						break;
					case UNIQUE:
						extractInfoUnique(xpp);
						break;
					case UNIQUE_COLUMN:
						xpp.next();
						extractInfoUniqueColumn(xpp);
						break;
					case CONSTRAINT_CHECK:
						extractInfoConstraintCheck(xpp);
						break;
					case FOREIGN_KEY:
						this.database.addTableForeignKey();
						break;
					case FOREIGN_COLUMN:
						xpp.next();
						extractInfoForeignColumn(xpp);
						break;
					case REFERENCES:
						extractInfoReferences(xpp);
						break;
					case REFERENCE_COLUMN:
						xpp.next();
						extractInfoReferenceColumn(xpp);
						break;
					case ON_DELETE:
						extractInfoOnDeleteOnUpdate(xpp);
						break;
					case ON_UPDATE:
						extractInfoOnDeleteOnUpdate(xpp);
						break;
					case DEFERRABLE:
						extractInfoDeferrable(xpp);
						break;
					case VIEW:
						extractInfoView(xpp);
						break;
					case SELECT_STATEMENT:
						xpp.next();
						extractInfoSelectStatement(xpp);
						break;
					case TRIGGER:
						extractInfoTrigger(xpp);
						break;
					case UPDATE_COLUMN:
						xpp.next();
						extractInfoUpdateColumn(xpp);
						break;
					case WHEN_RESTRICTION:
						xpp.next();
						extractWhenRestriction(xpp);
						break;
					case BEGIN_STATEMENT:
						xpp.next();
						extractBeginStatement(xpp);
						break;
					case INDEX:
						extractInfoIndex(xpp);
						break;
					case INDEX_COLUMN:
						extractInfoIndexColumn(xpp);
					default:
						break;
					}
				}

				xpp.next();
			}

		} catch (XmlPullParserException xppe) {
			throw new SQLiteFrameworkException(
					"Bad file format has caused a XmlPullParserException"
							+ " while trying to read the file " + packageName
							+ ":xml/dbschema.xml", xppe);
		} catch (IOException e) {
			throw new SQLiteFrameworkException(
					"An IOException occurred while trying to read the file "
							+ packageName + ":xml/dbschema.xml", e);
		} catch (Exception e) {
			throw new SQLiteFrameworkException(e);
		}

		return this.database;
	}

	public Database initialLoadParse(int version, String dbName)
			throws SQLiteFrameworkException {

		XmlPullParser xpp = null;

		this.database = new Database(dbName, version);

		try {

			AssetManager assetManager = this.context.getAssets();
			InputStream in = assetManager.open("xml/initial_load_" + version
					+ ".xml");

			XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
			factory.setNamespaceAware(true);
			xpp = factory.newPullParser();
			xpp.setInput(in, "UTF-8");

			while (xpp.getEventType() != XmlResourceParser.END_DOCUMENT) {
				if (xpp.getEventType() == XmlResourceParser.START_TAG) {

					String s = xpp.getName();
					System.out.println(s);

					XmlSchemaTag tagReaded = XmlSchemaTag
							.getXmlSchemaTagFrom(s);

					switch (tagReaded) {
					case TABLE:
						extractTableLoad(xpp);
						break;
					case COLUMN:
						extractColumnLoad(xpp);
						break;
					case ROW:
						this.database.addRow();
						break;
					case VALUE:
						xpp.next();
						extractValueLoad(xpp);
						break;
					default:
						break;
					}
				}

				xpp.next();
			}

		} catch (XmlPullParserException xppe) {
			throw new SQLiteFrameworkException(
					"Bad file format has caused a XmlPullParserException"
							+ " while trying to read the file " + packageName
							+ ":xml/dbschema.xml", xppe);
		} catch (IOException e) {
			throw new SQLiteFrameworkException(
					"An IOException occurred while trying to read the file "
							+ packageName + ":xml/dbschema.xml", e);
		} catch (Exception e) {
			throw new SQLiteFrameworkException(e);
		}

		return this.database;
	}

	private void extractInfoDBTag(XmlPullParser xpp) {
		this.database = new Database();

		int dbVersion = Integer.parseInt(xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.VERSION.getXmlAttrName()));

		String dbName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.DATABASE_NAME.getXmlAttrName());

		this.database.setName(dbName);
		this.database.setVersion(dbVersion);
	}

	private void extractInfoTable(XmlPullParser xpp) {
		String tableName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.TABLE_NAME.getXmlAttrName());

		this.database.addTable(tableName);
	}

	private void extractInfoColumn(XmlPullParser xpp) {
		String columnType = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.TYPE.getXmlAttrName());

		String columnName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLUMN_NAME.getXmlAttrName());

		String columnNotNull = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.NOT_NULL.getXmlAttrName());

		String columnUnique = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.UNIQUE.getXmlAttrName());

		this.database.addColumn(columnName, columnType, columnNotNull,
				columnUnique);
	}

	private void extractInfoColumnConstraint(XmlPullParser xpp) {
		String constraintName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.NAME.getXmlAttrName());
		
		this.database.addColumnConstraint(constraintName);
	}

	private void extractInfoColumnPrimaryKey(XmlPullParser xpp) {
		String strColumnAutoincrement = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLUMN_AUTOINCREMENT.getXmlAttrName());
		Boolean columnAutoincrement = Boolean
				.parseBoolean(strColumnAutoincrement);

		String conflictClause = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLUMN_CONFLICT_CLAUSE.getXmlAttrName());

		String order = xpp.getAttributeValue(null, XmlSchemaTagAttribute.ORDER
				.getXmlAttrName());
		
		this.database.addColumnPrimaryKey(order, conflictClause, columnAutoincrement);
	}

	private void extractInfoColumnCheck(XmlPullParser xpp) {
		String checkExpr = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.EXPR.getXmlAttrName());

		this.database.addColumnCheck(checkExpr);
	}

	private void extractInfoColumnDefault(XmlPullParser xpp, String tagName) {
		String defaultValue = xpp.getText();

		this.database.addColumnDefault(tagName, defaultValue);
	}

	private void extractInfoColumnCollate(XmlPullParser xpp) {
		String collationName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLLATION_NAME.getXmlAttrName());

		this.database.addColumnCollate(collationName);
	}

	private void extractInfoTableConstraint(XmlPullParser xpp) {
		String tableConstraintName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.NAME.getXmlAttrName());
		
		this.database.addTableConstraint(tableConstraintName);
	}

	private void extractInfoPrimaryKey(XmlPullParser xpp) {
		String conflictClause = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.CONFLICT_CLAUSE.getXmlAttrName());

		this.database.addPrimaryKey(conflictClause);
	}

	private void extractInfoKeyColumn(XmlPullParser xpp) {
		String keyColumn = xpp.getText();

		this.database.addKeyColumn(keyColumn);
	}

	private void extractInfoUnique(XmlPullParser xpp) {
		String conflictClause = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.CONFLICT_CLAUSE.getXmlAttrName());

		this.database.addUnique(conflictClause);
	}

	private void extractInfoUniqueColumn(XmlPullParser xpp) {
		String uniqueColumn = xpp.getText();

		this.database.addUniqueColumn(uniqueColumn);
	}

	private void extractInfoConstraintCheck(XmlPullParser xpp) {
		String checkExpr = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.EXPR.getXmlAttrName());

		this.database.addConstraintCheck(checkExpr);
	}

	private void extractInfoForeignColumn(XmlPullParser xpp) {
		String foreignColumn = xpp.getText();

		this.database.addForeignColumn(foreignColumn);
	}

	private void extractInfoReferences(XmlPullParser xpp) {
		String tableReference = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.TABLE_NAME.getXmlAttrName());

		this.database.addReferences(tableReference);
	}

	private void extractInfoReferenceColumn(XmlPullParser xpp) {
		String referenceColumn = xpp.getText();

		this.database.addReferenceColumn(referenceColumn);
	}

	private void extractInfoOnDeleteOnUpdate(XmlPullParser xpp) {
		String actionType = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.ACTION.getXmlAttrName());

		String tagName = xpp.getName();

		if (tagName.equals(XmlSchemaTag.ON_DELETE.getXmlTag()))
			this.database.addOnDelete(actionType);
		else
			this.database.addOnUpdate(actionType);
	}

	private void extractInfoDeferrable(XmlPullParser xpp) {

		boolean isNotDeferrable = Boolean.parseBoolean(xpp.getAttributeValue(
				null, XmlSchemaTagAttribute.NOT.getXmlAttrName()));

		String initially = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.INITIALLY.getXmlAttrName());
		
		this.database.addDeferrable(isNotDeferrable, initially);
	}

	private void extractInfoView(XmlPullParser xpp) {
		String viewName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.VIEW_NAME.getXmlAttrName());

		this.database.addView(viewName);
	}

	private void extractInfoSelectStatement(XmlPullParser xpp) {
		String selectStatement = xpp.getText();

		this.database.addSelectStatement(selectStatement);
	}

	private void extractInfoTrigger(XmlPullParser xpp) {
		String onTable = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.ON_TABLE.getXmlAttrName());

		String when = xpp.getAttributeValue(null, XmlSchemaTagAttribute.WHEN
				.getXmlAttrName());

		String event = xpp.getAttributeValue(null, XmlSchemaTagAttribute.EVENT
				.getXmlAttrName());

		String triggerName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.TRIGGER_NAME.getXmlAttrName());

		String forEachRow = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.FOR_EACH_ROW.getXmlAttrName());
		boolean isForEachRow = false;
		if (forEachRow != null)
			isForEachRow = Boolean.parseBoolean(forEachRow);

		this.database.addTrigger(triggerName, onTable, when, event, isForEachRow);
	}

	private void extractInfoUpdateColumn(XmlPullParser xpp) {
		String updateColumn = xpp.getText();

		this.database.addUpdateColumn(updateColumn);
	}

	private void extractWhenRestriction(XmlPullParser xpp) {
		String whenRestriction = xpp.getText();

		this.database.addWhenRestriction(whenRestriction);
	}

	private void extractBeginStatement(XmlPullParser xpp) {
		String beginStatement = xpp.getText();

		this.database.addBeginStatement(beginStatement);
	}

	private void extractInfoIndex(XmlPullParser xpp) {
		String onTable = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.ON_TABLE.getXmlAttrName());

		String indexName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.INDEX_NAME.getXmlAttrName());

		String unique = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.UNIQUE.getXmlAttrName());
		boolean isUnique = false;
		if (unique != null)
			isUnique = Boolean.parseBoolean(unique);

		this.database.addIndex(indexName, onTable, isUnique);
	}

	private void extractInfoIndexColumn(XmlPullParser xpp) {
		String columnName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLUMN_NAME.getXmlAttrName());

		String collateName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLLATE_NAME.getXmlAttrName());

		String order = xpp.getAttributeValue(null, XmlSchemaTagAttribute.ORDER
				.getXmlAttrName());

		this.database.addIndexColumn(columnName, collateName, order);
	}

	private void extractTableLoad(XmlPullParser xpp) {
		String tableName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.TABLE_NAME.getXmlAttrName());

		this.database.addTableLoad(tableName);
	}

	private void extractColumnLoad(XmlPullParser xpp) {
		String columnName = xpp.getAttributeValue(null,
				XmlSchemaTagAttribute.COLUMN_NAME.getXmlAttrName());

		this.database.addColumnLoad(columnName);
	}

	private void extractValueLoad(XmlPullParser xpp) {
		String value = xpp.getText();

		this.database.addValueLoad(value);
	}
}
