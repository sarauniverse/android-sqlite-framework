# **_Android SQLite Framework_** #

With this library, you will be able to manage SQLite databases in applications for the Android platform, based on both XML files and schema files (xsd).

By implementing this library in your Android application, you can perform the following tasks without the need to generate lines of java code:

<ol>
<li>Creation of Tables.</li>
<li>Creating Views.</li>
<li>Creating Triggers.</li>
<li>Creation of Indices.</li>
<li>Running bulk insert data.</li>
</ol>


This is executed by an abstract class that inherits from SQLiteOpenHelper, which reads an xml file (dbschema.xml) to be included in a folder on your android project (assets/xml). To do this, you only have to create a class that inherits from the abstract class SQLiteDBHelper, and this automatically make ​​the creation of the database when the event onCreate is generated.

If you want the application to perform an initial load of data once created the database, you only need to overload the event "create" and add a call to SQLiteDBHelper.initialLoad (SQLiteDatabase db). This method reads the xml file (initial\_load\_v.xml) containing the records you want to load that is in the same folder as the dbschema.xml.

This framework will evolve to implement other important tasks of managing SQLite databases for android applications, such as:

<ul>
<li>Backup database.</li>
<li>Restore databases.</li>
<li>Updated versions of databases.</li>
<li>Encryption and decryption databases.</li>
<li>Graphical User Interface for the generation of xml files.</li>
</ul>