package com.lopez83.spark;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.j256.ormlite.support.ConnectionSource;
import com.lopez83.spark.controller.UserController;
import com.lopez83.spark.services.UserService;
import com.lopez83.spark.utils.CorsFilters;
import com.lopez83.spark.utils.DBHelper;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class.getCanonicalName());

	public static void main(String[] args) throws ClassNotFoundException, SQLException, IOException {

		logger.debug("Starting...");

		CorsFilters.apply();

		DBHelper dBHelper = new DBHelper();
		ConnectionSource connectionSource = dBHelper.getConnectionSource();

		// instanciate controllers
		new UserController(new UserService(connectionSource));

	}
}