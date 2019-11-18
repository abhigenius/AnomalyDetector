
### Anomaly Detector ###

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

Payload : {
              "eventId" : "cj86g5ypk000004zvevipqxfn",
              "sensorId" : "fd0a635d-2aaf-4460-a817-6353e1b6c050",
              "timestamp" : "1506723249",
              "value" : "25.6734"
          }

5) Response is generated as per the input given.

{
  "eventId": "cj86g5ypk000004zvevipqxfn",
  "sensorId": "fd0a635d-2aaf-4460-a817-6353e1b6c050",
  "timestamp": "1506723249",
  "value": "25.6734",
  "status": "NO_MODEL",
  "cause": "",
  "message": ""
}

