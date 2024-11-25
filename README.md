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

    resources/postman_collection/trip-management-service-API-Collection.postman_collection.json_

Example API Request and Response Format

1. POST Request Example: Create a New Trip

   HTTP Method: POST

   Endpoint: /api/v1/tms/tripInfo/create

   Description: Creates a new trip with the provided details.

    Request:

    URL: POST /api/v1/tms/tripInfo/create

    Headers:

        Content-Type: application/json
        Authorization: No Auth

    Body:
    
        {
            "pickupDistrictId":3,
            "pickUpAddress":"Karimpur",
            "dropOffDistrictId":1,
            "dropOffAddress":"Rahimpur",
            "startDate":"2024-11-02T06:30:40",
            "endDate": "2024-11-05T06:30:40"
        }


   Response:
       
   HTTP Status Code: 201 Created (when the trip is successfully created)
    
Headers:
    
        Content-Type: application/json
    
Body:
    
        {
            "code": "TRIP-1732500249",
            "pickupDistrictId": 3,
            "pickupDistrictName": "Gazipur",
            "pickUpAddress": "Karimpur",
            "dropOffDistrictId": 1,
            "dropOffDistrictName": "Dhaka",
            "dropOffAddress": "Rahimpur",
            "currentStatus": "CREATED",
            "startDate": 1730529040000,
            "endDate": 1730788240000,
            "realTimeLocation": null,
            "assignedTransport": null,
            "createBy": 1,
            "createDate": 1732500249958,
            "responseMessage": "Successfully Created",
            "errorCode": null
        }


2. PUT Request Example: Update Trip Status

      HTTP Method: PUT

      Endpoint: /api/v1/tms/tripInfo/update

      Description: Updates trip status of an existing trip's details using the trip code.

    Request:
    URL: PUT /api/v1/tms/tripInfo/update

    Headers:

        Content-Type: application/json
        Authorization: No Auth
    Body:
        
        {
           "tripCode":"TRIP-001",
           "currentStatus":"RUNNING"   
        }

    Response:

       {
           "tripCode": "TRIP-1732384997",
           "tripStatus": "Booked",
           "assignedTransportId": 0,
           "message": "Update Successfully",
           "errorCode": null
       }
    HTTP Status Code: 200 OK (when the trip is successfully updated)
  
    Headers:

        Content-Type: application/json
    Body:   

        {
            "tripCode": "TRIP001",
            "tripStatus": "BOOKED",
            "assignedTransportId": "T123",
            "responseMessage": "Trip updated successfully"
        }
    If the trip code is not found:
    
    Headers:

        Content-Type: application/json
    Body:  
    
        {
            "tripCode": null,
            "tripStatus": null,
            "assignedTransportId": null,
            "responseMessage": "Invalid Trip Code",
            "errorCode": "INVALID_REQUEST"
        }
3. PUT Request Example: Assign Transporter

   HTTP Method: PUT

   Endpoint: /api/v1/tms/tripInfo/update

   Description: Add transporter to an existing trip using the trip code.

   Request:
   URL:  PUT  /api/v1/tms/tripInfo/update

   Headers:

        Content-Type: application/json
        Authorization: No Auth
   Body:

        {
             "tripCode":"TRIP-1732384997",   
             "transportId":1
        }

   Response:
    
        {
           "tripCode": "TRIP-1732384997",
           "tripStatus": "Booked",
           "assignedTransportId": 1,
           "message": "Update Successfully",
           "errorCode": null
        }
      HTTP Status Code: 200 OK (when the trip is successfully updated)

      Headers:

           Content-Type: application/json
      Body:

           {
             "tripCode":"TRIP-001",   
             "transportId":1
           }
      If the trip code is not found:

      Headers:

           Content-Type: application/json
      Body:

           {
             "tripCode": null,
             "tripStatus": null,
             "assignedTransportId": null,
             "responseMessage": "Invalid Trip Code",
             "errorCode": "INVALID_REQUEST"
           }

4. GET Request Example: Retrieve Trip Information by Code

   HTTP Method: GET

   Endpoint: /api/v1/tms/tripInfo/get?tripCode=TRIP-1732384997

   Description: Fetches details of a trip using a unique trip code.

    Request:
    
    URL: GET /api/v1/tms/tripInfo/get?tripCode=TRIP-1732384997

    Headers:    

       Content-Type: application/json
       Authorization: No Auth
    No request body is needed for a GET request.
    
    Response:    
    HTTP Status Code: 200 OK (if the trip is found)
      
       {
       "code": "TRIP-1732384997",
       "pickupDistrictId": 2,
       "pickupDistrictName": "Chotrogram",
       "pickUpAddress": "Karimpur",
       "dropOffDistrictId": 3,
       "dropOffDistrictName": "Gazipur",
       "dropOffAddress": "Rahimpur",
       "currentStatus": "BOOKED",
       "startDate": 1730529040000,
       "endDate": 1730788240000,
       "realTimeLocation": null,
       "assignedTransport": 0
       }

    If the trip code is invalid:

    URL: GET /api/v1/tms/tripInfo/get?tripCode=TRIP-001

    Headers:
    
        Content-Type: application/json
    
    Body:
    
        {
         "responseMessage": "Invalid Trip Code",
         "errorCode": "INVALID_REQUEST"
        }