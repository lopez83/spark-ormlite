package com.accesso.challengeladder.controller;

import com.accesso.challengeladder.model.LoginForm;
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
            LoginForm newLoginForm = new Gson().fromJson(req.body(), LoginForm.class);

            String userId = userService.validateUser(newLoginForm.getEmail(), newLoginForm.getPassword());
            User returnUser = userService.getUser(userId);

            if (returnUser == null)
            {
                res.status(HttpStatus.NOT_FOUND_404);
                return "User login failed";
            }

            return JsonUtil.toJson(returnUser);
        });
    }
}
