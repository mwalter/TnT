# TnT - Things and tasks to do #
Licensed under General Public License v3 (GPLv3)  
[newInstance.org](http://www.newinstance.org), 2012-2013

## About TnT ##
A simple web task list where you can create, edit and delete tasks. Every task belongs to an owner.
English and German language are supported.

## Usage ##
### Setting up the database ###
Make sure you have a MySQL database service running at standard port 3306.
Connect to MySQL and create a new database with the name `tntdb`.
Create a new database user with the name `tntuser` and password `newinstance`.
Grant this user all privileges on database `tntdb`.

### Launching the application ###
To create the war file execute `mvn clean install`. Then start Tomcat.
Point your browser to `localhost:8080`.

## Technologies ##
TnT is implemented in Java and uses JSF (PrimeFaces), Spring, Hibernate and a MySQL database.
The application is built by Maven.