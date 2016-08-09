package com.accesso.challengeladder.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ranking")
public class Ranking
{

	@DatabaseField(columnName = "rank_id", generatedId = true)
	private int id;

	@DatabaseField(columnName = "user_id")
	private int user_id;

	@DatabaseField
	private Date timestamp;

	public Ranking()
	{
		// ORMLite needs a no-arg constructor
	}

	public int getId()
	{
		return this.id;
	}

	public int getUser_id()
	{
		return user_id;
	}

	public Date getTimestamp()
	{
		return timestamp;
	}

	public void setUser_id(int user_id)
	{
		this.user_id = user_id;
	}

	public void setTimestamp(Date timestamp)
	{
		this.timestamp = timestamp;
	}

}