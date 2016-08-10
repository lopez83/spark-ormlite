package com.accesso.challengeladder.utils;

import java.io.IOException;
import java.sql.SQLException;

import com.j256.ormlite.jdbc.JdbcConnectionSource;
import com.j256.ormlite.support.ConnectionSource;

public class DBHelper
{

	private String databaseUrl;
	private String userDb;
	private String passDb;

	public DBHelper() throws IOException
	{

		// Properties prop = new Properties();
		// prop.load(DBHelper.class.getClassLoader().getResourceAsStream("db.properties"));

		// TODO - fix these later
		this.databaseUrl = "jdbc:mysql://192.168.2.246/accesso_table_tennis";
		this.userDb = "dba";
		this.passDb = "sql";

		// this.databaseUrl = prop.getProperty("db.url");
		// this.userDb = prop.getProperty("db.user");
		// this.passDb = prop.getProperty("db.password");
	}

	public ConnectionSource getConnectionSource() throws SQLException
	{
		ConnectionSource connectionSource = new JdbcConnectionSource(databaseUrl);
		((JdbcConnectionSource) connectionSource).setUsername(userDb);
		((JdbcConnectionSource) connectionSource).setPassword(passDb);

		return connectionSource;
	}

}
