package com.accesso.challengeladder.controller;

import java.io.IOException;
import java.sql.SQLException;

import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.services.EmailService;
import com.accesso.challengeladder.services.UserService;
import org.apache.log4j.Logger;

import static spark.Spark.post;


public class EmailController
{
    private static final Logger logger = Logger.getLogger(UserService.class.getCanonicalName());

    public EmailController() throws SQLException, IOException
    {

        UserService userService = new UserService();

        post("/challenge/:challenger/:challenged", (req, res) -> {
            String challenger = req.params(":challenger");
            User challengerUser = userService.getUser(challenger);

            String challenged = req.params(":challenged");
            User challengedUser = userService.getUser(challenged);

            String challengerEmail = "You challenged " + challengedUser.getName() + "!";
            String challengedEmail = challengerUser.getName() + "challenged you!";

            EmailService.sendEmail("Ping Pong Challenge!", challengerUser.getEmail(), challengerEmail);
            EmailService.sendEmail("Ping Pong Challenge!", challengedUser.getEmail(), challengedEmail);

            return "Email Sent!";
        });

    }
}
