package com.accesso.challengeladder.controller;

import com.accesso.challengeladder.requests.LoginRequest;
import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.services.UserService;
import com.accesso.challengeladder.utils.JsonUtil;
import com.google.gson.Gson;
import org.eclipse.jetty.http.HttpStatus;
import static spark.Spark.post;

import java.io.IOException;
import java.sql.SQLException;

public class LoginController
{
    public LoginController() throws SQLException, IOException
    {
        UserService userService = new UserService();
        post("/login", (req, res) -> {
            LoginRequest newLoginRequest = new Gson().fromJson(req.body(), LoginRequest.class);

            String userId = userService.validateUser(newLoginRequest.getUsername(), newLoginRequest.getPassword());
            User returnUser = userService.getUser(userId);

            if (returnUser == null)
            {
                res.status(HttpStatus.UNAUTHORIZED_401);
                return "User login failed";
            }

            return JsonUtil.toJson(returnUser);
        });
    }
}
