package com.accesso.challengeladder.controller;

import static spark.Spark.get;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.accesso.challengeladder.model.Match;
import com.accesso.challengeladder.services.MatchService;
import com.accesso.challengeladder.utils.JsonUtil;

public class MatchController
{
	public MatchController() throws SQLException, IOException
	{
		MatchService matchService = new MatchService();

		get("/matches", (req, res) -> {
			List<Match> rankings = matchService.getAllMatches();
			return JsonUtil.toJson(rankings);
		});
	}
}
