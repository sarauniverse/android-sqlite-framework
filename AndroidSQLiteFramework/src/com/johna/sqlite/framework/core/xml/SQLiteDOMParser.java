package com.johna.sqlite.framework.core.xml;

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

import com.johna.sqlite.framework.core.SQLiteFrameworkException;
import com.johna.sqlite.framework.core.structure.Database;

class SQLiteDOMParser {

	private Context context;

	public SQLiteDOMParser(Context context) {
		this.context = context;
	}

	public Database getDataBaseInfo() throws SQLiteFrameworkException {

		Element root = this.parse();

		NodeList nodes = root.getElementsByTagName(XmlSchemaTag.DATABASE
				.getXmlTag());

		NamedNodeMap nodeMap = nodes.item(0).getAttributes();

		String databaseName = nodeMap.getNamedItem(
				XmlSchemaTagAttribute.DATABASE_NAME.getXmlAttrName())
				.getNodeValue();

		int version = Integer.parseInt(nodeMap.getNamedItem(
				XmlSchemaTagAttribute.VERSION.getXmlAttrName()).getNodeValue());

		return new Database(databaseName, version);
	}

	private Element parse() throws SQLiteFrameworkException {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();

		try {
			AssetManager assetManager = this.context.getAssets();
			InputStream in =  assetManager.open("xml/dbschema.xml");

			DocumentBuilder builder = factory.newDocumentBuilder();
			Document dom = builder.parse(in);

			return dom.getDocumentElement();
		} catch (ParserConfigurationException e) {
			throw new SQLiteFrameworkException(
					"An ParserConfigurationException occurred while trying to read the xml file.",
					e);
		} catch (SAXException e) {
			throw new SQLiteFrameworkException(
					"An SAXException occurred while trying to read the xml file.",
					e);
		} catch (IOException e) {
			throw new SQLiteFrameworkException(
					"An IOException occurred while trying to read the xml file.",
					e);
		}
	}
}
