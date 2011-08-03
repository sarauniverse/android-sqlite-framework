package com.johna.sqlite.framework.core.xml;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import android.content.Context;
import android.content.res.AssetManager;

import com.johna.sqlite.framework.core.structure.Database;

class SQLiteDOMParser {

	private Context context;

	public SQLiteDOMParser(Context context) {
		this.context = context;
	}

	public Database getDataBaseInfo() throws FileNotFoundException,
			SAXException {

		Element root = this.parse();

		NodeList nodes = root.getElementsByTagName(XmlSchemaTag.DATABASE
				.getXmlTag());

		NamedNodeMap nodeMap = nodes.item(0).getAttributes();

		String databaseName = nodeMap.getNamedItem(
				XmlSchemaTagAttribute.DATABASE_NAME.getXmlAttrName())
				.getNodeValue();

		int version = 0;

		try {
			version = Integer.parseInt(nodeMap.getNamedItem(
					XmlSchemaTagAttribute.VERSION.getXmlAttrName())
					.getNodeValue());
		} catch (NumberFormatException e) {
			throw new NumberFormatException(
					"Dbschema.xml file has not defined a valid version for the database. "
							+ "The version of a database must be an integer.");
		}

		return new Database(databaseName, version);
	}

	private Element parse() throws FileNotFoundException, SAXException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;

		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
		}

		try {
			AssetManager assetManager = this.context.getAssets();
			InputStream in = assetManager.open("xml/dbschema.xml");

			Document dom = builder.parse(in);

			return dom.getDocumentElement();
		} catch (SAXException e) {
			throw e;
		} catch (IOException e) {
			throw new FileNotFoundException(
					"assets/xml/dbschema.xml file not found.");
		}
	}
}
