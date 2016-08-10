package com.accesso.challengeladder.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.accesso.challengeladder.model.Match;
import com.accesso.challengeladder.utils.DBHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class MatchService
{
	private ConnectionSource connectionSource;
	private Dao<Match, String> matchDao;

	public MatchService() throws SQLException, IOException
	{
		DBHelper dBHelper = new DBHelper();
		ConnectionSource connectionSource = dBHelper.getConnectionSource();

		this.connectionSource = connectionSource;
		matchDao = DaoManager.createDao(this.connectionSource, Match.class);
	}

	public List<Match> getAllMatches() throws SQLException
	{
		List<Match> matchList = matchDao.queryForAll();
		return matchList;
	}
}
