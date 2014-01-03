# Setup the initial database

1. Create an empty database
2. Execute the statements provided in "src/main/resources/mysql/create-tables.sql" to create the initial schema
3. Adjust the database configuration in "src/main/resources/spring/datasource.xml"
4. Run "InitMain" as Java Application to insert the initial data

# Run Liquibase via Spring

Simply execute "LiquibaseMain" as Java Application in order to migrate the database to the latest version. 
This should result in two new database tables "databasechangelog" and "databasechangeloglock".
In the "databasechangelog" table you should see that all changesets defined in "src/main/resources/changelogs/db-changelog-master.xml" have been executed.

In order to test the available features, adjust the Liquibase parameters in "src/main/resources/spring/spring-liquibase.xml". 

See also: http://www.liquibase.org/documentation/spring.html

# Setup Liquibase for commandline usage

1. Download the current Liquibase release (http://www.liquibase.org/download/index.html)
2. Unpack it somwhere on the file system
3. Copy the file "src/main/resources/liquibase/liquibase.properties" into "<liquibase-home>" and adjust the contained properties
4. Copy the file "src/main/resources/liquibase/mysql-connector-java-5.1.10.jar" into "<liquibase-home>/lib"
5. Open a commandline and navigate to "<liquibase-home>"
6. Run the following command to migrate the database to the latest version: liquibase.bat --defaultsFile=.\liquibase.properties update

Other interesting commands:
liquibase.bat --defaultsFile=.\liquibase.properties --contexts=test update
liquibase.bat --defaultsFile=.\liquibase.properties updateSQL > foo.sql
liquibase.bat --defaultsFile=.\liquibase.properties rollback 1.4
liquibase.bat --defaultsFile=.\liquibase.properties rollbackCount 3
liquibase.bat --defaultsFile=.\liquibase.properties rollbackToDate 2014-01-01

See also: http://www.liquibase.org/documentation/command_line.html