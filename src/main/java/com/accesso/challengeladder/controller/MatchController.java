package com.accesso.challengeladder.controller;

import static spark.Spark.get;
import static spark.Spark.post;
import static spark.Spark.put;

import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
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

		// get information about a match
		get("/matches/:id", (req, res) -> {
			return JsonUtil.toJson(matchService.getMatch(req.params(":id")));
		});

		// get all users in a match
		get("/matches/:id/users", (req, res) -> {
			return JsonUtil.toJson(matchService.getMatchUsers(req.params(":id")));
		});

		// TODO create match functionality
		post("/matches", (req, res) -> {

			return null;
		});

		// TODO params are passed as a json string in the body entity
		put("/matches/:id",
				(req, res) -> {
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
					return JsonUtil.toJson(matchService.updateMatch(req.queryParams("matchId"), req.queryParams("victorId"),
							sdf.parse(req.queryParams("matchTimestamp")), req.queryParams("status"), sdf.parse(req.queryParams("creationTimestamp")),
							req.queryParams("creatorId")));
				});
	}
}
