package com.johna.sqlite.framework.core.xml;

//import java.io.InputStream;
//import java.net.URL;
//
//import javax.xml.XMLConstants;
//import javax.xml.parsers.DocumentBuilder;
//import javax.xml.parsers.DocumentBuilderFactory;
//import javax.xml.transform.Source;
//import javax.xml.transform.dom.DOMSource;
//import javax.xml.transform.stream.StreamSource;
//import javax.xml.validation.Schema;
//import javax.xml.validation.SchemaFactory;
//import javax.xml.validation.Validator;
//
//import org.w3c.dom.Document;
//import org.xml.sax.SAXException;

import android.content.Context;

import com.johna.sqlite.framework.core.SQLiteFrameworkException;
import com.johna.sqlite.framework.core.structure.Database;

/**
 * @author N. Johnatan Flores Carmona
 *
 */
public class SQLiteXmlParser {

	private Context context;
	private Database database;

	/**
	 * @param context
	 */
	public SQLiteXmlParser(Context context) {
		this.context = context;
		this.database = null;

		validateXmlFile();
	}

	/**
	 * @return
	 * @throws SQLiteFrameworkException
	 */
	public Database getDatabaseStructure() throws SQLiteFrameworkException {

		validateXmlFile();

		if (database == null)
			getDataBaseFromXml();

		return database;
	}

	/**
	 * @return
	 * @throws SQLiteFrameworkException
	 */
	public Database getInitialLoadStructure() throws SQLiteFrameworkException {
		SQLiteSAXParser saxParser = new SQLiteSAXParser(context);

		int version = SQLiteXmlParser.getDatabaseVersion(context);

		String dbName = SQLiteXmlParser.getDatabaseName(context);

		return saxParser.initialLoadParse(version, dbName);
	}

	/**
	 * @param context
	 * @return
	 * @throws SQLiteFrameworkException
	 */
	public static String getDatabaseName(Context context)
			throws SQLiteFrameworkException {
		SQLiteDOMParser parser = new SQLiteDOMParser(context);

		return parser.getDataBaseInfo().getName();
	}

	/**
	 * @param context
	 * @return
	 * @throws SQLiteFrameworkException
	 */
	public static int getDatabaseVersion(Context context)
			throws SQLiteFrameworkException {
		SQLiteDOMParser parser = new SQLiteDOMParser(context);

		return parser.getDataBaseInfo().getVersion();
	}

	private void validateXmlFile() {
		// TODO Create the schema validation block
		// try {
		// AssetManager assetManager = this.context.getAssets();
		// InputStream in = assetManager.open("xml/dbschema.xml");
		//
		// DocumentBuilderFactory parserFactory = DocumentBuilderFactory
		// .newInstance();
		// parserFactory.setNamespaceAware(true);
		// DocumentBuilder parser = parserFactory.newDocumentBuilder();
		// Document document = parser.parse(in);
		//
		// SchemaFactory factory = SchemaFactory
		// .newInstance(XMLConstants.NULL_NS_URI);
		//
		// URL rssUrl = new URL(
		// "http://localhost/CPMEsquemas/sqlite/database.xsd");
		//
		// Source schemaFile = new StreamSource(rssUrl.openConnection()
		// .getInputStream());
		// Schema schema = factory.newSchema(schemaFile);
		//
		// Validator validator = schema.newValidator();
		//
		// try {
		// validator.validate(new DOMSource(document));
		// } catch (SAXException e) {
		// // instance document is invalid!
		// }
		// } catch (Exception e) {
		// e.printStackTrace();
		// }
	}

	private void getDataBaseFromXml() throws SQLiteFrameworkException {
		SQLiteSAXParser saxParser = new SQLiteSAXParser(context);

		this.database = saxParser.databaseParse();
	}
}
