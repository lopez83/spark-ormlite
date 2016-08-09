package com.accesso.challengeladder.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
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
            for(User user: users) {
                user.setPassword(null);
                user.setSalt(null);
                user.setActiveFlag(null);
                user.setAdminFlag(null);
                user.setEmail(null);
            }
			return JsonUtil.toJson(users);
		});

		get("/users/:id", (req, res) -> {
            String userId = req.params(":id");
            User user = null;
            if (userId != null) {
			    user = userService.getUser(userId);
            }
            if (user != null) {
                user.setPassword(null);
                user.setSalt(null);
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
