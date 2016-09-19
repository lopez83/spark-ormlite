package com.accesso.challengeladder.controller;

import static spark.Spark.post;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.services.EmailService;
import com.accesso.challengeladder.services.UserService;

public class EmailController
{
	private static final Logger logger = Logger.getLogger(UserService.class.getCanonicalName());

	public EmailController() throws SQLException, IOException
	{

		UserService userService = new UserService();
		// TODO challenge endpoint is the same as POST /matches endpoint
		post("/challenge/:challenger/:challenged", (req, res) -> {
			String challenger = req.params(":challenger");
			User challengerUser = userService.getUser(challenger);

			String challenged = req.params(":challenged");
			User challengedUser = userService.getUser(challenged);

			String emailMessage = challengerUser.getName() + " challenged " + challengedUser.getName() + "!";

			EmailService.sendEmail("Ping Pong Challenge!", challengerUser.getEmail(), emailMessage);
			EmailService.sendEmail("Ping Pong Challenge!", challengedUser.getEmail(), emailMessage);

			return "Email Sent!";
		});

	}
}
