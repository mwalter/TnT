# TnT - Things and tasks to do #
Licensed under General Public License v3 (GPLv3)  
[newInstance.org](http://www.newinstance.org), 2012-2015

## About TnT ##
A simple web task list where you can create, edit and delete tasks.
English and German languages are supported.

## Usage ##
### Setting up the database ###
TnT uses a MySQL database to persist tasks. SO make sure you have a MySQL database service running at standard port 3306. The database is accessed via JNDI lookup so all you need to do is [download a MySQL connector](http://dev.mysql.com/downloads/connector/j/) and place it into the `lib` directory of your Tomcat installation.
Next you have to add the database resource to your Tomcat server by editing the `context.xml` file in the `conf` directory:
```XML
<Resource name="jdbc/tntdb" auth="Container" type="javax.sql.DataSource"
    maxActive="1" maxIdle="1" maxWait="10000"
    username="user" password="pass" driverClassName="com.mysql.jdbc.Driver"
    url="jdbc:mysql://localhost:3306/tntdb">
</Resource>
```
Connect to MySQL and create a new database with the name `tntdb`.
Grant the user all CRUD privileges on database `tntdb`. In order to have the application create all database tables automatically you have to add privileges `CREATE, ALTER, INDEX, DROP` to the database user as well.

### Launching the application ###
To create the war file execute `mvn clean install`. Then start Tomcat and deploy the application.
Point your browser to `localhost:8080`.

## Technologies ##
TnT is implemented in Java and uses JSF ([PrimeFaces](http://primefaces.org/)), [Spring](http://spring.io/) and JPA ([Hibernate](http://hibernate.org/)).

### Build ###
The application is built using Maven.

[![Build Status](https://drone.io/github.com/mwalter/TnT/status.png)](https://drone.io/github.com/mwalter/TnT/latest)