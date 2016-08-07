Simple RESTful API Spark and ORMLite
====================================

Simple RESTful API using Spark framework and ORMLite in the persistence layer.

* Spark: http://sparkjava.com/documentation.html

* ORMLite: http://ormlite.com/ 


Start the application
=====================

* Create DB and users table: execute resources/schema.sql

* Set desired database parameters in db.properties file

* Run Main.java, this will start the server in port 4567

To create a new user:

	curl -X POST http://localhost:4567/users?username=oscar&email=oscar@mail.com

Get user with ID = 1:

	curl -X GET http://localhost:4567/users/1
	
Get list of users:
	
	curl -X GET http://localhost:4567/users
