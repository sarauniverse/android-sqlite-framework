package com.johna.sqlite.framework.core.structure;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;

/**
 * @author N. Johnatan Flores Carmona
 *
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
	 * 
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
	 * @param name
	 * @param version
	 */
	public Database(String name, int version) {
		this();
		this.name = name;
		this.version = version;
	}

	/**
	 * @return
	 */
	public String getName() {
		return name;
	}

	/**
	 * @return
	 */
	public int getVersion() {
		return version;
	}

	/**
	 * @return
	 */
	public ArrayList<Table> getTables() {
		return tables;
	}

	/**
	 * @return
	 */
	public ArrayList<Index> getIndexes() {
		return indixes;
	}

	/**
	 * @return
	 */
	public ArrayList<Trigger> getTriggers() {
		return triggers;
	}

	/**
	 * @return
	 */
	public ArrayList<View> getViews() {
		return views;
	}

	/**
	 * @param name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @param version
	 */
	public void setVersion(int version) {
		this.version = version;
	}

	/**
	 * 
	 */
	public void addTableForeignKey() {
		this.currentForeignKey = new TableForeignKey();
		this.currentTableConstraints.createForeignKey(this.currentForeignKey);
	}

	/**
	 * 
	 */
	public void addRow() {
		this.currentRow = new Row();
		this.currentTable.addRow(this.currentRow);
	}

	/**
	 * @param tableName
	 */
	public void addTable(String tableName) {
		this.currentTable = new Table(tableName);
		this.tables.add(currentTable);
	}

	/**
	 * @param columnName
	 * @param columnType
	 * @param columnNotNull
	 * @param columnUnique
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
	 * @param constraintName
	 */
	public void addColumnConstraint(String constraintName) {
		this.currentColumn.createConstraint(constraintName);
	}

	/**
	 * @param order
	 * @param conflictClause
	 * @param columnAutoincrement
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
	 * @param checkExpr
	 */
	public void addColumnCheck(String checkExpr) {
		this.currentColumn.createCheck(checkExpr);
	}

	/**
	 * @param tagName
	 * @param defaultValue
	 */
	public void addColumnDefault(String tagName, String defaultValue) {
		DefaultType defaultType = DefaultType.getDefaultTypeFrom(tagName);

		this.currentColumn.createDefaultValue(defaultType, defaultValue);
	}

	/**
	 * @param collationName
	 */
	public void addColumnCollate(String collationName) {
		this.currentColumn.createCollate(collationName);
	}

	/**
	 * @param tableConstraintName
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
	 * @param conflictClause
	 */
	public void addPrimaryKey(String conflictClause) {
		this.currentTableConstraints.createPrimaryKey(ConflictClause
				.getConflictClauseFrom(conflictClause));
	}

	/**
	 * @param keyColumn
	 */
	public void addKeyColumn(String keyColumn) {
		this.currentTableConstraints.getPrimaryKey().addColumnKey(keyColumn);
	}

	/**
	 * @param conflictClause
	 */
	public void addUnique(String conflictClause) {
		this.currentTableConstraints.createUnique(ConflictClause
				.getConflictClauseFrom(conflictClause));
	}

	/**
	 * @param uniqueColumn
	 */
	public void addUniqueColumn(String uniqueColumn) {
		this.currentTableConstraints.getUnique().addColumnKey(uniqueColumn);
	}

	/**
	 * @param checkExpr
	 */
	public void addConstraintCheck(String checkExpr) {
		this.currentTableConstraints.createCheck(checkExpr);
	}

	/**
	 * @param foreignColumn
	 */
	public void addForeignColumn(String foreignColumn) {
		this.currentForeignKey.addColumn(foreignColumn);
	}

	/**
	 * @param tableReference
	 */
	public void addReferences(String tableReference) {
		this.currentForeignKey.createReference(tableReference);
	}

	/**
	 * @param referenceColumn
	 */
	public void addReferenceColumn(String referenceColumn) {
		this.currentForeignKey.getReference().addColumnReference(
				referenceColumn);
	}

	/**
	 * @param actionType
	 */
	public void addOnDelete(String actionType) {
		ActionType action = ActionType.getActionTypeFrom(actionType);

		this.currentForeignKey.getReference().setOnDelete(action);
	}

	/**
	 * @param actionType
	 */
	public void addOnUpdate(String actionType) {
		ActionType action = ActionType.getActionTypeFrom(actionType);

		this.currentForeignKey.getReference().setOnUpdate(action);
	}

	/**
	 * @param isNotDeferrable
	 * @param initially
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
	 * @param viewName
	 */
	public void addView(String viewName) {
		this.currentView = new View(viewName);
		this.views.add(this.currentView);
	}

	/**
	 * @param selectStatement
	 */
	public void addSelectStatement(String selectStatement) {
		this.currentView.setSelectStatement(selectStatement);
	}

	/**
	 * @param triggerName
	 * @param onTable
	 * @param when
	 * @param event
	 * @param isForEachRow
	 */
	public void addTrigger(String triggerName, String onTable, String when,
			String event, boolean isForEachRow) {
		this.currentTrigger = new Trigger(onTable, WhenTriggerType
				.valueOf(when), EventType.getEventTriggerTypeFrom(event),
				triggerName, isForEachRow);

		this.triggers.add(this.currentTrigger);
	}
	
	/**
	 * @param updateColumn
	 */
	public void addUpdateColumn(String updateColumn){
		this.currentTrigger.addUpdateColumn(updateColumn);
	}
	
	/**
	 * @param whenRestriction
	 */
	public void addWhenRestriction(String whenRestriction){
		this.currentTrigger.setWhenRestriction(whenRestriction);
	}
	
	/**
	 * @param beginStatement
	 */
	public void addBeginStatement(String beginStatement){
		this.currentTrigger.setBeginStatement(beginStatement);
	}
	
	/**
	 * @param indexName
	 * @param onTable
	 * @param isUnique
	 */
	public void addIndex(String indexName, String onTable, boolean isUnique){
		this.currentIndex = new Index(indexName, onTable, isUnique);
		this.indixes.add(this.currentIndex);
	}
	
	/**
	 * @param columnName
	 * @param collateName
	 * @param order
	 */
	public void addIndexColumn(String columnName, String collateName, String order){
		if (order != null)
			this.currentIndex.addIndexColumn(columnName, collateName, OrderType
					.valueOf(order));
		else
			this.currentIndex.addIndexColumn(columnName, collateName, null);
	}
	
	/**
	 * @param tableName
	 */
	public void addTableLoad(String tableName){
		this.currentTable = new Table(tableName);
		this.tables.add(this.currentTable);
	}
	
	/**
	 * @param columnName
	 */
	public void addColumnLoad(String columnName){
		Column column = new Column(columnName);
		this.currentTable.addColumn(column);
	}
	
	/**
	 * @param value
	 */
	public void addValueLoad(String value){
		this.currentRow.addValue(value);
	}
	
	/**
	 * @param index
	 * @return
	 */
	public String getTableCreateStatement(int index){
		return this.tables.get(index).getCreateStatement();
	}
	
	/**
	 * @param index
	 * @return
	 */
	public String getViewCreateStatement(int index){
		return this.views.get(index).getCreateStatement();
	}
	
	/**
	 * @param index
	 * @return
	 */
	public String getTriggersCreateStatement(int index){
		return this.triggers.get(index).getCreateStatement();
	}
	
	/**
	 * @param index
	 * @param db
	 */
	public void insertRowOnTable(int index, SQLiteDatabase db){
		this.tables.get(index).insertRows(db);
	}
}
