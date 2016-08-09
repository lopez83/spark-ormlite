package com.lopez83.spark.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import com.lopez83.spark.model.User;
import com.lopez83.spark.utils.DBHelper;

public class UserService {

	private ConnectionSource connectionSource;
	private Dao<User, String> userDao;

	public UserService() throws SQLException, IOException {

		DBHelper dBHelper = new DBHelper();
		ConnectionSource connectionSource = dBHelper.getConnectionSource();

		this.connectionSource = connectionSource;
		userDao = DaoManager.createDao(this.connectionSource, User.class);
	}

	public User createUser(String username, String email) throws SQLException {
		User user = new User();
		user.setUsername(username);
		user.setEmail(email);
		// creates a new user in the DB
		userDao.create(user);
		return user;
	}

	public User getUser(String id) throws SQLException {
		User user = userDao.queryForId(id);
		if (user != null) {
			return user;
		} else {
			return null;
		}
	}

	public List<User> getAllUsers() throws SQLException {
		List<User> userList = userDao.queryForAll();
		return userList;
	}
}
