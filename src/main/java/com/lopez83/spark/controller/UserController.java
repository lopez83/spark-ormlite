package com.lopez83.spark.controller;

import static spark.Spark.get;
import static spark.Spark.post;

import java.util.List;

import com.google.gson.Gson;
import com.lopez83.spark.model.User;
import com.lopez83.spark.services.UserService;
import com.lopez83.spark.utils.JsonUtil;

public class UserController {
	public UserController(final UserService userService) {

		Gson gson = new Gson();

		// Routes, logic defined in the service

		get("/users", (req, res) -> {
			List<User> users = userService.getAllUsers();
			return gson.toJson(users);
		});

		get("/users/:id", (req, res) -> {
			User user = userService.getUser(req.params(":id"));
			if (user != null) {
				return gson.toJson(user);
			}

			res.status(404);
			return "User not found";
		});

		post("/users", (req, res) -> userService.createUser(req.queryParams("username"), req.queryParams("email")),
				JsonUtil.json());

	}
}
