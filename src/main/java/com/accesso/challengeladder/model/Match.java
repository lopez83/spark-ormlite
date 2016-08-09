package com.accesso.challengeladder.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "match")
public class Match
{

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField(columnName = "user_id", foreign = true)
	private User user;

	@DatabaseField(columnName = "match_timestamp")
	private Date matchTimestamp;

	@DatabaseField(columnName = "creation_timestamp")
	private Date creationTimestamp;

	@DatabaseField(columnName = "victor_user_id", foreign = true)
	private User victorUser;

	@DatabaseField(columnName = "status_id", foreign = true)
	private MatchStatus matchStatus;

	@DatabaseField(columnName = "creator_user_id", foreign = true)
	private User creatorUser;

	public Match()
	{
		// ORMLite needs a no-arg constructor
	}

	public int getId()
	{
		return this.id;
	}

	public Date getMatchTimestamp()
	{
		return matchTimestamp;
	}

	public Date getCreationTimestamp()
	{
		return creationTimestamp;
	}

	public void setMatchTimestamp(Date matchTimestamp)
	{
		this.matchTimestamp = matchTimestamp;
	}

	public void setCreationTimestamp(Date creationTimestamp)
	{
		this.creationTimestamp = creationTimestamp;
	}

	public User getUser()
	{
		return user;
	}

	public User getVictorUser()
	{
		return victorUser;
	}

	public MatchStatus getMatchStatus()
	{
		return matchStatus;
	}

	public User getCreatorUser()
	{
		return creatorUser;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public void setVictorUser(User victorUser)
	{
		this.victorUser = victorUser;
	}

	public void setMatchStatus(MatchStatus matchStatus)
	{
		this.matchStatus = matchStatus;
	}

	public void setCreatorUser(User creatorUser)
	{
		this.creatorUser = creatorUser;
	}

}