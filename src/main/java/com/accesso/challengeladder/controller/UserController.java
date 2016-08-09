package com.accesso.challengeladder.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.eclipse.jetty.http.HttpStatus;

import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.services.UserService;
import com.accesso.challengeladder.utils.JsonUtil;

public class UserController {
	public UserController() throws SQLException, IOException {

		UserService userService = new UserService();

		// Routes, logic defined in the service
		get("/users", (req, res) -> {
			List<User> users = userService.getAllUsers();
			return JsonUtil.toJson(users);
		});

		get("/users/:id", (req, res) -> {
			User user = userService.getUser(req.params(":id"));
			if (user != null) {
				return JsonUtil.toJson(user);
			}

			res.status(HttpStatus.NOT_FOUND_404);
			return "User not found";
		});

		// post?username=myusername&email=myemail
		post("/users",
				(req, res) -> userService.createUser(
						req.queryParams("username"), req.queryParams("email")),
				JsonUtil.json());

	}
}
