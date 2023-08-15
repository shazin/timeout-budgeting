# Microservice Timeout Budgeting in Spring Boot

How to run
==========

1. Build the project
2. Run Payment Service in port 9090 - `java -jar -Dserver.port=9090 timeout-budgeting-0.0.1-SNAPSHOT.jar`
3. Run User Service in port 8080 - `java -jar -Dserver.port=8080 timeout-budgeting-0.0.1-SNAPSHOT.jar`
4. Invoke `http://localhost:8080/users/123` with headers `REQUEST_START_MILLIS` and `TIMEOUT_BUDGET_SECONDS`
5. Value for `REQUEST_START_MILLIS` can be retrieved from https://currentmillis.com.
6. Value for `TIMEOUT_BUDGET_SECONDS` is just the timeout in seconds.


