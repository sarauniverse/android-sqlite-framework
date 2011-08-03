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

import java.io.FileNotFoundException;

import org.xml.sax.SAXException;

import android.content.Context;

import com.johna.sqlite.framework.core.SQLiteFrameworkException;
import com.johna.sqlite.framework.core.structure.Database;

/**
 * SQLiteXmlParser is the class to analyze the different xml files in the folder
 * assets/xml to obtain information from the database such as name and version.
 * <p>
 * Additionally, this class implements the functionality to extract the
 * structure of the database defined in the dbschema.xml file, and the records
 * that be added in the bulk insert operation.
 * 
 * @author N. Johnatan Flores Carmona
 * @version 1.0.1
 * @since August 03, 2011
 */
public class SQLiteXmlParser {

	private Context context;
	private Database database;

	/**
	 * Create an object that can read xml files in the folder assets/xml.
	 * 
	 * @param context
	 *            to use to open and read the xml file
	 */
	public SQLiteXmlParser(Context context) {
		this.context = context;
		this.database = null;

		validateXmlFile();
	}

	/**
	 * Return the object {@link Database} with the structure of the database
	 * defined in the file dbschema.xml
	 * 
	 * @return the object Database with the structure of the database
	 * @throws SQLiteFrameworkException
	 */
	public Database getDatabaseStructure() throws SQLiteFrameworkException {

		validateXmlFile();

		if (database == null)
			getDataBaseFromXml();

		return database;
	}

	/**
	 * Return the object with the structure of the records to be added in the
	 * process of bulk insert.
	 * 
	 * @return the object with the structure of the records
	 * @throws SQLiteFrameworkException
	 *             If there is an error in the xml file.
	 */
	public Database getBulkInsertStructure() throws SQLiteFrameworkException {
		SQLiteSAXParser saxParser = new SQLiteSAXParser(context);

		int version = SQLiteXmlParser.getDatabaseVersion(context);

		String dbName = SQLiteXmlParser.getDatabaseName(context);

		return saxParser.bulkInsertParse(version, dbName);
	}

	/**
	 * Returns the name assigned to the database
	 * 
	 * @param context
	 *            to use to open and read the xml file
	 * @return the name of the database
	 */
	public static String getDatabaseName(Context context) {
		SQLiteDOMParser parser = new SQLiteDOMParser(context);

		try {
			return parser.getDataBaseInfo().getName();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		} catch (SAXException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
	}

	/**
	 * Returns the version assigned to the database
	 * 
	 * @param context
	 *            to use to open and read the xml file
	 * @return the version of the database
	 */
	public static int getDatabaseVersion(Context context) {
		SQLiteDOMParser parser = new SQLiteDOMParser(context);

		try {
			return parser.getDataBaseInfo().getVersion();
		} catch (FileNotFoundException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		} catch (SAXException e) {
			throw new IllegalArgumentException(e.getMessage(), e);
		}
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
