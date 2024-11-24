# Trip Management Service API Exposes

This repo will contain Trip management service APIs.


**Prerequisite: **
*  openjdk 17
*  Postgresql 10 or higher
*  git client


**Prepare Database: **

    psql -U postgres -d postgres

    create user tms_user with encrypted password '123';

    create database trip_management_db owner tms_user;

now as we have our db created we need to run the db script:
    This script contains some district's data

    1. resources/db_script/v1_script_district.sql

    2. resources/db_script/v2_script_tripinfomation.sql

The application will be hosted on port 8085.

Build the war file using this command

    ./gradlew clean bootWar

The war will found on \build\libs\trip-management.war

Finally, For Deployment copy the generated war to /path/to/tomcat10/webapps/     directory as trip-management.war



For test please export the postman collection from this location

    resources/postman_collection/trip-management-service-API-Collection.postman_collection.json



   
