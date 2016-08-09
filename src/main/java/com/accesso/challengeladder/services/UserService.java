package com.accesso.challengeladder.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import org.apache.log4j.Logger;

import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.utils.DBHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;

public class UserService
{

	private static final Logger logger = Logger.getLogger(UserService.class.getCanonicalName());

	private ConnectionSource connectionSource;
	private Dao<User, String> userDao;

	public UserService() throws SQLException, IOException
	{

		DBHelper dBHelper = new DBHelper();
		ConnectionSource connectionSource = dBHelper.getConnectionSource();

		this.connectionSource = connectionSource;
		userDao = DaoManager.createDao(this.connectionSource, User.class);
	}

	public boolean addUser(User newUser)
	{
		try
		{
            newUser.setPassword("pingpong");
            newUser.setSalt("asdf");
            userDao.create(newUser);
            return true;
		}
		catch (Exception e)
		{
			logger.error("Exception..." + e.getMessage());
			return false;
		}
	}

	public boolean updateUser(User updatedUser)
	{
		try
		{
            Integer currentUserId = updatedUser.getId();
            User currentUser = getUser(currentUserId.toString());
            currentUser.setName(updatedUser.getName());
            currentUser.setEmail(updatedUser.getEmail());
            currentUser.setAdminFlag(updatedUser.getAdminFlag());
            currentUser.setActiveFlag(updatedUser.getActiveFlag());
            userDao.update(currentUser);
            return true;
		}
		catch (Exception e)
		{
			logger.error("Exception..." + e.getMessage());
			return false;
		}
	}

	public User createUser(String username, String email)
	{
		User user = new User();
		try
		{

			user.setEmail(email);
			// creates a new user in the DB
			userDao.create(user);
		}
		catch (Exception e)
		{
			logger.error("Exception..." + e.getMessage());
			return null;
		}
		return user;
	}

	public User getUser(String userId) throws SQLException
	{
        User user = null;
        if (userId != null) {
            user = userDao.queryForId(userId);
        }
        return user;
    }

	public User getMaskedUser(String userId) throws SQLException
	{
        User user = getUser(userId);
        if (userId != null) {
            user.setPassword(null);
            user.setSalt(null);
        }
        return user;
    }

	public List<User> getAllUsers() throws SQLException
	{
		List<User> userList = userDao.queryForAll();
        for(User user: userList) {
            user.setPassword(null);
            user.setSalt(null);
            user.setActiveFlag(null);
            user.setAdminFlag(null);
            user.setEmail(null);
        }
		return userList;
	}
}
