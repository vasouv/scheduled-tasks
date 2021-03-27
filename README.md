# scheduled tasks

The backend with a Scheduler service creates votes, resembling a very simple voting system. A chart shows the votes of each candidate.

## Description
The service uses a scheduler to generate votes every 3 seconds. The UI uses a timer to call the endpoint every 5 seconds and re-create the chart. As a simple error handling mechanism, if the UI cannot reach the backend an error is logged to the console and a JSON with 0 values is returned. This functionality can be seen if the Java application is stopped after the UI is started.

## Requirements
- Java 16
- Maven 3.6.3

## Run
`mvn package`

`java -jar scheduled-tasks.jar`

Then go to `localhost:8080` to see the chart. After 5 seconds, the chart will be reloaded.
