# AnomalyDetector



This Anomaly Detector application is developed in pure java without any use of framework(such as Spring), external Servers (like Tomcat)
and helper libraries(such as Lombok) to keep it as generic as possible.


Steps to Run the project.

1) Import the project and do maven clean and maven install.

mvn clean && mvn install.

2) Run the Application.java file to start the application.

3) Hit the URL http://localhost:8000 in the browser.

If the message "Server started successfully." is displayed, the server is successfully started.

4) Use Postman or use CURL command to hit the API for anomaly detection as follows :

POST : http://localhost:8000/api/event

##Sample Payload 1
          {
              "eventId" : "cj86g5ypk000004zvevipqxfn12",
              "sensorId" : "fd0a635d-2aaf-4460-a817-6353e1b6c050",
              "timestamp" : "1506723249",
              "value" : "25.6734"
          }

###Response is generated as per the input given.

    {
      "eventId": "cj86g5ypk000004zvevipqxfn12",
      "sensorId": "fd0a635d-2aaf-4460-a817-6353e1b6c050",
      "timestamp": "1506723249",
      "value": "25.6734",
      "status": "NO_ANOMALY",
      "cause": "",
      "message": ""
    }


##Sample Payload 2 :

    {
        "eventId" : "cj86g5ypk000004zvevipqxfn12",
        "sensorId" : "fd0a635d-2aaf-4460-a817-6353e1b6c050",
        "timestamp" : "1506723249",
        "value" : "28.6734"
    }

Response :

    {
      "eventId": "cj86g5ypk000004zvevipqxfn12",
      "sensorId": "fd0a635d-2aaf-4460-a817-6353e1b6c050",
      "timestamp": "1506723249",
      "value": "28.6734",
      "status": "ANOMALY",
      "cause": "Upper Bound Threshold Detector",
      "message": "Exceeds threshold"
    }


##Sample payload 3

    {
        "eventId" : "cj86g5ypk000004zvevipqxfn12",
        "sensorId" : "xyz",
        "timestamp" : "1506723249",
        "value" : "28.6734"
    }

Response

    {
      "eventId": "cj86g5ypk000004zvevipqxfn12",
      "sensorId": "xyz",
      "timestamp": "1506723249",
      "value": "28.6734",
      "status": "NO_MODEL",
      "cause": "",
      "message": ""
    }



## Average Anomaly Detector Sample Payload

Add multiple events to the database for the sensorId 9b779fec-79f9-4fd8-8b12-f17d8b233b46 (which supports average anomaly detection as per current configs)

    {
        "eventId" : "cj86g5ypk000004zvevipqxfn12",
        "sensorId" : "9b779fec-79f9-4fd8-8b12-f17d8b233b46",
        "timestamp" : "1506723249",
        "value" : "26.6734"
    }

Response (No Anomaly as average is less than threshold)

    {
      "eventId": "cj86g5ypk000004zvevipqxfn12",
      "sensorId": "9b779fec-79f9-4fd8-8b12-f17d8b233b46",
      "timestamp": "1506723249",
      "value": "26.6734",
      "status": "NO_ANOMALY",
      "cause": "",
      "message": ""
    }


Add one more event with different event Id :

    {
        "eventId" : "neweventid",
        "sensorId" : "9b779fec-79f9-4fd8-8b12-f17d8b233b46",
        "timestamp" : "1506723249",
        "value" : "36.6734"
    }

Response (No Anomaly as average is less than 35) :

    {
      "eventId": "neweventid",
      "sensorId": "9b779fec-79f9-4fd8-8b12-f17d8b233b46",
      "timestamp": "1506723249",
      "value": "36.6734",
      "status": "NO_ANOMALY",
      "cause": "",
      "message": ""
    }

Add one more event with a new eventId

    {
        "eventId" : "neweventid2",
        "sensorId" : "9b779fec-79f9-4fd8-8b12-f17d8b233b46",
        "timestamp" : "1506723249",
        "value" : "136.6734"
    }

Response (Anaomaly detected as average is more than 35)

    {
      "eventId": "neweventid2",
      "sensorId": "9b779fec-79f9-4fd8-8b12-f17d8b233b46",
      "timestamp": "1506723249",
      "value": "136.6734",
      "status": "ANOMALY",
      "cause": "Average Threshold Anomaly Detector",
      "message": "Exceeds threshold"
    }

