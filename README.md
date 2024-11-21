# Trip Management API Exposes

This repo will contain restaurant management APIs.


**Prerequisite: **
*  openjdk 17
*  Postgresql 10
*  git client


**Prepare Database: **

    psql -U postgres -d postgres

    create user tms_user with encrypted password '123';

    create database trip_management_db owner tms_user;

now as we have our db created we need to run the db script

    1. resources/db_script/s1_create_trip_info_table.sql

    2.  resources/db_script/s2_insert_table_data.sql

Build the war file using this command

    ./gradlew clean bootWar

The war will found on \build\libs\trip-management.war

Finally, copy the generated war to /path/to/tomcat10/webapps/ directory as trip-management.war


For test please export the postman collection from this location
resources/postman_collection/trip-managment.postman_collection.json



   
