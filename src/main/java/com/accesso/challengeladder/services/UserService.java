package com.accesso.challengeladder.services;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;

import com.accesso.challengeladder.model.User;
import com.accesso.challengeladder.utils.DBHelper;
import com.j256.ormlite.dao.Dao;
import com.j256.ormlite.dao.DaoManager;
import com.j256.ormlite.support.ConnectionSource;
import org.mindrot.jbcrypt.BCrypt;

public class UserService
{

	private static final Logger logger = Logger.getLogger(UserService.class.getCanonicalName());

	private ConnectionSource connectionSource;
	private Dao<User, String> userDao;
    private static String salt;

	public UserService() throws SQLException, IOException
	{

		DBHelper dBHelper = new DBHelper();
		ConnectionSource connectionSource = dBHelper.getConnectionSource();

		this.connectionSource = connectionSource;
		userDao = DaoManager.createDao(this.connectionSource, User.class);


	}

	public boolean addUser(User newUser)
	{
        BCrypt cryptWorker = new BCrypt();

		try
		{
            salt = cryptWorker.gensalt();

            newUser.setSalt(salt);
            newUser.setPassword(cryptWorker.hashpw("pingpong", salt));

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
        BCrypt cryptWorker = new BCrypt();

		try
		{
            salt = cryptWorker.gensalt();

            Integer currentUserId = updatedUser.getId();
            User currentUser = getUser(currentUserId.toString());
            currentUser.setName(updatedUser.getName());
            currentUser.setSalt(salt);
            currentUser.setPassword(updatedUser.getPassword());
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

    public User getUserByEmail(String email) throws SQLException
    {
        Map newMap = new HashMap();

        newMap.put("email", email);

        User user = null;
        if (email != null) {
            List<User> results = userDao.queryForEq("email", email);
            user = results.get(0);
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

    public String validateUser(String email, String password) throws Exception
    {
        BCrypt cryptWorker = new BCrypt();

        User user = getUserByEmail(email);

        String salt = user.getSalt();

        String hashedPW = cryptWorker.hashpw(password, salt);

        if (user.getPassword().equals(hashedPW))
        {
            return Integer.toString(user.getId());
        }

        return null;
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
