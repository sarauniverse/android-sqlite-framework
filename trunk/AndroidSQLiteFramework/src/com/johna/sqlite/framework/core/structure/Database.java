package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;

import com.johna.sqlite.framework.core.SQLiteFrameworkException;

import android.database.sqlite.SQLiteDatabase;

/**
 * This class contains the necessary information that describes the structure of
 * the SQLite database such as Tables, Views, Triggers and Indexes.
 * 
 * @author N. Johnatan Flores Carmona
 * @version 1.0.1
 * @since August 03, 2011
 */
public class Database {

	private String name;
	private int version;

	private ArrayList<Table> tables;
	private ArrayList<Index> indixes;
	private ArrayList<Trigger> triggers;
	private ArrayList<View> views;

	private Table currentTable;
	private Column currentColumn;
	private TableConstraint currentTableConstraints;
	private TableForeignKey currentForeignKey;
	private View currentView;
	private Trigger currentTrigger;
	private Index currentIndex;
	private Row currentRow;

	/**
	 * Creates an object to store the definition of the structure of a SQLite
	 * database.
	 */
	public Database() {
		tables = new ArrayList<Table>();
		indixes = new ArrayList<Index>();
		triggers = new ArrayList<Trigger>();
		views = new ArrayList<View>();

		this.currentTable = null;
		this.currentColumn = null;
		this.currentTableConstraints = null;
		this.currentForeignKey = null;
		this.currentView = null;
		this.currentTrigger = null;
		this.currentIndex = null;
		this.currentRow = null;
	}

	/**
	 * Creates an object to store the definition of the structure of a SQLite
	 * database.
	 * 
	 * @param name
	 *            assigned to the database
	 * @param version
	 *            mapped to the database
	 */
	public Database(String name, int version) {
		this();
		this.name = name;
		this.version = version;
	}

	/**
	 * Returns the name assigned to the database
	 * 
	 * @return the name of the database
	 */
	public String getName() {
		return name;
	}

	/**
	 * Returns the version assigned to the database
	 * 
	 * @return the version of the database
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * Returns the collection of tables in the database
	 * 
	 * @return the collection of tables in the database
	 */
	public ArrayList<Table> getTables() {
		return tables;
	}

	/**
	 * Returns the collection of indexes in the database
	 * 
	 * @return the collection of indexes in the database
	 */
	public ArrayList<Index> getIndexes() {
		return indixes;
	}

	/**
	 * Returns the collection of triggers in the database
	 * 
	 * @return the collection of triggers in the database
	 */
	public ArrayList<Trigger> getTriggers() {
		return triggers;
	}

	/**
	 * Returns the collection of views in the database
	 * 
	 * @return the collection of views in the database
	 */
	public ArrayList<View> getViews() {
		return views;
	}

	/**
	 * Sets the name assigned to the database
	 * 
	 * @param name
	 *            assigned to the database
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Sets the version assigned to the database
	 * 
	 * @param version
	 *            assigned to the database
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 */
	public void addTableForeignKey() {
		this.currentForeignKey = new TableForeignKey();
		this.currentTableConstraints.createForeignKey(this.currentForeignKey);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 */
	public void addRow() {
		this.currentRow = new Row();
		this.currentTable.addRow(this.currentRow);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param tableName
	 *            For internal use of the library.
	 */
	public void addTable(String tableName) {
		this.currentTable = new Table(tableName);
		this.tables.add(currentTable);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param columnName
	 *            For internal use of the library.
	 * @param columnType
	 *            For internal use of the library.
	 * @param columnNotNull
	 *            For internal use of the library.
	 * @param columnUnique
	 *            For internal use of the library.
	 */
	public void addColumn(String columnName, String columnType,
			String columnNotNull, String columnUnique) {
		this.currentColumn = new Column(columnName, ColumnType
				.getColumnTypeFrom(columnType));

		if (columnNotNull != null)
			this.currentColumn.createNotNull(ConflictClause
					.getConflictClauseFrom(columnNotNull));

		if (columnUnique != null)
			this.currentColumn.createUnique(ConflictClause
					.getConflictClauseFrom(columnUnique));

		this.currentTable.addColumn(this.currentColumn);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param constraintName
	 *            For internal use of the library.
	 */
	public void addColumnConstraint(String constraintName) {
		this.currentColumn.createConstraint(constraintName);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param order
	 *            For internal use of the library.
	 * @param conflictClause
	 *            For internal use of the library.
	 * @param columnAutoincrement
	 *            For internal use of the library.
	 */
	public void addColumnPrimaryKey(String order, String conflictClause,
			boolean columnAutoincrement) {
		OrderType orderType = null;
		if (order != null)
			orderType = OrderType.valueOf(order);

		this.currentColumn.createPrimaryKey(orderType, ConflictClause
				.getConflictClauseFrom(conflictClause), columnAutoincrement);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param checkExpr
	 *            For internal use of the library.
	 */
	public void addColumnCheck(String checkExpr) {
		this.currentColumn.createCheck(checkExpr);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param tagName
	 *            For internal use of the library.
	 * @param defaultValue
	 *            For internal use of the library.
	 */
	public void addColumnDefault(String tagName, String defaultValue) {
		DefaultType defaultType = DefaultType.getDefaultTypeFrom(tagName);

		this.currentColumn.createDefaultValue(defaultType, defaultValue);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param collationName
	 *            For internal use of the library.
	 */
	public void addColumnCollate(String collationName) {
		this.currentColumn.createCollate(collationName);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param tableConstraintName
	 *            For internal use of the library.
	 */
	public void addTableConstraint(String tableConstraintName) {
		if (tableConstraintName != null)
			this.currentTableConstraints = new TableConstraint(
					tableConstraintName);
		else
			this.currentTableConstraints = new TableConstraint();

		this.currentTable.addConstraints(this.currentTableConstraints);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param conflictClause
	 *            For internal use of the library.
	 */
	public void addPrimaryKey(String conflictClause) {
		this.currentTableConstraints.createPrimaryKey(ConflictClause
				.getConflictClauseFrom(conflictClause));
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param keyColumn
	 *            For internal use of the library.
	 */
	public void addKeyColumn(String keyColumn) {
		this.currentTableConstraints.getPrimaryKey().addColumnKey(keyColumn);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param conflictClause
	 *            For internal use of the library.
	 */
	public void addUnique(String conflictClause) {
		this.currentTableConstraints.createUnique(ConflictClause
				.getConflictClauseFrom(conflictClause));
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param uniqueColumn
	 *            For internal use of the library.
	 */
	public void addUniqueColumn(String uniqueColumn) {
		this.currentTableConstraints.getUnique().addColumnKey(uniqueColumn);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param checkExpr
	 *            For internal use of the library.
	 */
	public void addConstraintCheck(String checkExpr) {
		this.currentTableConstraints.createCheck(checkExpr);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param foreignColumn
	 *            For internal use of the library.
	 */
	public void addForeignColumn(String foreignColumn) {
		this.currentForeignKey.addColumn(foreignColumn);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param tableReference
	 *            For internal use of the library.
	 */
	public void addReferences(String tableReference) {
		this.currentForeignKey.createReference(tableReference);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param referenceColumn
	 *            For internal use of the library.
	 */
	public void addReferenceColumn(String referenceColumn) {
		this.currentForeignKey.getReference().addColumnReference(
				referenceColumn);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param actionType
	 *            For internal use of the library.
	 */
	public void addOnDelete(String actionType) {
		ActionType action = ActionType.getActionTypeFrom(actionType);

		this.currentForeignKey.getReference().setOnDelete(action);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param actionType
	 *            For internal use of the library.
	 */
	public void addOnUpdate(String actionType) {
		ActionType action = ActionType.getActionTypeFrom(actionType);

		this.currentForeignKey.getReference().setOnUpdate(action);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param isNotDeferrable
	 *            For internal use of the library.
	 * @param initially
	 *            For internal use of the library.
	 */
	public void addDeferrable(boolean isNotDeferrable, String initially) {
		boolean initiallyDeferred = false;
		boolean initiallyImmediate = false;

		if (initially != null) {
			if (initially.equals(InitiallyType.DEFERRED.getInitiallyValue()))
				initiallyDeferred = true;
			else
				initiallyImmediate = true;
		}

		this.currentForeignKey.getReference().createDeferrable(isNotDeferrable,
				initiallyDeferred, initiallyImmediate);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param viewName
	 *            For internal use of the library.
	 */
	public void addView(String viewName) {
		this.currentView = new View(viewName);
		this.views.add(this.currentView);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param selectStatement
	 *            For internal use of the library.
	 */
	public void addSelectStatement(String selectStatement) {
		this.currentView.setSelectStatement(selectStatement);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param triggerName
	 *            For internal use of the library.
	 * @param onTable
	 *            For internal use of the library.
	 * @param when
	 *            For internal use of the library.
	 * @param event
	 *            For internal use of the library.
	 * @param isForEachRow
	 *            For internal use of the library.
	 */
	public void addTrigger(String triggerName, String onTable, String when,
			String event, boolean isForEachRow) {
		this.currentTrigger = new Trigger(onTable, WhenTriggerType
				.valueOf(when), EventType.getEventTriggerTypeFrom(event),
				triggerName, isForEachRow);

		this.triggers.add(this.currentTrigger);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param updateColumn
	 *            For internal use of the library.
	 */
	public void addUpdateColumn(String updateColumn) {
		this.currentTrigger.addUpdateColumn(updateColumn);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param whenRestriction
	 *            For internal use of the library.
	 */
	public void addWhenRestriction(String whenRestriction) {
		this.currentTrigger.setWhenRestriction(whenRestriction);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param beginStatement
	 *            For internal use of the library.
	 */
	public void addBeginStatement(String beginStatement) {
		this.currentTrigger.setBeginStatement(beginStatement);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param indexName
	 *            For internal use of the library.
	 * @param onTable
	 *            For internal use of the library.
	 * @param isUnique
	 *            For internal use of the library.
	 */
	public void addIndex(String indexName, String onTable, boolean isUnique) {
		this.currentIndex = new Index(indexName, onTable, isUnique);
		this.indixes.add(this.currentIndex);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param columnName
	 *            For internal use of the library.
	 * @param collateName
	 *            For internal use of the library.
	 * @param order
	 *            For internal use of the library.
	 */
	public void addIndexColumn(String columnName, String collateName,
			String order) {
		if (order != null)
			this.currentIndex.addIndexColumn(columnName, collateName, OrderType
					.valueOf(order));
		else
			this.currentIndex.addIndexColumn(columnName, collateName, null);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param tableName
	 *            For internal use of the library.
	 */
	public void addTableLoad(String tableName) {
		this.currentTable = new Table(tableName);
		this.tables.add(this.currentTable);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param columnName
	 *            For internal use of the library.
	 */
	public void addColumnLoad(String columnName) {
		Column column = new Column(columnName);
		this.currentTable.addColumn(column);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param value
	 *            For internal use of the library.
	 */
	public void addValueLoad(String value) {
		this.currentRow.addValue(value);
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param index
	 *            For internal use of the library.
	 * @return
	 */
	public String getTableCreateStatement(int index) {
		return this.tables.get(index).getCreateStatement();
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param index
	 *            For internal use of the library.
	 * @return
	 */
	public String getViewCreateStatement(int index) {
		return this.views.get(index).getCreateStatement();
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param index
	 *            For internal use of the library.
	 * @return
	 */
	public String getTriggersCreateStatement(int index) {
		return this.triggers.get(index).getCreateStatement();
	}

	/**
	 * For internal use of the library. Using this method in code external to
	 * the library can cause erratic behavior of the API.
	 * 
	 * @param index
	 *            For internal use of the library.
	 * @param db
	 *            For internal use of the library.
	 * @throws SQLiteFrameworkException
	 */
	public void insertRowOnTable(int index, SQLiteDatabase db)
			throws SQLiteFrameworkException {
		this.tables.get(index).insertRows(db);
	}
}
