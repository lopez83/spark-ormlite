package com.accesso.challengeladder.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.accesso.challengeladder.model.Match;
import com.accesso.challengeladder.model.RankingHistory;
import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.utils.DBHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class RankingHistoryService
{

	private static final Logger logger = Logger.getLogger(RankingHistoryService.class.getCanonicalName());

	private ConnectionSource connectionSource;
	private Dao<RankingHistory, String> rankingHistoryDao;
	private Dao<User, String> userDao;
	private Dao<Match, String> matchDao;

	public RankingHistoryService() throws SQLException, IOException
	{

		DBHelper dBHelper = new DBHelper();
		ConnectionSource connectionSource = dBHelper.getConnectionSource();

		this.connectionSource = connectionSource;
		rankingHistoryDao = DaoManager.createDao(this.connectionSource, RankingHistory.class);
		userDao = DaoManager.createDao(this.connectionSource, User.class);
		matchDao = DaoManager.createDao(this.connectionSource, Match.class);
	}

	public RankingHistory createRankingHistory(int ranking, int userId, int matchId)
	{
		RankingHistory rankingHistory = new RankingHistory();

		try
		{
			Match match = matchDao.queryForId(String.valueOf(matchId));
			rankingHistory.setMatch(match);
			User user = userDao.queryForId(String.valueOf(userId));
			rankingHistory.setUser(user);
			rankingHistory.setRanking(ranking);
			// creates a new rankingHistory in the DB
			rankingHistoryDao.create(rankingHistory);
		}
		catch (Exception e)
		{
			logger.error("Exception..." + e.getMessage());
			return null;
		}
		return rankingHistory;
	}

	public List<RankingHistory> getAll() throws SQLException
	{
		List<RankingHistory> rankingHistoryList = rankingHistoryDao.queryForAll();
		return rankingHistoryList;
	}
}
