package com.accesso.challengeladder.controller;

import com.google.gson.Gson;
import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.sql.SQLException;

import org.eclipse.jetty.http.HttpStatus;

import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.services.UserService;
import com.accesso.challengeladder.utils.JsonUtil;
import static spark.Spark.put;

public class UserController {
	public UserController() throws SQLException, IOException {

		UserService userService = new UserService();

		// Routes, logic defined in the service
		get("/users", (req, res) -> JsonUtil.toJson(
                userService.getAllUsers())
		);

        post("/users", (req, res) -> userService.addUser(
                new Gson().fromJson(req.body(), User.class)
        ));

        put("/users", (req, res) -> userService.updateUser(
                new Gson().fromJson(req.body(), User.class)
        ));

		get("/users/:id", (req, res) -> {
            User user = userService.getMaskedUser(req.params(":id"));
            if (user == null) {
                res.status(HttpStatus.NOT_FOUND_404);
                return "User not found";
            } else {
                return JsonUtil.toJson(user);
            }
        });

        //this cant be right......
        post("/users/:email/:password/:login", (req, res) -> {
            if (req.params(":login").equals("1"))
            {
                String userId = userService.validateUser(req.params(":email"), req.params(":password"));
                User returnUser = userService.getMaskedUser(userId);

                if(returnUser == null)
                {
                    res.status(HttpStatus.NOT_FOUND_404);
                    return "User login failed";
                }

                User user = userService.getMaskedUser(userId);

                return JsonUtil.toJson(user);

            }

            res.status(HttpStatus.NOT_FOUND_404);
            return "User login failed";
        });

	}
}
