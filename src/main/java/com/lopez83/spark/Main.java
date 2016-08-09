package com.lopez83.spark;

import java.io.IOException;
import java.sql.SQLException;

import org.apache.log4j.Logger;

import com.lopez83.spark.controller.UserController;
import com.lopez83.spark.utils.CorsFilters;

public class Main {

	private static final Logger logger = Logger.getLogger(Main.class
			.getCanonicalName());

	public static void main(String[] args) throws ClassNotFoundException,
			SQLException, IOException {

		logger.debug("Starting...");

		CorsFilters.apply();

		new UserController();

	}
}