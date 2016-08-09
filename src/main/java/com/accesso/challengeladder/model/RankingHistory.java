package com.accesso.challengeladder.model;

import java.util.Date;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "ranking_history")
public class RankingHistory
{

	@DatabaseField(generatedId = true)
	private int id;

	@DatabaseField
	private int ranking;

	@DatabaseField(columnName = "user_id")
	private User user;

	@DatabaseField(columnName = "match_id")
	private Match match;

	@DatabaseField(columnName = "updated_at")
	private Date updatedAt;

	public RankingHistory()
	{
		// ORMLite needs a no-arg constructor
	}

	public int getId()
	{
		return this.id;
	}

	public int getRanking()
	{
		return ranking;
	}

	public Date getUpdatedAt()
	{
		return updatedAt;
	}

	public void setRanking(int ranking)
	{
		this.ranking = ranking;
	}

	public User getUser()
	{
		return user;
	}

	public Match getMatch()
	{
		return match;
	}

	public void setUser(User user)
	{
		this.user = user;
	}

	public void setMatch(Match match)
	{
		this.match = match;
	}

	public void setUpdatedAt(Date updatedAt)
	{
		this.updatedAt = updatedAt;
	}
}